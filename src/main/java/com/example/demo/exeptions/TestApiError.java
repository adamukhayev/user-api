package com.example.demo.exeptions;

public enum TestApiError {
    E500_NOT_FOUND("Not found"),
    E500_QUESTION_NOT_FOUND("Question not found"),
    E500_ANSWER_NOT_FOUND("Answer not found");

    private final String text;

    TestApiError(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
