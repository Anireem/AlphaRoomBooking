package com.example.alpharoombooking.models;

public enum Role {
    EMPLOYEE("Сотрудник"),
    OFFICE_MANAGER("Офис-менеджер"),
    ADMIN("Админ");

    private String translation;

    Role(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return this.translation;
    }

    @Override
    public String toString() {
        return this.translation;
    }
}
