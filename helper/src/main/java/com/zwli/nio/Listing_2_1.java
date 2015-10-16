package com.zwli.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zwli on 2015年10月14日
 */
public class Listing_2_1 {

    public static void main(String[] args) {
        // testA();
        testB();
    }

    public static void testB() {
        Path dir = Paths.get("D:/Program Files/BaiduYunGuanjia");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.exe")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testA() {
        // Path listing = Paths.get("/usr/bin/zip");
        Path listing = Paths.get("D:/_maven/bin");
        System.out.println("File name [" + listing.getFileName() + "]");
        System.out.println("Number of Name Elements in the path [" + listing.getNameCount() + "]");
        System.out.println("Parent Path [" + listing.getRoot() + "]");
        System.out.println("Root of Path [" + listing.getRoot() + "]");
        System.out.println("Subpath from Rootm 2 elements deep [" + listing.subpath(0, 2) + "]");
    }

}
