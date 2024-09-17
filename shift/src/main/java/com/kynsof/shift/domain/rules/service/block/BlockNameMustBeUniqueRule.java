package com.kynsof.shift.domain.rules.service.block;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.shift.domain.service.IBlockService;

import java.util.UUID;

public class BlockNameMustBeUniqueRule extends BusinessRule {

    private final IBlockService service;

    private final String name;

    private final UUID id;

    public BlockNameMustBeUniqueRule(IBlockService service, String name, UUID id) {
        super(
                DomainErrorMessage.BLOCK_NAME_MUST_BY_UNIQUE, 
                new ErrorField("name", "The block name must be unique.")
        );
        this.service = service;
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean isBroken() {
        return this.service.countByNameAndNotId(name, id) > 0;
    }

}
