package com.example.sensedia.enums;

public enum DeliveryStatus {

    PENDING(0),
    DELIVERED(1),
    CANCELLED(2);

    private final int index;

    DeliveryStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
