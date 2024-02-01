package com.kynsof.scheduled.domain;

public enum ERole {
	ROLE_USER,
    ROLE_ADMIN;

    public static boolean isMensajeTypeRole(String role){
        try {
            ERole.valueOf(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
