//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.31 at 10:45:51 AM EET 
//


package com.generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.generated package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.generated
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Staff }
     */
    public Staff createStaff() {
        return new Staff();
    }

    /**
     * Create an instance of {@link Staff.Employee }
     */
    public Staff.Employee createStaffEmployee() {
        return new Staff.Employee();
    }

    /**
     * Create an instance of {@link Staff.Employee.Hiredate }
     */
    public Staff.Employee.Hiredate createStaffEmployeeHiredate() {
        return new Staff.Employee.Hiredate();
    }

}