package com.UserManager.enums;

/**
 * Enum representing the status of a user or entity, each with a unique code and description.
 */
public enum Status {
    /** Represents an active status. */
    ACTIVE(1, "وضعیت فعال"),

    /** Represents an inactive status. */
    INACTIVE(2, "وضعیت غیرفعال"),

    /** Represents a banned or blocked status. */
    BANNED(3, "وضعیت بلاک");

    private final int code;
    private final String desc;

    /**
     * Constructor to initialize each status with a unique code and description.
     *
     * @param code Unique identifier for the status.
     * @param desc Description of the status.
     */
    Status(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets the unique code for the status.
     *
     * @return the status code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the description of the status.
     *
     * @return the status description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns a string representation of the status, which includes the description.
     *
     * @return the status description.
     */
    @Override
    public String toString() {
        return desc;
    }
}

