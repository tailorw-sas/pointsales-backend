package com.kynsof.shift.domain.rules.service.block;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.shift.domain.service.IBlockService;

import java.util.UUID;

public class BlockCodeMustBeUniqueRule extends BusinessRule {

    private final IBlockService service;

    private final String code;

    private final UUID id;

    public BlockCodeMustBeUniqueRule(IBlockService service, String code, UUID id) {
        super(
                DomainErrorMessage.SERVICE_NAME_MUST_BY_UNIQUE, 
                new ErrorField("name", "The block name must be unique.")
        );
        this.service = service;
        this.code = code;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByCodeAndNotId(code, id) > 0;
    }

}
