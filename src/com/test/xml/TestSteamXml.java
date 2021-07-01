package com.test.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author zhouj
 * @since 2019/12/20
 */
public class TestSteamXml {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("/Users/zhouj/Downloads/search_response.xml"));
        while (reader.hasNext()) {
            reader.next();
            if (reader.isStartElement() && reader.getLocalName().equals("Vehicle")) {
                System.out.println(reader.next());
            }
        }
    }
}
