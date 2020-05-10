package com.referencee.api.model.enumeration;

public enum Pose {
    ANY("any"),
    ACTION("action"),
    STATIC("static");

    public String label;

    private Pose(String label) {
        this.label = label;
    }

    public static Pose valueOfLabel(String label) {
        for (Pose e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
