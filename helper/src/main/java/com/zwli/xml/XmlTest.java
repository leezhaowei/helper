package com.zwli.xml;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlTest {

    public static void main(String[] args) {
        try {
            double d = test();
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String KEY_FAILURES = "failures";

    private static String KEY_TESTS = "tests";

    static double test() throws DocumentException {
        String unzipFolder = "E:/Temp/testZip/tJava_test-0.1";
        File unzipFile = new File(unzipFolder);
        File[] dirs = unzipFile.listFiles(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory() && f.getName().endsWith(".tests")) {
                    return true;
                }
                return false;
            }
        });
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (File file : dirs) {
            extractTestXml(file, result);
        }

        Integer failures = result.get(KEY_FAILURES);
        if (null != failures && failures > 0) {
            int tests = result.get(KEY_TESTS);
            return failures / tests;
        }
        return 1;
    }

    static void extractTestXml(File dir, Map<String, Integer> result) throws DocumentException {
        if (dir.isDirectory()) {
            String[] testXmls = dir.list();
            for (String xmlFile : testXmls) {
                extractJunitResults(dir.getAbsolutePath() + "/" + xmlFile, result);
            }
        }
    }

    static void extractJunitResults(String xmlFile, Map<String, Integer> result) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(xmlFile);
        Element node = (Element) doc.selectSingleNode("//testsuite");
        String value = node.attributeValue(KEY_FAILURES);
        Integer failures = Integer.parseInt(value);
        value = node.attributeValue(KEY_TESTS);
        Integer tests = Integer.parseInt(value);
        if (!result.containsKey(KEY_FAILURES)) {
            result.put(KEY_FAILURES, failures);
        } else {
            Integer v = result.get(KEY_FAILURES);
            result.put(KEY_FAILURES, v + failures);
        }
        if (!result.containsKey(KEY_TESTS)) {
            result.put(KEY_TESTS, tests);
        } else {
            Integer v = result.get(KEY_TESTS);
            result.put(KEY_TESTS, v + tests);
        }
    }

    static void printXml(Document doc) throws UnsupportedEncodingException, IOException {
        OutputFormat outFormat = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(System.out, outFormat);
        xmlWriter.write(doc);
        xmlWriter.flush();
    }
}
