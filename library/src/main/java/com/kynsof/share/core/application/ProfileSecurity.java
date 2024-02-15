package com.kynsof.share.core.application;

import java.util.Set;
import java.util.UUID;

public record ProfileSecurity(Long agencyCode, Set<String> access) {
}
