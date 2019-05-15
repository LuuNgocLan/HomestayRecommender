package com.ripple.effects.fb.java.module.detail.adapter;

public enum HomestayDetailSection {
    INFOR(0), FEATURE(1), MAP(2), REVIEW(3);

    private int value;

    HomestayDetailSection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
