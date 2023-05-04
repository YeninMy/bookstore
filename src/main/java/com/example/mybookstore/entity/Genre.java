package com.example.mybookstore.entity;

public enum Genre {
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    HISTORICAL_FICTION("Historical Fiction");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getDisplayNameForGenre(String genre) {
        try {
            return Genre.valueOf(genre.toUpperCase()).getDisplayName();
        } catch (IllegalArgumentException e) {
            return genre;
        }
    }
}