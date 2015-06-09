package com.xpath;

import com.parsers.Employee;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeXpathParser {

    private DocumentBuilder builder;
    private XPath path;

    public EmployeeXpathParser() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        XPathFactory pathFactory = XPathFactory.newInstance();
        path = pathFactory.newXPath();
    }

    public ArrayList<Employee> parse(String filename) throws IOException, SAXException, XPathExpressionException {
        File file = new File(filename);
        Document document = builder.parse(file);

        ArrayList<Employee> employees = new ArrayList<>();
        int emplCount = Integer.parseInt(path.evaluate("count(/staff/employee)", document));

        for (int i = 1; i <= emplCount; i++) {
            String name = path.evaluate("/staff/employee[" + i + "]/name", document);
            double salary = Double.valueOf(path.evaluate("/staff/employee[" + i + "]/salary", document));
            int year = Integer.valueOf(path.evaluate("/staff/employee[" + i + "]/hiredate/@year", document));
            int month = Integer.valueOf(path.evaluate("/staff/employee[" + i + "]/hiredate/@month", document));
            int day = Integer.valueOf(path.evaluate("/staff/employee[" + i + "]/hiredate/@day", document));

            employees.add(new Employee(name, salary, year, month, day));
        }
        return employees;
    }
}
