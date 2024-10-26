package com.UserManager.enums;

/**
 * Enum representing the gender of a person, with a unique code and display name.
 */
public enum Gender {
    /** Male gender. */
    MALE(1, "آقا"),

    /** Female gender. */
    FEMALE(2, "خانم");

    private final int code;
    private final String displayName;

    /**
     * Constructor to initialize the gender with a unique code and display name.
     *
     * @param code        Unique identifier for the gender.
     * @param displayName Display name for the gender.
     */
    Gender(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * Gets the unique code for the gender.
     *
     * @return the code of the gender.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the display name for the gender.
     *
     * @return the display name of the gender.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a string representation of the gender, which is its display name.
     *
     * @return the display name of the gender.
     */
    @Override
    public String toString() {
        return displayName;
    }
}


