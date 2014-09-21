package com.parsers;

public enum Files {

    EMPLOYEE_XML("./src/main/resources/employee.xml"),
    EMPLOYEE_XSD("./src/main/resources/employee.xsd");

    private String filename;

    public String getFilename() {
        return filename;
    }

    private Files(String filename) {
        this.filename = filename;
    }

}
