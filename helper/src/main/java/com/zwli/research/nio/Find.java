package com.zwli.research.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Find {

    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("E:/_libs/apache/Commons/logging/commons-logging-1.2/src");
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(".java")) {
                // System.out.println(file.getFileName());
                // System.out.println(file.getParent());
                System.out.println(file.toString());
            }
            return FileVisitResult.CONTINUE;
        }

    }
}
