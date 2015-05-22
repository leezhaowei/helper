package com.zwli.maven.ivy;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class IvyToPomHandler {

    public static void buildDependenciesFromIvyToPom(String ivyFile, String pomFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(ivyFile));

        String xpathExpression = "//ivy-module/dependencies/dependency";

        List<?> list = document.selectNodes(xpathExpression);
        // System.out.println(list.size());
        Node node = null;
        for (Object object : list) {
            node = (Node) object;
            buildDependenciesFromIvyToPom(pomFile, node);
        }

        // node = (Node) list.get(0);
        // buildDependenciesFromIvyToPom(pomFile, node);
    }

    private static void buildDependenciesFromIvyToPom(String pomFile, Node node) {
        // <dependency><groupId>com.google.gwt</groupId><artifactId>gwt-servlet</artifactId><version>${gwtVersion}</version></dependency>

        // System.out.println(node.asXML());

        String artifactId = node.valueOf("@name");
        String version = node.valueOf("@rev");
        String groupId = IvyToPomMap.getGroupId(artifactId);

        if (null == groupId) {
            System.out.println("++++++++++++");
            System.out.println(groupId);
            System.out.println(artifactId);
            System.out.println(version);
            System.out.println("++++++++++++");
            return;
        }

        String maven = "<dependency><groupId>" + groupId + "</groupId><artifactId>" + artifactId + "</artifactId><version>"
                + version + "</version></dependency>";
        System.out.println(maven);
    }

    public static void main(String[] args) {
        callBuildDependenciesFromIvyToPom();
    }

    private static void callBuildDependenciesFromIvyToPom() {
        String ivyFile = IvyToPomHandler.class.getResource("ivy.xml").getFile();
        // System.out.println(ivyFile);
        String pomFile = "E:/Temp/ivyToMaven/pom.xml";
        try {
            buildDependenciesFromIvyToPom(ivyFile, pomFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
