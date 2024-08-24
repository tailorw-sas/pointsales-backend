package com.kynsof.share.core.application.excel;

import lombok.Data;

import java.io.InputStream;

@Data
public class ReaderConfiguration {

    private InputStream inputStream;

    private boolean readLastActiveSheet;

    private boolean ignoreHeaders;

    private String sheetNameToRead;

    private boolean strictHeaderOrder;

    private int startReadRow;

    public ReaderConfiguration(InputStream inputStream,
                               boolean readLastActiveSheet,
                               boolean ignoreHeaders,
                               String sheetNameToRead,
                               boolean strictHeaderOrder,
                               int startReadRow) {
        this.inputStream = inputStream;
        this.readLastActiveSheet = readLastActiveSheet;
        this.ignoreHeaders = ignoreHeaders;
        this.sheetNameToRead = sheetNameToRead;
        this.strictHeaderOrder = strictHeaderOrder;
        this.startReadRow = startReadRow;
    }

    public static class ReaderConfigurationBuilder {
        private InputStream inputStream;

        private boolean readLastActiveSheet;

        private boolean ignoreHeaders;

        private String sheetNameToRead;

        private boolean strictHeaderOrder;

        private int startReadRow;
        public ReaderConfigurationBuilder() {
        }

         public ReaderConfigurationBuilder inputStream(InputStream fileInputReader){
            this.inputStream=fileInputReader;
            return this;
        }

        public ReaderConfigurationBuilder setReadLastActiveSheet(boolean readLastActiveSheet) {
            this.readLastActiveSheet = readLastActiveSheet;
            return this;
        }

        public ReaderConfigurationBuilder setIgnoreHeaders(boolean ignoreHeaders) {
            this.ignoreHeaders = ignoreHeaders;
            return this;
        }

        public ReaderConfigurationBuilder setSheetNameToRead(String sheetNameToRead) {
            this.sheetNameToRead = sheetNameToRead;
            return this;
        }

        public ReaderConfigurationBuilder setStrictHeaderOrder(boolean strictHeaderOrder) {
            this.strictHeaderOrder = strictHeaderOrder;
            return this;
        }

        public ReaderConfigurationBuilder setStartReadRow(int startReadRow) {
            this.startReadRow = startReadRow;
            return this;
        }

        public ReaderConfiguration build(){
            return new ReaderConfiguration(this.inputStream,
                    this.readLastActiveSheet,
                    this.ignoreHeaders,
                    this.sheetNameToRead,
                    this.strictHeaderOrder,
                    this.startReadRow);
        }

    }
}
