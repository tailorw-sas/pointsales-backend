package com.kynsof.identity.application.command.walletTransaction.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateWalletTransactionRequest {
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private  byte [] image;
    private String ruc;
    private String address;;
    private UUID geographicLocation;

}
