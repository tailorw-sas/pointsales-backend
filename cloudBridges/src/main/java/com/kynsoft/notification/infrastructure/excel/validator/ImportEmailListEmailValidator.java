package com.kynsoft.notification.infrastructure.excel.validator;

import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.notification.domain.bean.ImportEmailListRow;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportEmailListEmailValidator extends ExcelRuleValidator<ImportEmailListRow> {
    private final String EMAIL_REG="^(.+)@(.+)$";
    @Override
    public boolean validate(ImportEmailListRow obj, List<ErrorField> errorFieldList) {
        return validateEmailIfNotEmpty(obj,errorFieldList) &&
                validateEmailStructure(obj,errorFieldList) ;
    }

    private boolean validateEmailIfNotEmpty(ImportEmailListRow obj,List<ErrorField> errorFieldList){
        if (Objects.isNull(obj.getEmail()) || obj.getEmail().isEmpty()){
            errorFieldList.add(new ErrorField("Email","The email can't be empty"));
            return false;
        }
        return true;
    }
    private boolean validateEmailStructure(ImportEmailListRow obj,List<ErrorField> errorFieldList){
        Pattern pattern = Pattern.compile(EMAIL_REG);
        Matcher matcher = pattern.matcher(obj.getEmail());
        if(!matcher.matches()){
            errorFieldList.add(new ErrorField("Email","The email is not valid"));
            return false;
        }
        return true;
    }

}
