package com.example.demo.exeptions;

public enum TestApiError {
    E500_NOT_FOUND("Not found"),
    E500_ALREADY_EXIST("Email with the same name already exists"),
    E403_FORBIDDEN("Invalid email/password combination");

    private final String text;

    TestApiError(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
