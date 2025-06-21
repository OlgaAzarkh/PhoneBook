package utils;

public enum HeaderMenuItemEnum {
    LOGIN("//a[text()='LOGIN']"),
    HOME("//a[text()='HOME']"),
    ABOUT("//a[text()='ABOUT']"),
    CONTACTS("//a[text()='CONTACTS']"),
    ADD("//a[text()='ADD']"),
    SIGN_OUT("//a[text()='Sign Out']");

    private final String locator;

    HeaderMenuItemEnum(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
