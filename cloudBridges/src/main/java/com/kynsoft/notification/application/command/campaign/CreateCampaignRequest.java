package com.kynsoft.notification.application.command.campaign;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignRequest {

    @NotBlank(message = "campaign name is required")
    private String campaignName;

    @Email(message = "Sender email should be valid")
    @NotBlank(message = "Sender email is required")
    private String senderEmail;

    @NotBlank(message = "Sender name is required")
    private String senderName;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull(message = "Template ID is required")
    private Long templateId;

    @NotNull(message = "Contact list ID is required")
    private Long contactListId;
}
