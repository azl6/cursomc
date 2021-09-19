package com.nelioalves.curso.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(Integer httpStatus, String errorMessage, long timeStamp) {
        super(httpStatus, errorMessage, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
