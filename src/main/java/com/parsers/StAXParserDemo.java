package com.parsers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static com.parsers.Files.EMPLOYEE_XML;

public class StAXParserDemo {
    public static void main(String[] args) {
        try {
            StaxXmlParser staxXmlParser = new StaxXmlParser(EMPLOYEE_XML.getFilename());
            List<Employee> employees = staxXmlParser.parseEmployee();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

class StaxXmlParser {

    private XMLStreamReader reader;

    private List<Employee> employeeList;
    private Employee currentEmployee;
    private String tagContent;

    public StaxXmlParser(String filename) {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(new File(filename)));
            parseEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> parseEmployee() throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("employee".equals(reader.getLocalName())) {
                        currentEmployee = new Employee();
                    }
                    if ("staff".equals(reader.getLocalName())) {
                        employeeList = new ArrayList<>();
                    }
                    if ("hiredate".equals(reader.getLocalName())) {
                        int yearAttr = Integer.parseInt(reader.getAttributeValue(null, "year"));
                        int monthAttr = Integer.parseInt(reader.getAttributeValue(null, "month"));
                        int dayAttr = Integer.parseInt(reader.getAttributeValue(null, "day"));

                        currentEmployee.setHireDay(yearAttr, monthAttr - 1, dayAttr);
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.ATTRIBUTE:
                    int count = reader.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        System.out.printf("count is: %d%n", count);
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "employee":
                            employeeList.add(currentEmployee);
                            break;
                        case "name":
                            currentEmployee.setName(tagContent);
                            break;
                        case "salary":
                            currentEmployee.setSalary(Double.parseDouble(tagContent));
                            break;
                    }
            }
        }

        return employeeList;
    }

}
