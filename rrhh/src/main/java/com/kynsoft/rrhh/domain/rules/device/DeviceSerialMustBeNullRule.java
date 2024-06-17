package com.kynsoft.rrhh.domain.rules.device;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class DeviceSerialMustBeNullRule extends BusinessRule {

    private final String serial;

    public DeviceSerialMustBeNullRule(String serial) {
        super(
                DomainErrorMessage.DEVICE_SERIAL_CANNOT_BE_EMPTY, 
                new ErrorField("serial", "The serial of the device cannot be empty.")
        );
        this.serial = serial;
    }

    @Override
    public boolean isBroken() {
        return this.serial == null || this.serial.isEmpty();
    }

}
