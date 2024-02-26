package com.kynsof.share.core.domain.exception;

import jakarta.annotation.Nullable;


public enum DomainErrorMessage implements IDomainErrorMessage {

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
    PROFILE_EXITS(847, Series.DOMAIN_ERROR, "The Profile exist"),
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
     * {@code 659 Service Type icon movil required}
     */
    SERVICE_TYPE_ICON_MOVIL_REQUIRED(659, Series.DOMAIN_ERROR, "Service Type icon movil Required"),
    /**
     * {@code 659 Service Type icon web required}
     */
    SERVICE_TYPE_ICON_WEB_REQUIRED(660, Series.DOMAIN_ERROR, "Service Type icon web Required"),
    /**
     * {@code 661 Service Type Discount required}
     */
    SERVICE_TYPE_DISCOUNT_REQUIRED(661, Series.DOMAIN_ERROR, "Service Type Discount Required"),
    /**
     * {@code 662 Service Type Iva required}
     */
    SERVICE_TYPE_IVA_REQUIRED(662, Series.DOMAIN_ERROR, "Service Type iva Required"),
    /**
     * {@code 663 Service Type Price required}
     */
    SERVICE_TYPE_PRICE_REQUIRED(663, Series.DOMAIN_ERROR, "Service Type price Required"),
    /**
     * {@code 664 Service Type Name required}
     */
    SERVICE_TYPE_NAME_UNIQUE(664, Series.DOMAIN_ERROR, "Service Type name Unique"),

    /**
     * {@code 658 Type Procedure name unique}
     */
    PROCEDURE_TYPE_NAME_UNIQUE(665, Series.DOMAIN_ERROR, "Type Procedure name unique"),
    /**
     * {@code 659 Type Procedure icon web required}
     */
    PROCEDURE_TYPE_ICON_WEB_REQUIRED(667, Series.DOMAIN_ERROR, "Type Procedure icon web required"),
    /**
     * {@code 660 Type Procedure icon movil required}
     */
    PROCEDURE_TYPE_ICON_MOVIL_REQUIRED(668, Series.DOMAIN_ERROR, "Type Procedure icon movil required"),

    /**
     * {@code 669 "Marriage power of attorney required}
     */
    MARRIAGE_POWER_OF_ATTORNEY_REQUIRED(669, Series.DOMAIN_ERROR, "Marriage Power Of Attorney Document Required"),

    /**
     * {@code 670 Marriage reviews exceeded}
     */
    MARRIAGE_REVIEWS_NUMBER_EXCEEDED(670, Series.DOMAIN_ERROR, "Marriage Reviews Exceeded"),

    /**
     * {@code 671 Document rejection id is required}
     */
    MARRIAGE_DOCUMENT_REJECTION_ID_REQUIRED(671, Series.DOMAIN_ERROR, "Document Rejection Id Required"),

    /**
     * {@code 672 Marriage state must be REJECTED, APPROVED or FINALIZED}
     */
    MARRIAGE_CHANGE_STATE_REQUIRED(672, Series.DOMAIN_ERROR, "Marriage State Must Be REJECTED, APPROVED or FINALIZED"),

    /**
     * {@code 673 Marriage id is required}
     */
    MARRIAGE_REVIEW_BY_OPERATOR_REQUIRED(673, Series.DOMAIN_ERROR, "Marriage Review By Operator Required"),

    /**
     * {@code 674 Marriage id is required}
     */
    MARRIAGE_APPROVED_DOCUMENT_REQUIRED(674, Series.DOMAIN_ERROR, "Marriage With Approved Documents Required"),

    /**
     * {@code 675 Marriage outside info required}
     */
    MARRIAGE_OUTSIDE_INFO_REQUIRED(675, Series.DOMAIN_ERROR, "Marriage outside Info Required"),

    /**
     * {@code 676 Marital status required}
     */
    MARRIAGE_DOCUMENTATION_REQUEST_MARITAL_STATUS_REQUIRED(676, Series.DOMAIN_ERROR, "Marital Status Required"),

    /**
     * {@code 677 Document name required}
     */
    MARRIAGE_DOCUMENTATION_REQUEST_DOCUMENT_NAME_REQUIRED(677, Series.DOMAIN_ERROR, "Document Name Required"),

    /**
     * {@code 678 Document rejection name  required}
     */
    MARRIAGE_DOCUMENT_REJECTION_NAME_REQUIRED(678, Series.DOMAIN_ERROR, "Document Rejection Name Required"),

    /**
     * {@code 679 Marriage identity card required}
     */
    MARRIAGE_IDENTITY_CARD_REQUIRED(679, Series.DOMAIN_ERROR, "Marriage Identity Card Required"),

    CHANGE_NAME_REQUEST_NAME_INVALID(680, Series.DOMAIN_ERROR, "Name invalid"),

    CHANGE_NAME_REQUEST_NAME_SIZE_INVALID(681, Series.DOMAIN_ERROR, "Size of name invalid"),

    CHANGE_NAME_REQUEST_UPDATE_INACTIVE(682, Series.DOMAIN_ERROR, "Forbidden to update an inactive change name"),

    CHANGE_NAME_REQUEST_EXIST(683, Series.DOMAIN_ERROR, "There is a previous name change"),

    STATE_INVALID(684, Series.DOMAIN_ERROR, "Invalid state"),

    PAYMENT_REQUIRED(685, Series.DOMAIN_ERROR, "Payment required"),

    USER_ADULT_REQUIRED(686, Series.DOMAIN_ERROR, " user adult required"),

    CHANGE_NAME_RESOURCE_NOT_FOUND(687, Series.DOMAIN_ERROR, "Change name resource not found"),

    CHANGE_NAME_DOCUMENT_RESOURCE_NOT_FOUND(688, Series.DOMAIN_ERROR, "Change name document resource not found"),

    /**
     * {@code 689 Type Procedure iva required}
     */
    PROCEDURE_TYPE_IVA_REQUIRED(689, Series.DOMAIN_ERROR, "Type Procedure iva required"),
    /**
     * {@code 689 Type Procedure discount required}
     */
    PROCEDURE_TYPE_DISCOUNT_REQUIRED(690, Series.DOMAIN_ERROR, "Type Procedure discount required"),
    /**
     * {@code 691 Type Procedure price required}
     */
    PROCEDURE_TYPE_PRICE_REQUIRED(691, Series.DOMAIN_ERROR, "Type Procedure price required"),

    /**
     * {@code 692 Type procedure type Invalid}
     */
    TYPE_PROCEDURE_TYPE(692, Series.DOMAIN_ERROR, "Type Procedure type Invalid"),

    /**
     * {@code 693 Marriage File Must Be One Of ZIP, PDF or RAR}
     */
    MARRIAGE_FILE_TYPE_REQUIRED(693, Series.DOMAIN_ERROR, "Marriage File Must Be Pdf, Zip Or Rar"),

    /**
     * {@code 694 Marriage Witness Required}
     */
    MARRIAGE_WITNESS_REQUIRED(694, Series.DOMAIN_ERROR, "Marriage Witness Required"),

    /**
     * {@code 695 Marriage Receipt Code Required}
     */
    MARRIAGE_RECEIPT_CODE(695, Series.DOMAIN_ERROR, "Marriage Receipt Code Required"),

    /**
     * {@code 696 Marriage Receipt Payment Type Required}
     */
    MARRIAGE_RECEIPT_PAYMENT_TYPE(696, Series.DOMAIN_ERROR, "Marriage Receipt Payment Type Required"),

    /**
     * {@code 697 Marriage With Rejected State Can't Be Changed}
     */
    MARRIAGE_WITH_REJECTED_STATE_CANT_BE_CHANGED(697, Series.DOMAIN_ERROR, "Marriage With Rejected State Can't Be Changed"),

    /**
     * {@code 698 Marriage With Finalized State Can't Be Changed}
     */
    MARRIAGE_WITH_FINALIZED_STATE_CANT_BE_CHANGED(698, Series.DOMAIN_ERROR, "Marriage With Finalized State Can't Be Changed"),

    /**
     * {@code 699 Marriage With Approved State Can't Be Changed}
     */
    MARRIAGE_WITH_APPROVED_STATE_CANT_BE_CHANGED(699, Series.DOMAIN_ERROR, "Marriage With Approved State Can't Be Changed"),

    /**
     * {@code 700 Registration Token Not Found}
     */
    REGISTRATION_TOKEN_NOT_FOUND(700, Series.DOMAIN_ERROR, "Registration Token Not Found"),
    /**
     * {@code 701 Registration Token Expired}
     */
    REGISTRATION_TOKEN_EXPIRED(701, Series.DOMAIN_ERROR, "Registration Token Expired"),
    /**
     * {@code 702 Registration Token Max Attempts Reached}
     */
    REGISTRATION_TOKEN_MAX_ATTEMPTS_REACHED(702, Series.DOMAIN_ERROR, "Registration Token Max Attempts Reached"),
    /**
     * {@code 703 User Already Registered}
     */
    USER_ALREADY_REGISTERED(703, Series.DOMAIN_ERROR, "User Already Registered"),

    /**
     * {@code 704 User NUI Format Invalid}
     */
    USER_NUI_FORMAT_INVALID(704, Series.DOMAIN_ERROR, "User NUI Format Invalid"),
    /**
     * {@code 705 Type Service Type Invalid}
     */
    TYPE_SERVICE_TYPE(705, Series.DOMAIN_ERROR, "Type Service Type Invalid"),
    /**
     * {@code 706 ServiceCertificationSimpleTypeFamily Invalid}
     */
    CERTIFICATION_SIMPLE_TYPE_FAMILY(706, Series.DOMAIN_ERROR, "Type Family Required"),
    /**
     * {@code 707 Invalid NUI Invalid}
     */
    CERTIFICATION_SIMPLE_TYPE_NUI_FAMILY(707, Series.DOMAIN_ERROR, "Invalid NUI"),
    /**
     * {@code 708 Invalid Type Service Invalid}
     */
    CERTIFICATION_SIMPLE_TYPE_SERVICE_NOT_ACEPTABLE(708, Series.DOMAIN_ERROR, "Invalid Type Service."),
    /**
     * {@code 709 Service Type Name required}
     */
    SERVICE_TYPE_TYPE_UNIQUE(709, Series.DOMAIN_ERROR, "Service Type, type Unique"),
    /**
     * {@code 710 Type Certification Simple not Aceptable}
     */
    CERTIFICATION_SIMPLE_TYPE_NOT_ACEPTABLE(710, Series.DOMAIN_ERROR, "Type Certification Simple not Acceptable"),
    /**
     * {@code 711 Type Act Registration not Aceptable}
     */
    ACT_REGISTRATION_TYPE_NOT_ACEPTABLE(711, Series.DOMAIN_ERROR, "Type Act Registration not Acceptable"),

    SERVICE_PAYMENT_REQUIRED(712, Series.DOMAIN_ERROR, "Payment required"),

    /**
     * {@code 713 Telephone Operator Name Required}
     */
    TELEPHONE_OPERATOR_NAME_REQUIRED(713, Series.DOMAIN_ERROR, "Telephone Operator Name Required"),
    /**
     * {@code 714 Telephone Operator Name Unique}
     */
    TELEPHONE_OPERATOR_NAME_UNIQUE(714, Series.DOMAIN_ERROR, "User NUI Format Unique"),
    /**
     * {@code 715 User must be registred}
     */
    USER_MUST_BE_REGISTERED(715, Series.DOMAIN_ERROR, "User must be registred"),
    /**
     * {@code 716 Token must be elegible to refresh}
     */
    JWT_TOKEN_MUST_BE_ELEGIBLE_TO_REFRESH(716, Series.DOMAIN_ERROR, "Token must be elegible to refresh"),
    /**
     * {@code 717 Geographic Location Topology Violation}
     */
    GEOGRAPHIC_LOCATION_TOPOLOGY_VIOLATION(717, Series.DOMAIN_ERROR, "Geographic Location Topology Violation"),
    /**
     * {@code 718 Geographic Location Name Required}
     */
    GEOGRAPHIC_LOCATION_NAME_REQUIRED(718, Series.DOMAIN_ERROR, "Geographic Location Name Required"),
    /**
     * {@code 719 Geographic Location Type Required}
     */
    GEOGRAPHIC_LOCATION_TYPE_REQUIRED(719, Series.DOMAIN_ERROR, "Geographic Location Type Required"),

    CITIZENSHIP_DOCUMENT_REQUIRED(720, Series.DOMAIN_ERROR, "Citizenship documentation required not found"),

    CITIZENSHIP_RESOURCE_NOT_FOUND(721, Series.DOMAIN_ERROR, "Citizenship resource not found"),

    OPERATOR_ASSIGNED(722, Series.DOMAIN_ERROR, "Request already has an operator assigned"),

    CITIZENSHIP_REGISTER_USER_REQUIRED(723, Series.DOMAIN_ERROR, "Citizenship user information required"),

    CITIZENSHIP_DOCUMENT_REJECT_COMMENT_REQUIRED(724, Series.DOMAIN_ERROR, "Citizenship documentation reject comment required"),

    CITIZENSHIP_STATE_INVALID(725, Series.DOMAIN_ERROR, "Invalid state"),

    CITIZENSHIP_DOCUMENT_TOUPDATE_REQUIRED(726, Series.DOMAIN_ERROR, "Not changes in citizenship documentation"),

    CITIZENSHIP_DOCUMENT_RESOURCE_NOT_FOUND(727, Series.DOMAIN_ERROR, "Citizenship document resource not found"),
    /**
     * {@code 728 User Not Found}
     */
    USER_NOT_FOUND(728, Series.DOMAIN_ERROR, "User Not Found"),
    /**
     * {@code 729 User Old Password Not Match}
     */
    USER_OLD_PASSWORD_NOT_MATCH(729, Series.DOMAIN_ERROR, "User Old Password Not Match"),

    /**
     * {@code 730 Residential Electrical Code Required}
     */
    RESIDENTIAL_ELECTRICAL_CODE_REQUIRED(730, Series.DOMAIN_ERROR, "Residential Electrical Code Required"),

    /**
     * {@code 731 Digital signature state Required}
     */
    DIGITAL_SIGNATURE_STATE_REQUIRED(731, Series.DOMAIN_ERROR, "Digital Signature State Required"),

    /**
     * {@code 732 Digital signature state Required}
     */
    SOLICITUDE_NUT_REQUIRED(732, Series.DOMAIN_ERROR, "Solicitude NUT Required"),
    /**
     * {@code 733 Type Service name not null}
     */
    SERVICE_TYPE_NAME_NOT_NULL(733, Series.DOMAIN_ERROR, "Type Service name not null"),
    /**
     * {@code 734 Invalid enum type: SERVICE or PROCEDURE}
     */
    TYPE_ENUM_SERVICE_TYPE(734, Series.DOMAIN_ERROR, "Invalid enum type: SERVICE or PROCEDURE"),

    MARRIAGE_UPDATES_BY_CITIZEN_REQUIRED(735, Series.DOMAIN_ERROR, "Marriage Updates By Citizen Required"),

    MARRIAGE_WITNESSES_CANT_BE_THE_SAME_PERSON(736, Series.DOMAIN_ERROR, "Marriage Witnesses Can't Be The Same Person"),

    MARRIAGE_PARTNERS_CANT_BE_THE_SAME_PERSON(737, Series.DOMAIN_ERROR, "Marriage Partners Can't Be The Same Person"),

    OPERATOR_UNASSIGNED(738, Series.DOMAIN_ERROR, "Request dont have operator assigned"),

    PARENT_RELATIONSHIP_REQUIRED(739, Series.DOMAIN_ERROR, "User does not register association with son/daughter"),

    RECEIPT_OWNER_UNIQUE(740, Series.DOMAIN_ERROR, "Only one receipt by owner"),

    RECEIPT_MUST_BE_UNIQUE(741, Series.DOMAIN_ERROR, "Receipt must be unique"),

    RECEIPT_MUST_BE_IN_TIME(742, Series.DOMAIN_ERROR, "Receipt must be in time range"),

    STOCK_IS_SOLD_OUT(743, Series.DOMAIN_ERROR, "Schedule stock is sold out"),

    STOCK_POSITIVE(744, Series.DOMAIN_ERROR, "Stock of receipts must be a positive valor"),

    START_TIME_AFTER_END_TIME(745, Series.DOMAIN_ERROR, "Start time can't be after ending time"),

    HOLIDAY_MUST_BE_UNIQUE(746, Series.DOMAIN_ERROR, "Only one holiday registry per day"),

    NATIONAL_HOLIDAY_CAN_NOT_HAVE_AGENCY(747, Series.DOMAIN_ERROR,"A national holiday can not be associated with an specific agency"),

    LOCAL_HOLIDAY_MUST_HAVE_AGENCY(748, Series.DOMAIN_ERROR,"A local holiday must be associated with an agency"),

    CATEGORY_MUST_BE_UNIQUE(749, Series.DOMAIN_ERROR,"The category mus be unique"),

    CATEGORY_CIRCULAR_REFERENCE_DETECTED(750, Series.DOMAIN_ERROR,"A circular reference was detected in category hierarchy"),

    TIME_RANGE_IN_COLLISION(751, Series.DOMAIN_ERROR,"There is a schedule in this time range"),

    SCHEDULE_MUST_BE_SPECIAL(752, Series.DOMAIN_ERROR, "Schedule must be special"),

    SCHEDULE_RANGE_TIME_OF_ONE_HOUR_REQUIRED(753, Series.DOMAIN_ERROR, "Schedule Range Time Of One Hour Required"),

    MARRIAGE_PARTNER_CONADIS_REQUIRED(754, Series.DOMAIN_ERROR, "Marriage Partner CONADIS Is Required"),

    SCHEDULE_DATE_REQUIRED(755, Series.DOMAIN_ERROR, "Schedule Date Is Required"),

    SCHEDULE_STATUS_REQUIRED(756, Series.DOMAIN_ERROR, "Schedule Status Is Required"),

    DUPLICATE_ID_RESOURCE_NOT_FOUND(757, Series.DOMAIN_ERROR, "Duplicate identity card name resource not found"),

    PAYMENT_EXPIRED(758, Series.DOMAIN_ERROR, "Payment expired"),

    RECEIPT_TICKET_INVALID(759, Series.DOMAIN_ERROR, "Invalid Receipt Ticket For The Given User"),

    SCHEDULE_WITH_RECEIPTS_CANT_BE_DELETED(760, Series.DOMAIN_ERROR, "Schedule With Associated Receipts Can't Be Deleted"),

    SCHEDULE_WITH_RECEIPTS_CANT_BE_CHANGED(761, Series.DOMAIN_ERROR, "Schedule With Associated Receipts. Only The Stock Can Be Increased"),

    FAILED_CONNECT_SURI_OR_NUI_NOT_EXIST(762, Series.DOMAIN_ERROR, "Failed response, to connect the suri server or nui not exist!"),

    FAILED_CONNECT_SURI(763, Series.DOMAIN_ERROR, "Failed to connect the suri server!"),

    FAILED_INVALID_DATE_FORMAT(764, Series.DOMAIN_ERROR, "Invalid date format!"),

    CITIZEN_RELATIONSHIP_REQUIRED(765, Series.DOMAIN_ERROR, "You are not authorized to generate the citizen's certificate. Please contact the system administrator at enlinea@registrocivil.gob.ec."),

    AUTHENTICATED_USER_REQUIRED(766, Series.DOMAIN_ERROR, "User must be authenticated"),

    MARRIAGE_INTERVIEW_DATE_REQUIRED(767, Series.DOMAIN_ERROR, "Marriage Interview Date Required"),

    MARRIAGE_INTERVIEW_TIME_REQUIRED(768, Series.DOMAIN_ERROR, "Marriage Interview Time Required"),

    MARRIAGE_INTERVIEW_LINK_REQUIRED(769, Series.DOMAIN_ERROR, "Marriage Interview Link Required"),

    MARRIAGE_PENDING_INTERVIEW_STATE_REQUIRED(770, Series.DOMAIN_ERROR, "Marriage Pending Interview State Required"),

    PASSWORD_RECOVERY_MAX_ATTEMPTS_REACHED(771, Series.DOMAIN_ERROR, "Password Recovery max attempts reached"),

    PASSWORD_RECOVERY_OTP_NOT_MATCH(772, Series.DOMAIN_ERROR, "Password Recovery OTP not match"),

    PASSWORD_RECOVERY_OTP_EXPIRED(773, Series.DOMAIN_ERROR, "OTP Expired"),

    PASSWORD_RECOVERY_USER_BLOCKED(774, Series.DOMAIN_ERROR, "User blocked"),

    INVALID_NUI(775, Series.DOMAIN_ERROR, "Invalid nui"),

    CITIZEN_ACCESS_REQUIRED(776, Series.DOMAIN_ERROR, "Citizen Validation Access Required"),

    CITIZEN_MARITAL_STATUS_REQUIRED(777, Series.DOMAIN_ERROR, "Citizen Marital Status Required"),

    BACKOFFICE_ACCESS_TYPE_REQUIRED(778, Series.DOMAIN_ERROR, "Backoffice Access Type Required"),

    CITIZEN_VALIDATION_ACCESS_ALREADY_EXISTS(779, Series.DOMAIN_ERROR, "Citizen Validation Already Exists With The Given Identity Code And Marital Status"),

    USER_REGISTERED(780, Series.DOMAIN_ERROR, "User has registered before"),

    USER_VERIFICATION_PENDING(781, Series.DOMAIN_ERROR, "User registration pending of validation"),

    USER_REGISTRATION_BLOCKED(782, Series.DOMAIN_ERROR, "User registration blocked"),

    GEOGRAPHIC_LOCATION_ID_UBICATION_UNIQUE(783, Series.DOMAIN_ERROR, "Geographic Location idUbication must be unique"),

    GEOGRAPHIC_LOCATION_ID_UBICATION_REQUIRED(784, Series.DOMAIN_ERROR, "Geographic Location idUbication Required"),

    AGENCY_ID_SURI_UNIQUE(785, Series.DOMAIN_ERROR, "ID Agency Suri must be unique"),

    AGENCY_ID_SURI_REQUIRED(786, Series.DOMAIN_ERROR, "ID Agency Suri Required"),

    USER_NOT_MATCH(787, Series.DOMAIN_ERROR, "User of OTP request not match with current user"),

    DUPLICATEID_REQUEST_IN_PROGRESS(788, Series.DOMAIN_ERROR, "There is a duplicate id in progress"),

    DUPLICATEID_REQUEST_INVALID(789, Series.DOMAIN_ERROR, "Cant print duplicate identity card"),

    DUPLICATEID_REQUEST_PENDING(847, Series.DOMAIN_ERROR, "No se puede imprimir el duplicado porque ya existe"),

    BILL_REQUEST_ERROR(790, Series.DOMAIN_ERROR, "Cant create bill"),

    CITIZEN_TYPE_REQUIRED(791, Series.DOMAIN_ERROR, "Citizen Type Required"),

    BILL_REQUEST_USER_NOT_FOUND(792, Series.DOMAIN_ERROR, "User does not exist in SIR"),

    FOREIGNER_VISA_REQUIRED(793, Series.DOMAIN_ERROR, "Visa required"),

    INVALID_VISA(794, Series.DOMAIN_ERROR, "Invalid visa number"),

    DIGITAL_SIGNATURE_RESOURCE_NOT_FOUND(795, Series.DOMAIN_ERROR, "Digital signature resource not found"),

    VIRTUAL_AGENCY_ACCESS_REQUIRED(796, Series.DOMAIN_ERROR, "User doesn't have access to the Virtual Agency"),

    SCHEDULE_TIME_REQUIRED(797, Series.DOMAIN_ERROR, "Schedule Time Is Required"),

    SCHEDULE_STOCK_REQUIRED(798, Series.DOMAIN_ERROR, "Schedule Stock Is Required"),

    SCHEDULE_EXCEL_INVALID(799, Series.DOMAIN_ERROR, "Excel Program Schedule Is Invalid"),
    
    PAYMENT_CODE_INVALID(800, Series.DOMAIN_ERROR, "Invalid payment code"),

    CITIZEN_MUST_BE_ADULT(801, Series.DOMAIN_ERROR, "Citizen must be an adult"),

    GRANDPARENT_RELATIONSHIP_REQUIRED(802, Series.DOMAIN_ERROR, "User does not register association with grandson/granddaughter"),

    MARRIAGE_PARTNERS_AND_WITNESSES_CANT_BE_THE_SAME_PERSON(803, Series.DOMAIN_ERROR, "Marriage Partners and witnesses can't Be The Same Person"),

    USER_CANT_BE_DEAD(804, Series.DOMAIN_ERROR, "User cant be dead"),

    SERVICE_CERTIFICATION_CITIZEN_CANT_BE_DEAD(805, Series.DOMAIN_ERROR, "The citizen does not have a death certificate because they are not deceased."),
    
    SERVICE_ACT_CITIZEN_CANT_BE_DEAD(806, Series.DOMAIN_ERROR, "The citizen does not have a death certificate because they are not deceased."),

    CITIZEN_MUST_NOT_BE_DEAD(807, Series.DOMAIN_ERROR, "The citizen must be alive"),

    DUPLICATEID_UNEXPECTED_ERROR(808, Series.DOMAIN_ERROR, "Unexpected error creating duplicate id suri request"),

    DUPLICATEID_PERSONAL_DATA_UPDATED_AFTER(809, Series.DOMAIN_ERROR, "There is an update of personal data after the date of issuance of your last identification card"),

    DUPLICATEID_LAST_IDENTITYCARD_SEDIP_REQUIRED(810, Series.DOMAIN_ERROR, "The latest identification card format has not been issued by SEDIP"),

    DUPLICATEID_IDENTITYCARD_NEXT_TO_EXPIRED(811, Series.DOMAIN_ERROR, "Your identification card is next to expire"),

    DUPLICATEID_CITIZEN_EXISTS_REQUIRED(812, Series.DOMAIN_ERROR, "No citizen found"),

    DUPLICATEID_CANT_BE_CHANNELLED(813, Series.DOMAIN_ERROR, "Your request must be channeled in person"),

    CERTIFICATION_SIMPLE_CANNOT_VALIDATE(814, Series.DOMAIN_ERROR, "Error validating the certificate"),

    SIMPLE_CERTIFICATE_DOES_NOT_EXIST(815, Series.DOMAIN_ERROR, "There are no data for the requested certificate."),

    ACT_REGISTRATION_NUI_INVALID(816, Series.DOMAIN_ERROR, "Invalid nui."),

    ACT_REGISTRATION_SURI_SYSTEM_CANNOT_ACCESS(817, Series.DOMAIN_ERROR, "SURI system cannot be accessed."),

    ACT_REGISTRATION_USER_CANNOT_ACCESS(818, Series.DOMAIN_ERROR, "The user does not have access to the system."),

    RECEIPT_TICKET_NOT_FOUND(819, Series.DOMAIN_ERROR, "Receipt Ticket not found"),

    RECEIPT_RESCHEDULE_PAID_REQUIRED(820, Series.DOMAIN_ERROR, "Receipt unpaid is not elegible to ReSchedule"),

    RECEIPT_RESCHEDULE_NOT_ENOUGH_STOCK(821, Series.DOMAIN_ERROR, "Not enough stock"),

    RECEIPT_RESCHEDULE_TYPESERVICE_MISSMATCH(822, Series.DOMAIN_ERROR, "ReSchedule typeservice missmatch"),

    RECEIPT_TICKET_TYPESERVICE_INVALID(823, Series.DOMAIN_ERROR, "Invalid receipt ticket for the given typeservice"),

    RECEIPT_CODE_EXISTS(824, Series.DOMAIN_ERROR, "Invalid receipt code"),

    REQUEST_CONTENT_TYPE_WRONG(825, Series.DOMAIN_ERROR, "Request Content Type Wrong"),

    SCHEDULE_DATE_CANT_BE_HOLIDAY(826, Series.DOMAIN_ERROR, "Schedule cant be holiday day"),

    SERVICE_ACT_CITIZEN_DO_NOT_HAVE_MARRIAGE(827, Series.DOMAIN_ERROR, "Your information is not available, please approach the nearest agency."),

    SERVICE_ACT_CITIZEN_DO_NOT_HAVE_FACTO_UNION(828, Series.DOMAIN_ERROR, "Your information is not available, please approach the nearest agency."),

    SERVICE_ACT_PAYMENT_NON_DIGITIZED_RECORD(829, Series.DOMAIN_ERROR, "Dear client, your record is not digitized, it is not possible to accept your payment."),

    USER_STATE_MUST_BE(830, Series.DOMAIN_ERROR, "User state"),

    IP_BLOCKED(831, Series.DOMAIN_ERROR, "Ip blocked"),

    OTP_INCORRECT(832, Series.DOMAIN_ERROR, "OTP incorrect"),

    VOUCHER_REQUEST_ERROR(833, Series.DOMAIN_ERROR, "Cant create voucher"),

    PARAMETIRAZATION_TOTAL_DAY_NEGATIVE(834, Series.DOMAIN_ERROR, "The total days must be positive!"),

    SCHEDULE_ACCESS_TYPE_CITIZEN_CODE(835, Series.DOMAIN_ERROR, "The access to create already exists, with the same citizen code and service type!"),

    LOGIN_MAX_ATTEMPTS_REACHED(836, Series.DOMAIN_ERROR, "Login max number of attempts reached"),

    ACT_USER_DOES_NOT_APPLY_TO_THE_SERVICE(837, Series.DOMAIN_ERROR, "User does not apply to the service."),

    USER_WITHOUT_ACCESS_TO_SURI(838, Series.DOMAIN_ERROR, "User without access to suri."),

    PARAMETIRAZATION_NOT_FOUND(839, Series.DOMAIN_ERROR, "No active parameterization found"),

    RESIDENTIAL_REGISTRATION_CANNOT_BE_UPGRADED(840, Series.DOMAIN_ERROR, "The necessary time has not elapsed to make the modification"),

    CITIZEN_EXIT_IN_SYSTEM(841, Series.DOMAIN_ERROR, "The ID number is already registered, please try to authenticate."),
    
    PARAMETERIZATION_STATE_NOT_VALID(842, Series.DOMAIN_ERROR, "Parameterization state not acceptable"),

    VALIDATE_CERTIFICATION_ACT_NOT_VALID_CODE_OR_DATE(843, Series.DOMAIN_ERROR, "The data entered are incorrect"),

    VALIDATE_CERTIFICATION_ACT_EXPIRED(844, Series.DOMAIN_ERROR, "Validity time of the expired certificate or number of verifications completed. Please generate new certificate."),

    VALIDATE_CERTIFICATION_ACT_NUMBER_EXPIRED(845, Series.DOMAIN_ERROR, "The document cannot be downloaded because it is not current, it has reached the maximum number of validations."),

    VALIDATE_CERTIFICATION_ACT_DATE_EXPIRED(846, Series.DOMAIN_ERROR, "The document cannot be downloaded because it is not valid, it has reached the maximum validity period."),

    MUST_HAVE_A_MAIN_RESIDENCE(847, Series.DOMAIN_ERROR, "Must have a main residence to generate the certificate."),

    USER_DOES_NOT_HAVE_AN_ACT(848, Series.DOMAIN_ERROR, "User does not have an act.");

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
     * Return the {@code ApiErrorStatus} enum constant with the specified numeric
     * value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the
     *                                  specified numeric value
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
