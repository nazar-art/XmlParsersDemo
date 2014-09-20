package com.parsers;

import com.generated.Staff;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;

public class JaxbDemo {

    private static final Logger LOGGER = Logger.getLogger(JaxbDemo.class);

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            // create jaxb and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Staff.class.getPackage().getName());
            FileInputStream in = new FileInputStream(new File(Files.EMPLOYEE_XML.getFilename()));

            System.out.println("Output from employee XML file");
            Unmarshaller um = context.createUnmarshaller();
            Staff staff = (Staff) um.unmarshal(in);

            // print employee list
            for (Staff.Employee emp : staff.getEmployee()) {
//                System.out.println(new ObjectAnalyzer().toString(emp));
                LOGGER.info(ToStringBuilder.reflectionToString(emp));
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(String.format("Parsing time is: %d ms%n", elapsedTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
