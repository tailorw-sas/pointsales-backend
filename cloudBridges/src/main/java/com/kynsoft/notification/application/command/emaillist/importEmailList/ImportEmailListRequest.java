package com.kynsoft.notification.application.command.emaillist.importEmailList;

import lombok.Getter;
import org.springframework.http.codec.multipart.FilePart;

@Getter
public class ImportEmailListRequest {
    private final FilePart emailList;
    private final String importProcessId;

    private final String campaignId;

    public ImportEmailListRequest(FilePart emailList, String importProcessId, String campaignId) {
        this.emailList = emailList;
        this.importProcessId = importProcessId;
        this.campaignId = campaignId;
    }
}
