package com.kynsoft.notification.infrastructure.excel.validator;

import com.kynsof.share.core.domain.response.ErrorField;

import java.util.List;

public abstract class ExcelRuleValidator<T> {
    public abstract boolean validate(T obj,List<ErrorField> errorFieldList);
}

