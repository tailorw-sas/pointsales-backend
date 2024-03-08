package com.kynsoft.notification.shared.infrastructure.exceptions;


import io.micrometer.common.lang.Nullable;

public enum ApiErrorStatus implements ApiErrorStatusCode {

    // --- 6xx Domain Error ---

    /**
     * {@code 601 User First Name Required}
     */
    USER_FIRST_NAME_REQUIRED(601, Series.DOMAIN_ERROR, "User First Name Required"),
    /**
     * {@code 602 Network Authentication Required}.
     */
    USER_LAST_NAME_REQUIRED(602, Series.DOMAIN_ERROR, "User Last Name Required"),
    /**
     * {@code 603 User Identification Required}
     */
    USER_IDENTIFICATION_REQUIRED(603, Series.DOMAIN_ERROR, "User Identification Required"),
    /**
     * {@code 604 User Identification Unique}
     */
    USER_IDENTIFICATION_UNIQUE(604, Series.DOMAIN_ERROR, "User Identification Must Be Unique"),
    /**
     * {@code 605 User Mail Required}
     */
    USER_MAIL_REQUIRED(605, Series.DOMAIN_ERROR, "User Mail Required"),
    /**
     * {@code 606 User Mail Unique}
     */
    USER_MAIL_UNIQUE(606, Series.DOMAIN_ERROR, "User Mail Must Be Unique"),
    /**
     * {@code 607 User Password Required}
     */
    USER_PASSWORD_REQUIRED(607, Series.DOMAIN_ERROR, "User Password Required"),
    /**
     * {@code 608 User Password Strong}
     */
    USER_PASSWORD_STRONG(608, Series.DOMAIN_ERROR, "User Password Must Be Strong"),
    /**
     * {@code 609 User Type Required}
     */
    USER_TYPE_REQUIRED(609, Series.DOMAIN_ERROR, "User Type Required"),
    /**
     * {@code 610 User State Required}
     */
    USER_STATE_REQUIRED(610, Series.DOMAIN_ERROR, "User State Required"),
    /**
     * {@code 611 User Profiles Required}
     */
    USER_PROFILES_REQUIRED(611, Series.DOMAIN_ERROR, "User Profiles Required"),
    /**
     * {@code 612 Access Name Unique}
     */
    ACCESS_NAME_UNIQUE(612, Series.DOMAIN_ERROR, "Access Name Must Be Unique"),
    /**
     * {@code 613 Access Name Required}
     */
    ACCESS_NAME_REQUIRED(613, Series.DOMAIN_ERROR, "Access Name Required"),
    /**
     * {@code 614 Access Code Required}
     */
    ACCESS_CODE_REQUIRED(614, Series.DOMAIN_ERROR, "Access Code Required"),
    /**
     * {@code 615 Access Code Unique}
     */
    ACCESS_CODE_UNIQUE(615, Series.DOMAIN_ERROR, "Access Code Must Be Unique"),
    /**
     * {@code 616 Access Description Required}
     */
    ACCESS_DESCRIPTION_REQUIRED(616, Series.DOMAIN_ERROR, "Access Description Required"),
    /**
     * {@code 617 Agency Name Required}
     */
    AGENCY_NAME_REQUIRED(617, Series.DOMAIN_ERROR, "Agency Name Required"),
    /**
     * {@code 618 Agency Name Unique}
     */
    AGENCY_NAME_UNIQUE(618, Series.DOMAIN_ERROR, "Agency Name Must Be Unique"),
    /**
     * {@code 619 Agency Country Required}
     */
    AGENCY_COUNTRY_REQUIRED(619, Series.DOMAIN_ERROR, "Agency Country Required"),
    /**
     * {@code 620 Agency Province Required}
     */
    AGENCY_PROVINCE_REQUIRED(620, Series.DOMAIN_ERROR, "Agency Province Required"),
    /**
     * {@code 621 Agency Canton Required}
     */
    AGENCY_CANTON_REQUIRED(621, Series.DOMAIN_ERROR, "Agency Canton Required"),
    /**
     * {@code 622 Agency Parish Required}
     */
    AGENCY_PARISH_REQUIRED(622, Series.DOMAIN_ERROR, "Agency Parish Required"),
    /**
     * {@code 623 Agency City Required}
     */
    AGENCY_CITY_REQUIRED(623, Series.DOMAIN_ERROR, "Agency City Required"),
    /**
     * {@code 624 Agency State Required}
     */
    AGENCY_STATE_REQUIRED(624, Series.DOMAIN_ERROR, "Agency State Required"),
    /**
     * {@code 625 Application Name Required}
     */
    APPLICATION_NAME_REQUIRED(625, Series.DOMAIN_ERROR, "Application Name Required"),
    /**
     * {@code 626 Application Name Unique}
     */
    APPLICATION_NAME_UNIQUE(626, Series.DOMAIN_ERROR, "Application Name Unique"),
    /**
     * {@code 627 Application State Required}
     */
    APPLICATION_STATE_REQUIRED(627, Series.DOMAIN_ERROR, "Application State Required"),
    /**
     * {@code 628 Application Access Required}
     */
    APPLICATION_ACCESS_REQUIRED(628, Series.DOMAIN_ERROR, "Application Access Required"),
    /**
     * {@code 629 Profile Name Required}
     */
    PROFILE_NAME_REQUIRED(629, Series.DOMAIN_ERROR, "Profile Name Required"),
    /**
     * {@code 630 Profile Agency Required}
     */
    PROFILE_AGENCY_REQUIRED(630, Series.DOMAIN_ERROR, "Profile Agency Required"),
    /**
     * {@code 631 Profile State Required}
     */
    PROFILE_STATE_REQUIRED(631, Series.DOMAIN_ERROR, "Profile State Required"),
    /**
     * {@code 632 Profile Access Required}
     */
    PROFILE_ACCESS_REQUIRED(632, Series.DOMAIN_ERROR, "Profile Access Required"),
    /**
     * {@code 633 Agency Region Required}
     */
    AGENCY_REGION_REQUIRED(633, Series.DOMAIN_ERROR, "Agency Region Required"),
    /**
     * {@code 634 Residential Canton Required}
     */
    RESIDENTIAL_CANTON_REQUIRED(634, Series.DOMAIN_ERROR, "Residential Canton Required"),
    /**
     * {@code 634 Residential Province Required}
     */
    RESIDENTIAL_PROVINCE_REQUIRED(635, Series.DOMAIN_ERROR, "Residential Province Required"),
    /**
     * {@code 634 Residential Parroquia Required}
     */
    RESIDENTIAL_PARROQUIA_REQUIRED(636, Series.DOMAIN_ERROR, "Residential Parroquia Required"),
    /**
     * {@code 634 Residential Nui Required}
     */
    RESIDENTIAL_NUI_REQUIRED(637, Series.DOMAIN_ERROR, "Residential Nui Required"),
    /**
     * {@code 634 Residential Main Street Required}
     */
    RESIDENTIAL_MAIN_STREET_REQUIRED(638, Series.DOMAIN_ERROR, "Residential Main Street Required"),
    /**
     * {@code 634 Residential Email Required}
     */
    RESIDENTIAL_EMAIL_REQUIRED(639, Series.DOMAIN_ERROR, "Residential Email Required"),
    /**
     * {@code 634 Residential Date Birth Required}
     */
    RESIDENTIAL_DATE_BIRTH_REQUIRED(640, Series.DOMAIN_ERROR, "Residential Date Birth Required"),
    /**
     * {@code 634 Residential CellPhone Required}
     */
    RESIDENTIAL_CELL_PHONE_REQUIRED(641, Series.DOMAIN_ERROR, "Residential CellPhone Required"),
    /**
     * {@code 634 Residential CellPhone Unique}
     */
    RESIDENTIAL_CELL_PHONE_UNIQUE(642, Series.DOMAIN_ERROR, "Residential CellPhone Unique"),
    /**
     * {@code 634 Residential Email Unique}
     */
    RESIDENTIAL_EMAIL_UNIQUE(643, Series.DOMAIN_ERROR, "Residential Email Unique"),
    /**
     * {@code 634 Residential Nui Unique}
     */
    RESIDENTIAL_NUI_UNIQUE(644, Series.DOMAIN_ERROR, "Residential Nui Unique"),
    /**
     * {@code 645 Procedure Agency Name Required}
     */
    PROCEDURE_AGENCY_REQUIRED(645, Series.DOMAIN_ERROR, "Procedure Agency Name Required"),
    /**
     * {@code 646 Procedure City Name Required}
     */
    PROCEDURE_CITY_REQUIRED(646, Series.DOMAIN_ERROR, "Procedure City Required"),
    /**
     * {@code 647 Procedure Code Required}
     */
    PROCEDURE_CODE_REQUIRED(647, Series.DOMAIN_ERROR, "Procedure Code Required"),
    /**
     * {@code 648 Procedure description Required}
     */
    PROCEDURE_DESCRIPTION_REQUIRED(648, Series.DOMAIN_ERROR, "Procedure description Required"),
    /**
     * {@code 649 Procedure price Required}
     */
    PROCEDURE_PRICE_REQUIRED(649, Series.DOMAIN_ERROR, "Procedure price Required"),
    /**
     * {@code 650 Procedure type Required}
     */
    PROCEDURE_TYPE_REQUIRED(650, Series.DOMAIN_ERROR, "Procedure type Required"),
    /**
     * {@code 651 Type Service percent Required}
     */
    TYPE_SERVICE_PERCENT_REQUIRED(651, Series.DOMAIN_ERROR, "Type Service percent Required"),
    /**
     * {@code 652 Type Service name Required}
     */
    TYPE_SERVICE_NAME_REQUIRED(652, Series.DOMAIN_ERROR, "Type Service name Required"),
    /**
     * {@code 653 Service agency Required}
     */
    SERVICE_AGENCY_REQUIRED(653, Series.DOMAIN_ERROR, "Service agency Required"),
    /**
     * {@code 654 Service city Required}
     */
    SERVICE_CITY_REQUIRED(654, Series.DOMAIN_ERROR, "Service city Required"),
    /**
     * {@code 655 Service Code Unique}
     */
    SERVICE_CODE_UNIQUE(655, Series.DOMAIN_ERROR, "Service Code Unique"),
    /**
     * {@code 656 Service description required}
     */
    SERVICE_DESCRIPTION_REQUIRED(656, Series.DOMAIN_ERROR, "Service province Required"),
    /**
     * {@code 657 Service province required}
     */
    SERVICE_PROVINCE_REQUIRED(657, Series.DOMAIN_ERROR, "Service province Required"),
    /**
     * {@code 658 Service Type required}
     */
    SERVICE_TYPE_REQUIRED(658, Series.DOMAIN_ERROR, "Service Type Required"),
    /**
     * {@code 658 Type Procedure name unique}
     */
    PROCEDURE_TYPE_NAME_UNIQUE(659, Series.DOMAIN_ERROR, "Type Procedure name unique"),
    /**
     * {@code 659 Type Procedure icon web required}
     */
    PROCEDURE_TYPE_ICON_WEB_REQUIRED(659, Series.DOMAIN_ERROR, "Type Procedure icon web required"),
    /**
     * {@code 660 Type Procedure icon movil required}
     */
    PROCEDURE_TYPE_ICON_MOVIL_REQUIRED(660, Series.DOMAIN_ERROR, "Type Procedure icon movil required"),
    /**
     * {@code 661 Type Procedure iva required}
     */
    PROCEDURE_TYPE_IVA_REQUIRED(661, Series.DOMAIN_ERROR, "Type Procedure iva required"),
    /**
     * {@code 662 Type Procedure discount required}
     */
    PROCEDURE_TYPE_DISCOUNT_REQUIRED(662, Series.DOMAIN_ERROR, "Type Procedure dicount required"),
    /**
     * {@code 663 Type Procedure price required}
     */
    PROCEDURE_TYPE_PRICE_REQUIRED(663, Series.DOMAIN_ERROR, "Type Procedure price required"),
    /**
     * {@code 800 Middleware_Mail: Failed to send email}
     */
    MIDDLEWARE_MAIL_FAILED(800, Series.DOMAIN_ERROR, "Failed to send email!"),
    /**
     * {@code 950 Request Content Type Wrong}
     */
    REQUEST_CONTENT_TYPE_WRONG(950, Series.DOMAIN_ERROR, "Request Content Type Wrong");

    private static final ApiErrorStatus[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;

    private final Series series;

    private final String reasonPhrase;

    ApiErrorStatus(int value, Series series, String reasonPhrase) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public int value() {
        return this.value;
    }

    /**
     * Return the ApiError status series of this status code.
     */
    public Series series() {
        return this.series;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public boolean is6xxDomainError() {
        return (series() == Series.DOMAIN_ERROR);
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }

    /**
     * Return the {@code ApiErrorStatus} enum constant with the specified numeric
     * value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the
     *                                  specified numeric value
     */
    public static ApiErrorStatus valueOf(int statusCode) {
        ApiErrorStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    /**
     * Resolve the given status code to an {@code ApiErrorStatus}, if possible.
     *
     * @param statusCode the ApiError status code (potentially non-standard)
     * @return the corresponding {@code ApiErrorStatus}, or {@code null} if not
     *         found
     */
    @Nullable
    public static ApiErrorStatus resolve(int statusCode) {
        // Use cached VALUES instead of values() to prevent array allocation.
        for (ApiErrorStatus status : VALUES) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

    /**
     * Enumeration of ApiError status series.
     * <p>
     * Retrievable via {@link ApiErrorStatus#series()}.
     */
    public enum Series {

        DOMAIN_ERROR(6);

        private final int value;

        Series(int value) {
            this.value = value;
        }

        /**
         * Return the integer value of this status series. Beginning from 6.
         */
        public int value() {
            return this.value;
        }

        /**
         * Return the {@code Series} enum constant for the supplied status code.
         *
         * @param statusCode the ApiError status code (potentially non-standard)
         * @return the {@code Series} enum constant for the supplied status code
         * @throws IllegalArgumentException if this enum has no corresponding constant
         */
        public static Series valueOf(int statusCode) {
            Series series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
            }
            return series;
        }

        /**
         * Resolve the given status code to an {@code ApiErrorStatus.Series}, if
         * possible.
         *
         * @param statusCode the ApiError status code (potentially non-standard)
         * @return the corresponding {@code Series}, or {@code null} if not found
         */
        @Nullable
        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            for (Series series : values()) {
                if (series.value == seriesCode) {
                    return series;
                }
            }
            return null;
        }
    }

}
