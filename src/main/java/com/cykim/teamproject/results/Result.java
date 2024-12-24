package com.cykim.teamproject.results;

public interface Result {
    String NAME = "result";

    String name();

    default String nameToLower() {
        return name().toLowerCase();
    }
}
