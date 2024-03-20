package com.kynsof.share.core.domain.exception;

import jakarta.annotation.Nullable;

public enum DomainErrorMessage implements IDomainErrorMessage {
    PATIENTS_NOT_FOUND(601, Series.DOMAIN_ERROR, "Patients not found."),
    QUALIFICATION_NOT_FOUND(602, Series.DOMAIN_ERROR, "Qualification not found."),
    QUALIFICATION_OR_ID_NULL(603, Series.DOMAIN_ERROR, "Qualification DTO or ID cannot be null."),
    BUSINESS_NOT_FOUND(604, Series.DOMAIN_ERROR, "Business not found."),
    BUSINESS_OR_ID_NULL(605, Series.DOMAIN_ERROR, "Business DTO or ID cannot be null."),
    EXISTS_SCHEDULE_SOME_DATE_WHOSE_TIME_RANGE(606, Series.DOMAIN_ERROR, "There exists a schedule on the same date, whose time range coincides at some moment with what you want to create."),
    EXISTS_SCHEDULE_WITH_DATE_STARTTIME_ENDTIME(607, Series.DOMAIN_ERROR, "There exists a schedule with the same date, start time, and end time."),
    SCHEDULE_NOT_FOUND(608, Series.DOMAIN_ERROR, "Schedule not found."),
    SCHEDULE_CANNOT_BE_EQUALS_STARTTIME_ENDTIME(609, Series.DOMAIN_ERROR, "The start time and end time cannot be equal."),
    SCHEDULE_DATE_LESS_THAN_CURRENT_DATE(610, Series.DOMAIN_ERROR, "The provided date is less than the current date."),
    SCHEDULE_INITIAL_TIME_IS_PASSED(611, Series.DOMAIN_ERROR, "The initial time has passed."),
    SCHEDULE_END_TIME_IS_LESS_THAN(612, Series.DOMAIN_ERROR, "The provided end time is less than the start time."),
    SCHEDULE_EXISTS_SOME_TIME_STARTTIME_EDNTIME(613, Series.DOMAIN_ERROR, "There exists a schedule with the same date, start time, and end time."),
    RESOURCE_NOT_FOUND(614, Series.DOMAIN_ERROR, "Resource not found."),
    RECEIPT_NOT_FOUND(615, Series.DOMAIN_ERROR, "Receipt not found."),
    STATUS_NOT_ACCEPTED(616, Series.DOMAIN_ERROR, "Status not accepted, the appointment was attended."),
    SCHEDULE_IS_NOT_AVAIBLE(617, Series.DOMAIN_ERROR, "The selected schedule is not available."),
    COLUMN_UNIQUE(618, Series.DOMAIN_ERROR, "Duplicate key value violates unique constraint."),
    QUALIFICATION_DESCRIPTION_NOT_NULL(619, Series.DOMAIN_ERROR, "Qualification description not null!"),
    QUALIFICATION_DESCRIPTION_UNIQUE(620, Series.DOMAIN_ERROR, "Qualification description unique!"),
    PERMISSION_NOT_FOUND(621, Series.DOMAIN_ERROR, "Permission not found.");

    private static final DomainErrorMessage[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;

    private final Series series;

    private final String reasonPhrase;

    DomainErrorMessage(int value, Series series, String reasonPhrase) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public int value() {
        return this.value;
    }

    /**
     * Return the status series of this status code.
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

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }

    /**
     * Return the {@code ApiErrorStatus} enum constant with the specified
     * numeric value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the
     * specified numeric value
     */
    public static DomainErrorMessage valueOf(int statusCode) {
        DomainErrorMessage status = resolve(statusCode);
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
     * found
     */
    @Nullable
    public static DomainErrorMessage resolve(int statusCode) {
        // Use cached VALUES instead of values() to prevent array allocation.
        for (DomainErrorMessage status : VALUES) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

    /**
     * Enumeration of ApiError status series.
     * <p>
     * Retrievable via {@link DomainErrorMessage#series()}.
     */
    public enum Series {
        DOMAIN_ERROR
    }

}
