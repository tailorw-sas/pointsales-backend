package com.kynsoft.notification.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MailJetRecipient {
    private String email;
    private String name;

    public static JSONArray createRecipientsJsonArray(List<MailJetRecipient> recipients) {
        JSONArray recipientsArray = new JSONArray();

        for (MailJetRecipient recipient : recipients) {
            recipientsArray.put(new JSONObject()
                    .put("Email", recipient.getEmail())
                    .put("Name", recipient.getName()));
        }

        return recipientsArray;
    }
}
