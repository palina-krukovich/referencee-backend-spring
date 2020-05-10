package com.referencee.api.model.enumeration;

public enum Gender {
    ANY("any"),
    MALE("male"),
    FEMALE("female");

    public String label;

    private Gender(String label) {
        this.label = label;
    }

    public static Gender valueOfLabel(String label) {
        for (Gender e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
