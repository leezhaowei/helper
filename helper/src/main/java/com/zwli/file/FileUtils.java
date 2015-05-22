package com.zwli.file;

import java.io.File;
import java.util.Arrays;

public class FileUtils {

    public static void main(String[] args) {
        listFiles();
    }

    static void listFiles() {
        String dir = "E:/_private/Photograph/婚礼仪式照片65";
        File f = new File(dir);
        String[] arr = f.list();
        System.out.println(Arrays.toString(arr));
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
