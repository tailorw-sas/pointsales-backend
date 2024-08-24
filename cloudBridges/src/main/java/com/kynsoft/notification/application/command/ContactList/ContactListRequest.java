package com.kynsoft.notification.application.command.ContactList;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactListRequest {
    private String name;
    private List<String> emails;
}
