package com.test.jaxb;


import lombok.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouj
 * @since 2019/11/12
 */
@Data
@XmlRootElement(name="txn")
@XmlAccessorType(XmlAccessType.FIELD)
public  class TNode {

    @XmlElement(name="key", required = true)
    private String key;

    @XmlElement(name="value", required = true)
    private String value;

    public TNode(String key,String value){
        this.key = key;
        this.value = value;
    }

    public TNode(){

    }
    private static ConcurrentHashMap<Class, JAXBContext> jaxbContMap
            = new ConcurrentHashMap<Class, JAXBContext>();

    public static <T> String cacheWriteAsString(T t) {
        try {
            JAXBContext jc = jaxbContMap.get(t.getClass());
            if (jc == null) {
                synchronized (t.getClass()) {
                    jc = JAXBContext.newInstance(t.getClass());
                    jaxbContMap.put(t.getClass(), jc);
                }
            }
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return null;
    }


        public static <T> String writeAsString(T t) {
        try {
            JAXBContext jc = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        TNode node = new TNode("key", "value");

        // *) 迭代重复的次数
        int numIter = 1000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numIter; i++) {
           cacheWriteAsString(node);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("iter num: %d, consume: %dms, avg: %.2fms",
                numIter, (endTime - startTime), (endTime - startTime) * 1.0 / numIter));
    }


}
