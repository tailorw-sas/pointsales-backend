package com.kynsof.rrhh.doman.rules.device;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceIpValidateRule extends BusinessRule {

    private final String ip;

    public DeviceIpValidateRule(String ip) {
        super(
                DomainErrorMessage.DEVICE_IP_VALIDATE,
                new ErrorField("ip", "La direccion ip no es correcta.")
        );
        this.ip = ip;
    }

    @Override
    public boolean isBroken() {
        return !isValidIPAddress(ip);
    }

    private static final String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static boolean isValidIPAddress(String ip) {
        if (ip == null) {
            return false;
        }
        Matcher matcher = IPV4_PATTERN.matcher(ip);
        return matcher.matches();
    }

}
