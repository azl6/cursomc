package com.nelioalves.curso.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

    private Integer httpStatus;
    private String errorMessage;
    private long timeStamp;

    public StandardError(Integer httpStatus, String errorMessage, long timeStamp) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
