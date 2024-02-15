package com.kynsof.share.core.application;

public final class CitizenAccessCodes {

    public static final String VIRTUAL_AGENCY_ACCOUNT_ACCESS = "VirtualAgencyAccount:ClientAccess";

    public static final String BIRTH_CERTIFICATE_ACCESS = "BirthCertificate:ClientAccess";

    public static final String MARRIAGE_CERTIFICATE_ACCESS = "MarriageCertificate:ClientAccess";

    public static final String DEATH_CERTIFICATE_ACCESS = "DeathCertificate:ClientAccess";

    public static final String UNION_CERTIFICATE_ACCESS = "UnionCertificate:ClientAccess";

    public static final String IDENTITY_CIVIL_STATUS_CERTIFICATE_ACCESS = "IdentityCivilStatusCertificate:ClientAccess";

    public static final String BIRTH_REGISTRATION_CERTIFICATE_ACCESS = "BirthRegistrationCertificate:ClientAccess";

    public static final String MARRIAGE_REGISTRATION_CERTIFICATE_ACCESS = "MarriageRegistrationCertificate:ClientAccess";

    public static final String DEATH_REGISTRATION_CERTIFICATE_ACCESS = "DeathRegistrationCertificate:ClientAccess";

    public static final String REGISTRY_ACT_OF_UNION_ACCESS = "RegistryActOfFactoUnion:ClientAccess";

    public static final String CHANGE_NAME_ACCESS = "NameChange:ClientAccess";

    public static final String DOCUMENT_VALIDATION_ACCESS = "DocumentValidation:ClientAccess";

    public static final String MARRIAGE_CELEBRATION_ACCESS = "MarriageCelebration:ClientAccess";

    public static final String PAYMENT_OF_NOTARIAL_ACT_ACCESS = "PaymentOfNotarialAct:ClientAccess";

    public static final String PAYMENT_OF_DOCUMENT_VALIDATION_ACCESS = "PaymentOfDocumentValidation:ClientAccess";

    public static final String BIRTH_REGISTRATION_ABROAD_ACCESS = "BirthRegistrationAbroad:ClientAccess";

    public static final String RESIDENCY_REGISTRATION_ACCESS = "ResidencyRegistration:ClientAccess";

    public static final String DUPLICATEID_ACCESS = "IdentificationDuplicate:ClientAccess";

    private CitizenAccessCodes() {
        throw new IllegalStateException("Utility class for access code representation");
    }
}
