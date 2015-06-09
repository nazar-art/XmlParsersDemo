package com.xpath;

import com.parsers.Employee;
import com.parsers.FilesLocation;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeXpathParserDemo {

    private static final Logger LOGGER = Logger.getLogger(EmployeeXpathParser.class);

    public static void main(String[] args) {
        try {
            EmployeeXpathParser xpathParser = new EmployeeXpathParser();
            ArrayList<Employee> employees = xpathParser.parse(FilesLocation.EMPLOYEE_XML.getFilename());
            for (Employee employee : employees) {
                System.out.println(employee);
            }

        } catch (ParserConfigurationException  e) {
            LOGGER.error(e);
        } catch (SAXException | XPathExpressionException | IOException e) {
            e.printStackTrace();
        }
    }
}
