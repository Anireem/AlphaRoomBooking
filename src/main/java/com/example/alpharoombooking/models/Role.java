package com.example.alpharoombooking.models;

public enum Role {
    EMPLOYEE("Сотрудник"),
    OFFICE_MANAGER("Офис-менеджер");

    private String translation;

    Role() {
    }

    Role(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
