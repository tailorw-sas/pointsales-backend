package com.kynsoft.notification.infrastructure.service;

import com.kynsoft.notification.domain.dto.EmailRequest;
import com.kynsoft.notification.domain.dto.MailJetAttachment;
import com.kynsoft.notification.domain.dto.MailJetRecipient;
import com.kynsoft.notification.domain.dto.MailJetVar;
import com.kynsoft.notification.domain.service.IEmailService;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Contact;
import com.mailjet.client.resource.Contactslist;
import com.mailjet.client.resource.Email;
import com.mailjet.client.resource.Listrecipient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceMailjet implements IEmailService {

    public EmailServiceMailjet() {
    }

    @Override
    public void sendEmailMailjet(EmailRequest emailRequest, String mailjetApiKey, String mailjetApiSecret,
                                 String fromEmail, String fromName) {
        try {
            MailjetClient client = createMailjetClient(mailjetApiKey, mailjetApiSecret);

            MailjetRequest request = new MailjetRequest(Email.resource)
                    .property(Email.FROMEMAIL, fromEmail)
                    .property(Email.FROMNAME, fromName)
                    .property(Email.MJTEMPLATEID, emailRequest.getTemplateId())
                    .property(Email.MJTEMPLATELANGUAGE, true)
                    .property(Email.SUBJECT, emailRequest.getSubject())
                    .property(Email.RECIPIENTS, MailJetRecipient.createRecipientsJsonArray(emailRequest.getRecipientEmail()))
                    .property(Email.ATTACHMENTS, MailJetAttachment.generateAttachments(emailRequest.getMailJetAttachments()))
                    .property(Email.VARS, MailJetVar.createVarsJsonObject(emailRequest.getMailJetVars()))
                    .property(Email.MJEVENTPAYLOAD, "Eticket,1234,row,15,seat,B");

            MailjetResponse response = client.post(request);
            response.getStatus(); // Handle response status

        } catch (MailjetException e) {
            System.err.println("Mailjet API error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
        }
    }

    @Override
    public Long createContactList(String name, List<String> emails, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        MailjetClient client = createMailjetClient(mailjetApiKey, mailjetApiSecret);

        MailjetRequest createListRequest = new MailjetRequest(Contactslist.resource)
                .property("Name", name)
                .property("IsDeleted", "false");

        MailjetResponse createListResponse = client.post(createListRequest);

        if (createListResponse.getStatus() == 201) {
            Long listId = createListResponse.getData().getJSONObject(0).getLong("ID");

            for (String email : emails) {
                Long contactId = getOrCreateContactId(client, email);

                MailjetRequest addContactRequest = new MailjetRequest(Listrecipient.resource)
                        .property("ContactID", contactId)
                        .property("ListID", listId)
                        .property("IsUnsubscribed", "false");

                MailjetResponse addContactResponse = client.post(addContactRequest);

                if (addContactResponse.getStatus() != 201 && addContactResponse.getStatus() != 200) {
                    throw new RuntimeException("Failed to add contact to the list: " + addContactResponse.getStatus() + " " + addContactResponse.getData());
                }
            }

            return listId;
        } else {
            throw new RuntimeException("Failed to create recipients list: " + createListResponse.getStatus() + " " + createListResponse.getData());
        }
    }

    @Override
    public void addContactsToList(Long listId, List<String> emails, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        MailjetClient client = createMailjetClient(mailjetApiKey, mailjetApiSecret);

        for (String email : emails) {
            Long contactId = getOrCreateContactId(client, email);

            MailjetRequest addContactRequest = new MailjetRequest(Listrecipient.resource)
                    .property("ContactID", contactId)
                    .property("ListID", listId)
                    .property("IsUnsubscribed", "false");

            MailjetResponse addContactResponse = client.post(addContactRequest);

            if (addContactResponse.getStatus() != 201 && addContactResponse.getStatus() != 200) {
                throw new RuntimeException("Failed to add contact to the list: " + addContactResponse.getStatus() + " " + addContactResponse.getData());
            }
        }
    }

    @Override
    public void removeContactFromList(Long listId, String email, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        MailjetClient client = createMailjetClient(mailjetApiKey, mailjetApiSecret);

        MailjetRequest request = new MailjetRequest(Contact.resource)
                .filter(Contact.EMAIL, email)
                .filter(Contact.CONTACTSLIST, listId.toString());

        MailjetResponse response = client.delete(request);

        if (response.getStatus() != 200 && response.getStatus() != 204) {
            throw new RuntimeException("Failed to remove contact from the list: " + response.getStatus() + " " + response.getData());
        }
    }

    @Override
    public void deleteContactList(Long listId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        MailjetClient client = createMailjetClient(mailjetApiKey, mailjetApiSecret);

        MailjetRequest request = new MailjetRequest(Contactslist.resource, listId);

        MailjetResponse response = client.delete(request);

        if (response.getStatus() != 204) {  // 204 No Content indicates successful deletion
            throw new RuntimeException("Failed to delete contact list: " + response.getStatus() + " " + response.getData());
        }
    }

    @Override
    public String createCampaign(String campaignName, String senderEmail, String senderName, String subject,
                                 Long templateId, Long contactListId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        // Implement this method if needed
        return "";
    }

    @Override
    public boolean sendCampaign(String campaignId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException {
        // Implement this method if needed
        return false;
    }

    private MailjetClient createMailjetClient(String apiKey, String apiSecret) {
        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(apiSecret)
                .build();

        return new MailjetClient(options);
    }

    private Long getOrCreateContactId(MailjetClient client, String email) throws MailjetException {
        MailjetRequest getContactRequest = new MailjetRequest(Contact.resource)
                .filter(Contact.EMAIL, email);

        MailjetResponse getContactResponse = client.get(getContactRequest);

        if (getContactResponse.getStatus() == 200 && !getContactResponse.getData().isEmpty()) {
            return getContactResponse.getData().getJSONObject(0).getLong("ID");
        } else {
            MailjetRequest createContactRequest = new MailjetRequest(Contact.resource)
                    .property("Email", email);

            MailjetResponse createContactResponse = client.post(createContactRequest);

            if (createContactResponse.getStatus() == 201 || createContactResponse.getStatus() == 200) {
                return createContactResponse.getData().getJSONObject(0).getLong("ID");
            } else {
                throw new RuntimeException("Failed to create contact: " + createContactResponse.getStatus() + " " + createContactResponse.getData());
            }
        }
    }
}