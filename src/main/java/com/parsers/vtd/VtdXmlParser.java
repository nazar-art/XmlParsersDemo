package com.parsers.vtd;

import com.parsers.FilesLocation;
import com.ximpleware.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class VtdXmlParser {

    private static final Logger LOGGER = Logger.getLogger(VtdXmlParser.class);

    private VTDNav vn;

    public VtdXmlParser(String filename) {
        try {
            File file = new File(filename);
            FileInputStream in = new FileInputStream(file);
            byte[] ba = new byte[(int) file.length()];
            in.read(ba);
            VTDGen vg = new VTDGen();
            vg.setDoc(ba);
            vg.parse(false);
            vn = vg.getNav();
            parseEmployee();
        } catch (IOException | ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void parseEmployee() {
        try {
            if (vn.matchElement("staff")) {
                /*System.out.println(" orderDate==>"
                        + vn.toString(vn.getAttrVal("orderDate")));*/
                if (vn.toElement(VTDNav.FIRST_CHILD, "employee")) {
                    if (vn.toElement(VTDNav.FIRST_CHILD)) {
                        do {
                            System.out.print(vn.toString(vn.getCurrentIndex()));
                            System.out.print("==>");
                            System.out.println(vn.toString(vn.getText()));
                        } while (vn.toElement(VTDNav.NEXT_SIBLING));
                    }
                }
            }
        } catch (NavException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new VtdXmlParser(FilesLocation.EMPLOYEE_XML.getFilename());
    }


}
