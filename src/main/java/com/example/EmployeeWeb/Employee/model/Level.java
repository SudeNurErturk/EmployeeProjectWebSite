package com.example.EmployeeWeb.Employee.model;

import lombok.Getter;


@Getter
public enum Level {
    L0(1,"Level 0"),
    L1(2, "Level 1"),
    L2(3, "Level 2"),
    L3(4, "Level 3"),
    L4(5,"Level 4"),
    L5(6, "Level 5");

    private final int value;
    private final String description;

    Level(int value, String description) {
        this.value = value;
        this.description = description;
    }
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Level fromValue(int value) {
        for (Level level : Level.values()) {
            if (level.value == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }


}
