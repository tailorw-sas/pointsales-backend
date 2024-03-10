package com.kynsof.share.core.domain.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileKafka {

    private String fileName;

    private byte [] file;

    public FileKafka() {
    }

}
