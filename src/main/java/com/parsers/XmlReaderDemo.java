package com.parsers;


import com.northconcepts.datapipeline.core.DataEndpoint;
import com.northconcepts.datapipeline.core.DataReader;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.xml.XmlReader;
import org.apache.log4j.Logger;

import java.io.File;

public class XmlReaderDemo {
    public static final Logger log = DataEndpoint.log;

    public static void main(String[] args) {
        DataReader reader = new XmlReader(new File(FilesLocation.BOOKSTORE_XML.getFilename()))
                .addField("title", "//book/title/text()")
                .addField("language", "//book/title/@lang")
                .addField("price", "//book/price/text()")
                .addRecordBreak("//book");

        reader.open();
        processFile(reader);
    }

    private static void processFile(DataReader reader) {
        try {
            Record record;
            while ((record = reader.read()) != null) {
                log.info(record);
//                System.out.println(record);
            }
        } finally {
            if (reader.isOpen()) {
                reader.close();
            }
        }
    }
}
