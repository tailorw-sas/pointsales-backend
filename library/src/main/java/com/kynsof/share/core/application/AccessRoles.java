package com.kynsof.share.core.application;

public final class AccessRoles {

    public static final String ADMIN_ROLE = "ADMIN";

    public static final String CITIZEN_ROLE = "CITIZEN";

    public static final String SPECIALIST_ROLE = "SPECIALIST";

    private AccessRoles() {
        throw new IllegalStateException("Utility class for roles representation");
    }
}
