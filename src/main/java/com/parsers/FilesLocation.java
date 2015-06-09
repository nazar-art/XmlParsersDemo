package com.parsers;

public enum FilesLocation {

    EMPLOYEE_XML("src/main/resources/employee.xml"),
    BOOKSTORE_XML("src/main/resources/bookstore.xml"),
    EMPLOYEE_XSD("src/main/resources/employee.xsd");

    private String filename;

    public String getFilename() {
        return filename;
    }

    private FilesLocation(String filename) {
        this.filename = filename;
    }

}
