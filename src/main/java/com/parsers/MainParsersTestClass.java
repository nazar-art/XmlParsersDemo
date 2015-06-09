package com.parsers;

import com.xpath.EmployeeXpathParser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.parsers.FilesLocation.EMPLOYEE_XML;

public class MainParsersTestClass {

    private static final Logger LOGGER = Logger.getLogger(MainParsersTestClass.class);

    public static void main(String[] args) {
        long startTime, elapsedTime;
        MainParsersTestClass main = new MainParsersTestClass();

        startTime = System.currentTimeMillis();
        main.testSaxParser();   // test
        elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info(String.format("Parsing time is: %d ms%n", elapsedTime));

        startTime = System.currentTimeMillis();
        main.testStaxParser();  // test
        elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info(String.format("Parsing time is: %d ms%n", elapsedTime));

        startTime = System.currentTimeMillis();
        main.testDomParser();  // test
        elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info(String.format("Parsing time is: %d ms%n", elapsedTime));

        startTime = System.currentTimeMillis();
        main.testXpathParser();  // test
        elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info(String.format("Parsing time is: %d ms%n", elapsedTime));
    }

    private void testXpathParser() {
        LOGGER.info("Using Xpath Parser:\n-----------------");
        try {
            EmployeeXpathParser xpathParser = new EmployeeXpathParser();
            ArrayList<Employee> employees = xpathParser.parse(FilesLocation.EMPLOYEE_XML.getFilename());
            for (Employee employee : employees) {
                System.out.println(employee);
            }

        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (SAXException | XPathExpressionException | IOException e) {
            e.printStackTrace();
        }
    }

    void testSaxParser() {
        LOGGER.info("Using SAX Parser:\n-----------------");
        try {
            /*SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(OLD_EMPLOYEE_XSD.getFilename()));
            // create new xml schema*/
            SAXParserFactory factory = SAXParserFactory.newInstance();
//            factory.setSchema(schema);                                  // set schema to the schemaFactory

            InputStream xmlInput = new FileInputStream(EMPLOYEE_XML.getFilename());
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            saxParser.parse(xmlInput, handler);

            for (Employee emp : handler.employees) {
                LOGGER.info(ToStringBuilder.reflectionToString(emp/*, ToStringStyle.MULTI_LINE_STYLE*/));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    void testStaxParser() {
        LOGGER.info("Using StAX Parser:\n------------------");
        try {
            StaxCursorXmlParser staxCursorXmlParser = new StaxCursorXmlParser(EMPLOYEE_XML.getFilename());
            List<Employee> employees = staxCursorXmlParser.parseEmployee();
            for (Employee emp : employees) {
                LOGGER.info(ToStringBuilder.reflectionToString(emp));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    void testDomParser() {
        LOGGER.info("Using DOM Parser:\n-----------------");
        DomXmlParser domXmlParser = new DomXmlParser();

        List<Employee> employees = domXmlParser.parseFromXmlToEmployee();
        for (Employee employee : employees) {
            LOGGER.info(ToStringBuilder.reflectionToString(employee));
        }
    }
}

