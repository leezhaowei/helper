package com.zwli.xml;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ProcessXmlByDom4j {

    private static List<String[]> escapingCharacters;

    private static void init() {
        escapingCharacters = new ArrayList<String[]>();
        escapingCharacters.add(new String[] { "&lt;", "<" });
        escapingCharacters.add(new String[] { "&quot;", "\"" });
        escapingCharacters.add(new String[] { "&#xA;", "" });
    }

    public static void main(String[] args) {
        init();
        // testA();
        // testB();
        testC();
    }

    private static void testC() {
        URL fileUrl = ProcessXmlByDom4j.class.getResource("nexus.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document doc = saxReader.read(fileUrl.openStream());
            List<?> nodes = doc.selectNodes("//metadata/versioning/versions/version");
            for (Object obj : nodes) {
                Element e = (Element) obj;
                String text = e.getTextTrim();
                System.out.println(text);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testB() {
        String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <MapReduceInfos> <MapReduceInfo node=\"tSortRow_1\" groupId=\"1\"> <Map x=\"494\" y=\"244\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"614\" y=\"244\" width=\"122\" height=\"18\" mrId=\"1\"/> <Map x=\"494\" y=\"265\" width=\"101\" height=\"18\" mrId=\"2\"/> <Reduce x=\"614\" y=\"265\" width=\"122\" height=\"18\" mrId=\"2\"/> </MapReduceInfo> <MapReduceInfo node=\"tRowGenerator_1\" groupId=\"2\"> <Map x=\"246\" y=\"84\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_1\" groupId=\"3\"> <Map x=\"93\" y=\"244\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"213\" y=\"244\" width=\"122\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_2\" groupId=\"4\"> <Map x=\"207\" y=\"404\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"327\" y=\"404\" width=\"122\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_3\" groupId=\"5\"> <Map x=\"229\" y=\"564\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_4\" groupId=\"6\"> <Map x=\"229\" y=\"724\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> </MapReduceInfos> ";
        StringReader sr = new StringReader(value);
        SAXReader saxReader = new SAXReader();
        List<MapReduceInfo> toReturn = new ArrayList<MapReduceInfo>();
        try {
            Document doc = saxReader.read(sr);
            List<?> nodes = doc.selectNodes("//MapReduceInfos/MapReduceInfo");
            for (Object obj : nodes) {
                Element e = (Element) obj;
                // System.out.println(e.asXML());
                processMapReduceInfo(e, toReturn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processMapReduceInfo(Element parent, List<MapReduceInfo> toReturn) {
        List<?> children = parent.elements();
        String nodeName = parent.attributeValue("node");
        String groupId = parent.attributeValue("groupId");
        for (Object obj : children) {
            Element e = (Element) obj;
            // System.out.println(e.asXML());
            String tagName = e.getName();
            String mrId = e.attributeValue("mrId");
            int width = Integer.valueOf(e.attributeValue("width"));
            int height = Integer.valueOf(e.attributeValue("height"));
            Coordinate coordinate = new Coordinate();
            coordinate.setX(Integer.valueOf(e.attributeValue("x")));
            coordinate.setY(Integer.valueOf(e.attributeValue("y")));
            MapReduceInfo info = new MapReduceInfo(nodeName, tagName, groupId, mrId, width, height, coordinate);
            System.out.println(info);
        }
    }

    static void testA() {
        String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <MapReduceInfos> <MapReduceInfo node=\"tSortRow_1\" groupId=\"1\"> <Map x=\"494\" y=\"244\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"614\" y=\"244\" width=\"122\" height=\"18\" mrId=\"1\"/> <Map x=\"494\" y=\"265\" width=\"101\" height=\"18\" mrId=\"2\"/> <Reduce x=\"614\" y=\"265\" width=\"122\" height=\"18\" mrId=\"2\"/> </MapReduceInfo> <MapReduceInfo node=\"tRowGenerator_1\" groupId=\"2\"> <Map x=\"246\" y=\"84\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_1\" groupId=\"3\"> <Map x=\"93\" y=\"244\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"213\" y=\"244\" width=\"122\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_2\" groupId=\"4\"> <Map x=\"207\" y=\"404\" width=\"101\" height=\"18\" mrId=\"1\"/> <Reduce x=\"327\" y=\"404\" width=\"122\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_3\" groupId=\"5\"> <Map x=\"229\" y=\"564\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> <MapReduceInfo node=\"tHDFSInput_4\" groupId=\"6\"> <Map x=\"229\" y=\"724\" width=\"101\" height=\"18\" mrId=\"1\"/> </MapReduceInfo> </MapReduceInfos> ";
        StringReader sr = new StringReader(value);
        SAXReader saxReader = new SAXReader();
        try {
            Document doc = saxReader.read(sr);
            OutputFormat of = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(System.out, of);
            writer.write(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
