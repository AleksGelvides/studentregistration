package com.example.studentregistration.enums;

import lombok.Getter;

@Getter
public enum EventAction {
    CREATE_NEW_STUDENT("Student was created: "),
    REMOVE_STUDENT("Student was removed: "),
    WAS_NOT_REMOVED("Student wasn't removed with id: "),
    INIT_SUCCESS("Students was loaded from file. Collection size: "),
    INIT_ERROR("Students wasn't loaded from file. Error: ");

    private final String value;
    EventAction(String s) {
        this.value = s;
    }
}
