package com.parsers;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static com.parsers.Files.EMPLOYEE_XML;

public class MainTest {

    private static final Logger LOGGER = Logger.getLogger(MainTest.class);

    public static void main(String[] args) {
        long startTime, elapsedTime;
        MainTest main = new MainTest();

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
            StaxXmlParser staxXmlParser = new StaxXmlParser(EMPLOYEE_XML.getFilename());
            List<Employee> employees = staxXmlParser.parseEmployee();
            for (Employee emp : employees) {
                ToStringBuilder.reflectionToString(emp);
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
            ToStringBuilder.reflectionToString(employee);
        }
    }
}

