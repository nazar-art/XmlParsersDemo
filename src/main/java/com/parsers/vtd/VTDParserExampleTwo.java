package com.parsers.vtd;

import com.parsers.FilesLocation;
import com.ximpleware.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class VTDParserExampleTwo {

    private VTDGen vg;
    private VTDNav vn;
    private AutoPilot ap;

    public VTDParserExampleTwo() {
        vg = new VTDGen();
    }

    public void parseAndPrint() throws NavException {
        int level = 0;
        for (boolean el = (vn != null); el; el = vn.toElement(VTDNav.NEXT_SIBLING)) {
            printTag(vn, level);

            parseAndPrintChildren(level);
        }

    }

    private void parseAndPrintChildren(int level) throws NavException {
        vn.push();

        for (boolean el = vn.toElement(VTDNav.FIRST_CHILD); el; el = vn.toElement(VTDNav.NEXT_SIBLING)) {
            printTag(vn, level + 1);
            parseAndPrintChildren(level + 1);
        }

        vn.pop();
    }

    private VTDNav loadFile(String filePath) throws IOException {
        File fDoc = new File(filePath);

        if (fDoc.exists()) {
            System.out.println("loadFile file exists [" + filePath + "]");

            vg.clear();
            if (vg.parseFile(filePath, true)) {
                vn = vg.getNav();
            }
        } else {
            throw new IOException("File [" + filePath + "] invalid");
        }

        if (vn == null) {
            throw new IOException("Cannot parse file [" + filePath + "]");
        }

        return vn;
    }

    public boolean getElementsByXpath() {
        boolean found = false;

        ap = new AutoPilot(vn);

        try {
            String xpQ = "//employee";

            ap.selectXPath(xpQ);
            if (ap.evalXPathToBoolean()) {
                found = true;
            } else {
                System.out.println(this.getClass() + ".getAllElements evalXPathToBoolean[" + ap.evalXPathToBoolean() + "]");
            }
        } catch (XPathParseException e) {
            e.printStackTrace();
        }

        return found;
    }

    private void loadAttributeMap(VTDNav nav, Map<String, String> amap) {
        nav.push();

        try {
            AutoPilot apAtt = new AutoPilot(nav);
            apAtt.selectXPath("@*");

            int j;
            while ((j = apAtt.evalXPath()) != -1) {
                String name = nav.toString(j);
                String val = nav.toString(j + 1);

                amap.put(name, val);
            }
        } catch (XPathParseException | XPathEvalException | NavException e) {
            e.printStackTrace();
        }

        nav.pop();
    }

    private void printTag(VTDNav vn, int level) throws NavException {
        String tag = vn.toString(vn.getCurrentIndex());
        System.out.print("Level [" + level + "] Tag [" + tag + "]");

        Map<String, String> amap = new LinkedHashMap<>();
        loadAttributeMap(vn, amap);

        for (String aname : amap.keySet()) {
            String aval = amap.get(aname);

            System.out.print(" @" + aname + "=" + aval);
        }
        System.out.print("\n");
    }

    private void parseAndPrintAP() {
        int level = 0;

        try {
            while (ap.evalXPath() != -1) {
                printTag(vn, level);

                parseAndPrintChildren(level);

            }
        } catch (XPathEvalException | NavException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VTDParserExampleTwo vp = new VTDParserExampleTwo();
        try {
            vp.loadFile(FilesLocation.EMPLOYEE_XML.getFilename());
            if (vp.getElementsByXpath()) {
                vp.parseAndPrintAP();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
