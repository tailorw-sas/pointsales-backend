package com.kynsoft.socket.transfer.socket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputMessage extends Message {

    private String time;

    public OutputMessage(final String from, final String to, final String text, final String time) {
        super(from, to, text);
        this.time = time;
    }
}
