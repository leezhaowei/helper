package com.zwli.log4j;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;

/**
 * zwli on Apr 29, 2014 3:06:04 PM<br>
 */
public class Log4jInitializer {

    public static void main(String[] args) {
        String log4j = "log4j.properties";
        URL url = Log4jInitializer.class.getResource(log4j);
        System.out.println(url.getFile());
        try {
            List<String> list = IOUtils.readLines(url.openStream());
            for (String s : list) {
                System.out.println(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void initLog4j() {
        String log4j = "log4j.properties";
        // System.err.println(">>> " + log4j);
        PropertyConfigurator.configure(log4j);
    }
}
