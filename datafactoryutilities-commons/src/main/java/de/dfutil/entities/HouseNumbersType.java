package de.dfutil.entities;

public enum HouseNumbersType {

    NOT_SHARED("N"),
    EVEN("G"),
    ODD("U");

    private String statusValue;

    private HouseNumbersType(String statusValue) {
        this.statusValue = statusValue;
    }


}
