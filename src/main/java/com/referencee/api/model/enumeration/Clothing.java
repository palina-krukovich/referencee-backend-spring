package com.referencee.api.model.enumeration;

public enum Clothing {
    ANY("any"),
    NUDE("nude"),
    CLOTHED("clothed");

    public final String label;

    private Clothing(String label) {
        this.label = label;
    }

    public static Clothing valueOfLabel(String label) {
        for (Clothing e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
