package com.kynsoft.report.applications.command.generateReport;

import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;

@Getter
public class GenerateReportMessage implements ICommandMessage {

    private final byte[] result;

    private final String command = "CREATE_REPORT_CONTENT";

    public GenerateReportMessage(byte[] result ) {
        this.result = result;
    }

}
