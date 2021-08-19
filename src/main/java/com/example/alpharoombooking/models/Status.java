package com.example.alpharoombooking.models;

public enum Status {
    APPROVAL("На подтверждении"),
    AGREED("Подтверждено"),
    REJECTED("Отклонено");

    private String translation;

    Status(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return this.translation;
    }
}
