package com.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.parsers.FilesLocation.EMPLOYEE_XSD;
import static com.parsers.FilesLocation.EMPLOYEE_XML;

/**
 * @author nlelyak
 * @version 1.00 2014-01-28.
 */
public class DomParserDemo {
    public static void main(String[] args) {
        DomXmlParser domXmlParser = new DomXmlParser();

        List<Employee> employees = domXmlParser.parseFromXmlToEmployee();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

class DomXmlParser {

    private Document document;
    List<Employee> empList = new ArrayList<>();

    public SchemaFactory schemaFactory;
    public final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";


    public DomXmlParser() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(EMPLOYEE_XML.getFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isDocumentSchemaValid(Document document) {
        boolean result = true;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            schemaFactory = SchemaFactory.newInstance(language);
            Schema schema = schemaFactory.newSchema(new File(EMPLOYEE_XSD.getFilename()));

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public List<Employee> parseFromXmlToEmployee() {

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node instanceof Element) {
                Employee emp = new Employee();

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    // identify the child tag of employees
                    if (cNode instanceof Element) {
                        switch (cNode.getNodeName()) {
                            case "name":
                                emp.setName(text(cNode));
                                break;
                            case "salary":
                                emp.setSalary(Double.parseDouble(text(cNode)));
                                break;
                            case "hiredate":
                                int yearAttr = Integer.parseInt(cNode.getAttributes().getNamedItem("year").getNodeValue());
                                int monthAttr = Integer.parseInt(cNode.getAttributes().getNamedItem("month").getNodeValue());
                                int dayAttr = Integer.parseInt(cNode.getAttributes().getNamedItem("day").getNodeValue());

                                emp.setHireDay(yearAttr, monthAttr - 1, dayAttr);
                                break;
                        }
                    }
                }

                empList.add(emp);
            }
        }
        return empList;
    }

    private String text(Node cNode) {
        return cNode.getTextContent().trim();
    }
}

class SimpleErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println(exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println(exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println(exception.getMessage());
    }
}
