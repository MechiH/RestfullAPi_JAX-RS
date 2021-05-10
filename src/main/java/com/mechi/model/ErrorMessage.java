package com.mechi.model;

public class ErrorMessage {
    private String errorMessage;
    private int erroCode;
    private String documentation;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, int erroCode, String documentation) {
        super();
        this.documentation = documentation;
        this.erroCode = erroCode;
        this.errorMessage = errorMessage;

    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErroCode() {
        return this.erroCode;
    }

    public void setErroCode(int erroCode) {
        this.erroCode = erroCode;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

}
