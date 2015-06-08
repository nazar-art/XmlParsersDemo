package com.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.parsers.Files.EMPLOYEE_XSD;
import static com.parsers.Files.EMPLOYEE_XML;

/**
 * @author nlelyak
 * @version 1.00 2014-01-28.
 */
public class SaxParserDemo {
    public static void main(String[] args) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(EMPLOYEE_XSD.getFilename())); // create new xml schema
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setSchema(schema);                                  // set schema to the schemaFactory

            InputStream xmlInput = new FileInputStream(EMPLOYEE_XML.getFilename());
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            saxParser.parse(xmlInput, handler);

            System.out.println("List of employees:");

            for (Employee emp : handler.employees) {
                System.out.println(emp);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

class SaxHandler extends DefaultHandler {

    private Stack<String> elementStack = new Stack<>();
    private Stack<Object> objectStack = new Stack<>();

    public List<Employee> employees = new ArrayList<>();
    Employee employee = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if ("employee".equals(qName)) {
            employee = new Employee();
            this.objectStack.push(employee);
            this.employees.add(employee);
        }
        if ("hiredate".equals(qName)) {
            int yearAtt = Integer.parseInt(attributes.getValue("year"));
            int monthAtt = Integer.parseInt(attributes.getValue("month"));
            int dayAtt = Integer.parseInt(attributes.getValue("day"));

            if (employee != null) {
                employee.setHireDay(yearAtt, monthAtt - 1, dayAtt);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elementStack.pop();

        if ("employee".equals(qName)) {
            this.objectStack.pop();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;        // skip white space

        if ("name".equals(currentElement())) {
            employee = (Employee) this.objectStack.peek();
            employee.setName(value);
        } else if ("salary".equals(currentElement()) && "employee".equals(currentParentElement())) {
            employee.setSalary(Double.parseDouble(value));
        }
    }

    private String currentElement() {
        return this.elementStack.peek();
    }

    private String currentParentElement() {
        if (this.elementStack.size() < 2) return null;
        return this.elementStack.get(this.elementStack.size() - 2);
    }
}
