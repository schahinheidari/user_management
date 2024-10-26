package com.UserManager.enums;

/**
 * Enum representing different types of permissions, each with a unique code and description.
 */
public enum Permissions {
    /** Permission to view only. */
    READ(1, "فقط اجازه مشاهده داشتن"),

    /** Permission to view and write. */
    WRITE(2, "اجازه مشاهده و نوشتن"),

    /** Administrator permission with full access. */
    ADMIN(3, "ادمین"),

    /** Permission to delete content. */
    DELETE(4, "اجازه حذف کردن داشتن"),

    /** Permission to edit content. */
    EDIT(5, "اجازه ویرایش داشتن");

    private final int code;
    private final String desc;

    /**
     * Constructor to initialize each permission with a code and description.
     *
     * @param code Unique identifier for the permission.
     * @param desc Description of the permission.
     */
    private Permissions(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets the unique code for the permission.
     *
     * @return the permission code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the description of the permission.
     *
     * @return the permission description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns a string representation of the permission, including its code and description.
     *
     * @return a formatted string with code and description.
     */
    @Override
    public String toString() {
        return "Permissions [code=" + code + ", desc=" + desc + "]";
    }
}

