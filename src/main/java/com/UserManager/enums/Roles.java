package com.UserManager.enums;

/**
 * Enum representing different user roles in the system, each with a unique ID and description.
 */
public enum Roles {
    /** Administrator role with full access permissions. */
    ADMIN(1, "نقش ادمین داشتن"),

    /** Editor role with permissions to modify content. */
    EDITOR(2, "نقش ویرایشگر داشتن"),

    /** Manager role with oversight and management permissions. */
    MANAGER(3, "نقش مدیر داشتن"),

    /** Viewer role with read-only access. */
    VIEWER(4, "فقط نقش بیننده و نظاره گر بودن");

    private final int id;
    private final String role;

    /**
     * Constructor to initialize the role with a unique ID and description.
     *
     * @param id   Unique identifier for the role.
     * @param role Description of the role.
     */
    Roles(int id, String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Gets the unique identifier for the role.
     *
     * @return the ID of the role.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the description of the role.
     *
     * @return the role description.
     */
    public String getRole() {
        return role;
    }

    /**
     * Returns a string representation of the role, including its ID and description.
     *
     * @return a string in the format "Role ID: <id>, Description: <role>"
     */
    @Override
    public String toString() {
        return "Role ID: " + id + ", Description: " + role;
    }
}

