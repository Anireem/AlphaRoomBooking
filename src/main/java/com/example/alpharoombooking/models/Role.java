package com.example.alpharoombooking.models;

public enum Role {
    ADMIN("Админ"),
    EMPLOYEE("Сотрудник"),
    OFFICE_MANAGER("Офис-менеджер");

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
