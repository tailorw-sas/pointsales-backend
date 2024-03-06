package com.kynsoft.notification.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MailJetVar {
    public String Key;
    public Object Value;

    public static JSONObject createVarsJsonObject(List<MailJetVar> vars) {
        JSONObject varsJsonObject = new JSONObject();

        for (MailJetVar var : vars) {
            varsJsonObject.put(var.getKey(), var.getValue());
        }

        return varsJsonObject;
    }
}
