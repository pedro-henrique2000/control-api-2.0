package com.project.controlfood.interfaces.http.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TagDTO {
    JAPANESE, PIZZA, ARABIAN, OTHER;

    @JsonCreator
    public static TagDTO from(String value) {
        for (TagDTO tagDTO : TagDTO.values()) {
            if (tagDTO.name().equalsIgnoreCase(value)) {
                return tagDTO;
            }
        }
        return null;
    }
}
