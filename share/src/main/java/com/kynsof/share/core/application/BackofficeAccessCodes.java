package com.kynsof.share.core.application;

public final class BackofficeAccessCodes {

    public static final String MARRIAGE_READ_EDIT_ACCESS = "Marriage:ReadEditAccess";

    public static final String MARRIAGE_DOCUMENTS_FULL_ACCESS = "MarriageDocuments:FullAccess";

    public static final String MARRIAGE_DOCUMENTS_CREATE_ACCESS = "MarriageDocuments:CreateAccess";

    public static final String MARRIAGE_DOCUMENTS_READ_EDIT_ACCESS = "MarriageDocuments:ReadEditAccess";

    public static final String DIGITAL_SIGNATURE_READ_ONLY_ACCESS = "DigitalSignature:ReadOnlyAccess";

    public static final String DIGITAL_SIGNATURE_READ_EDIT_ACCESS = "DigitalSignature:ReadEditAccess";

    public static final String DIGITAL_SIGNATURE_FULL_ACCESS = "DigitalSignature:FullAccess";

    public static final String CHANGENAME_CREATE_ACCESS = "NameChange:CreateAccess";

    public static final String CHANGENAME_READ_EDIT_ACCESS = "NameChange:ReadEditAccess";

    public static final String CHANGENAME_DELETE_ACCESS = "NameChange:DeleteAccess";

    public static final String CHANGENAME_READ_ONLY_ACCESS = "NameChange:ReadOnlyAccess";

    public static final String CHANGENAME_FULL_ACCESS = "NameChange:FullAccess";

    public static final String DUPLICATEID_CREATE_ACCESS = "IdentificationDuplicate:CreateAccess";

    public static final String DUPLICATEID_READ_EDIT_ACCESS = "IdentificationDuplicate:ReadEditAccess";

    public static final String DUPLICATEID_DELETE_ACCESS = "IdentificationDuplicate:DeleteAccess";

    public static final String DUPLICATEID_READ_ONLY_ACCESS = "IdentificationDuplicate:ReadOnlyAccess";

    public static final String DUPLICATEID_FULL_ACCESS = "IdentificationDuplicate:FullAccess";

    public static final String CITIZENSHIP_CREATE_ACCESS = "BirthRegistrationAbroad:CreateAccess";

    public static final String CITIZENSHIP_READ_EDIT_ACCESS = "BirthRegistrationAbroad:ReadEditAccess";

    public static final String CITIZENSHIP_DELETE_ACCESS = "BirthRegistrationAbroad:DeleteAccess";

    public static final String CITIZENSHIP_READ_ONLY_ACCESS  = "BirthRegistrationAbroad:ReadOnlyAccess";

    public static final String CITIZENSHIP_FULL_ACCESS = "BirthRegistrationAbroad:FullAccess";

    private BackofficeAccessCodes() {
        throw new IllegalStateException("Utility class for access code representation");
    }
}
