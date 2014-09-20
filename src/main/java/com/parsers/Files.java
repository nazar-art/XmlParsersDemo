package com.parsers;

/**
 * @author nlelyak
 * @version 1.00 2014-01-25.
 */
public enum Files {

    EMPLOYEE_XML("./src/main/resources/employee.xml"),
    EMPLOYEE_XSD("resources/employee.xsd"),
    NEW_EMPLOYEE_XSD("resources/newEmployee.xsd"),
    OLD_EMPLOYEE_XSD("resources/oldEmployee.xsd");

    private String filename;

    public String getFilename() {
        return filename;
    }

    private Files(String filename) {
        this.filename = filename;
    }

}
