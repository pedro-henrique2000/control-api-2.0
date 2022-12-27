package com.project.controlfood.interfaces.http.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusDTO {
    INACTIVE, ACTIVE;

    @JsonCreator
    public static StatusDTO from(String value) {
        for (StatusDTO statusDTO : StatusDTO.values()) {
            if (statusDTO.name().equalsIgnoreCase(value)) {
                return statusDTO;
            }
        }
        return null;
    }

}
