package com.talend.nb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class ZipUtil {

    private static String ZIP_SUFFIX = ".zip";

    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static String BACKSLASH = "/";

    private static String DOUBLE_SLASH = "\\";

    private static boolean PRINT_LOG = false;

    public static void setPrintLog(boolean printLog) {
        PRINT_LOG = printLog;
    }

    /**
     * Compressing everything in folder directoryPath and name the zip as same as this folder.
     * 
     * @param directoryPath
     * @return
     * @throws IOException
     */
    public static File zipDirectory(String directoryPath) throws IOException {
        return zipFiles(directoryPath, null, null);
    }

    public static File zipDirectory(String directoryPath, String zipFileName) throws IOException {
        return zipFiles(directoryPath, zipFileName, null);
    }

    /**
     * @param rootPath directory which contains files
     * @param zipFileName
     * @param files files to zip
     * @return
     * @throws IOException
     */
    public static File zipFiles(String rootPath, String zipFileName, File[] files) throws IOException {
        String zipPath = createZipPath(rootPath, zipFileName);
        File zipFile = new File(zipPath);

        ZipArchiveOutputStream zipOut = null;
        if (null == files) {
            File dir = new File(rootPath);
            files = dir.listFiles();
        }
        try {
            zipOut = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            addFilesToZip(zipOut, zipFile.getName(), files, "");
            log("### Compressed successfully [" + zipFile.getAbsolutePath() + "]", true);
            return zipFile;
        } finally {
            zipOut.finish();
            zipOut.close();
        }
    }

    private static String createZipPath(String directoryPath, String zipFileName) {
        File dir = new File(directoryPath);
        checkDirectory(dir);

        if (null == zipFileName) {
            zipFileName = dir.getName() + ZIP_SUFFIX;
        }

        return directoryPath + File.separator + zipFileName;
    }

    private static void checkDirectory(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("[" + dir.getAbsolutePath() + "] is not correct.");
        }
    }

    /**
     * Creates a zip entry for the path specified with a name built from the base passed in and the file/directory name.
     * If the path is a directory, a recursive call is made such that the full directory is added to the zip.
     * 
     * @param zipOut The zip file's output stream
     * @param zipFileName the zip file name
     * @param filesToZip files to zip
     * @param basePath The base prefix to for the name of the zip file entry
     * @throws IOException
     */
    private static void addFilesToZip(ZipArchiveOutputStream zipOut, String zipFileName, File[] filesToZip, String basePath)
            throws IOException {
        FileInputStream fis = null;
        for (File f : filesToZip) {
            if (zipFileName.equals(f.getName())) {
                continue;
            }
            String entryName = basePath + f.getName();
            ZipArchiveEntry zipEntry = new ZipArchiveEntry(f, entryName);
            zipOut.putArchiveEntry(zipEntry);
            if (f.isFile()) {
                try {
                    log("--- zip file:[" + f.getAbsolutePath() + "]", PRINT_LOG);
                    fis = new FileInputStream(f);
                    IOUtils.copy(fis, zipOut, 1024 * 8);
                    zipOut.closeArchiveEntry();
                } finally {
                    fis.close();
                }
            } else {
                log("--- zip directory: [" + f.getAbsolutePath() + "]", PRINT_LOG);
                zipOut.closeArchiveEntry();
                File[] children = f.listFiles();
                if (null != children) {
                    addFilesToZip(zipOut, zipFileName, children, entryName + File.separator);
                }
            }
        }
    }

    // /**
    // * Creates a zip file at the specified path with the contents of the specified directory.
    // *
    // * @param directoryPath: The path of the directory where the archive will be created.
    // * @param zipPath: The full path of the archive to create.
    // * @throws IOException: If anything goes wrong
    // */
    // public static void createZipRecursively(String directoryPath, String zipPath) throws IOException {
    // File zipFile = new File(zipPath);
    // ZipArchiveOutputStream zipOut = null;
    // try {
    // zipOut = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
    // addFileToZipRecursively(zipOut, zipFile.getName(), directoryPath, "");
    // } finally {
    // zipOut.finish();
    // zipOut.close();
    // }
    // }
    // /**
    // * Creates a zip entry for the path specified with a name built from the base passed in and the file/directory
    // name.
    // * If the path is a directory, a recursive call is made such that the full directory is added to the zip.
    // *
    // * @param zipOut The zip file's output stream
    // * @param path The filesystem path of the file/directory being added
    // * @param base The base prefix to for the name of the zip file entry
    // * @throws IOException If anything goes wrong
    // */
    // private static void addFileToZipRecursively(ZipArchiveOutputStream zipOut, String zipFileName, String path,
    // String base)
    // throws IOException {
    // File f = new File(path);
    // if (zipFileName.equals(f.getName())) {
    // return;
    // }
    // String entryName = base + f.getName();
    // ZipArchiveEntry zipEntry = new ZipArchiveEntry(f, entryName);
    // zipOut.putArchiveEntry(zipEntry);
    // if (f.isFile() && !zipFileName.equals(f.getName())) {
    // FileInputStream fis = null;
    // try {
    // log("--- addFileToZipRecursively file [" + f.getAbsolutePath() + "]");
    // fis = new FileInputStream(f);
    // IOUtils.copy(fis, zipOut);
    // zipOut.closeArchiveEntry();
    // } finally {
    // fis.close();
    // }
    //
    // } else {
    // log("--- addFileToZipRecursively directories [" + f.getAbsolutePath() + "]");
    // zipOut.closeArchiveEntry();
    // File[] children = f.listFiles();
    // if (children != null) {
    // for (File child : children) {
    // addFileToZipRecursively(zipOut, zipFileName, child.getAbsolutePath(), entryName + "/");
    // }
    // }
    // }
    // }

    /**
     * Extract zip file at the specified target path.
     * 
     * @param zipPath path to zip file
     * @param targetPath path to extract zip file to. Created if it doesn't exist.
     * @param printLog print log
     */
    public static void unzip(String zipPath, String targetPath) {
        File f = new File(zipPath);
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(f);
            byte[] buf = new byte[1024 * 8];
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            int count = 0;
            while (entries.hasMoreElements()) {
                ZipArchiveEntry zipEntry = entries.nextElement();
                String name = zipEntry.getName().replace(DOUBLE_SLASH, BACKSLASH);
                File targetFile = new File(targetPath + File.separator + name);

                // if (name.endsWith(BACKSLASH)) {
                if (zipEntry.isDirectory()) {
                    if (!targetFile.exists() && !targetFile.isDirectory()) {
                        targetFile.mkdirs();
                    }
                    continue;
                } else {
                    File parentFolder = targetFile.getParentFile();
                    if (!parentFolder.exists() && !parentFolder.isDirectory()) {
                        parentFolder.mkdirs();
                    }
                    count++;

                    if (count <= 5) {
                        log("--- unzip file [" + targetFile.getAbsolutePath() + "]", PRINT_LOG);
                    } else if (count % 180 == 0) {
                        System.out.println();
                    } else {
                        System.out.print('-');
                    }

                    BufferedInputStream bis = null;
                    BufferedOutputStream bos = null;
                    try {
                        bos = new BufferedOutputStream(new FileOutputStream(targetFile));
                        int len = -1;
                        bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                        while ((len = bis.read(buf)) != -1) {
                            if (len > 0) {
                                bos.write(buf, 0, len);
                            }
                            bos.flush();
                        }
                    } finally {
                        if (null != bos) {
                            bos.close();
                        }
                        if (null != bis) {
                            bis.close();
                        }
                    }
                }
            }
            log("\n### Uncompressed successfully [" + zipPath + "] => [" + targetPath + "]", true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zipFile) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void log(String msg, boolean printLog) {
        if (printLog) {
            System.out.println(msg);
        }
    }

    private static String tag() {
        return SDF.format(Calendar.getInstance().getTime());
        // return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws IOException {
        // test();
        // unzipBuild();
        unzipStudio();
    }

    static void unzipBuild() throws IOException {
        String buildPath = "D:/TalendGit/tac/org.talend.administrator/target";
        File dir = new File(buildPath);
        if (!dir.exists() || !dir.isDirectory()) {
            log("There is no build war.", true);
            return;
        }
        File[] files = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile() && pathname.getName().endsWith(".war")) {
                    return true;
                }
                return false;
            }
        });

        if (null == files || files.length == 0 || files.length > 1) {
            log("There is no build war.", true);
            return;
        }

        String zipPath = "E:/TAC/builds/Administrator.war";
        zipPath = files[0].getAbsolutePath();
        String targetPath = "E:/TAC/builds/Administrator";

        File f = new File(targetPath);
        if (f.isFile()) {
            f.deleteOnExit();
        }

        // setPrintLog(true);
        unzip(zipPath, targetPath);
    }

    static void test() throws IOException {
        String baseDir = "E:/Temp/toTestZip";
        String dirToUnuZip = "E:/Temp/unzipped";
        String zipFileName = tag() + ".zip";

        File zipFile = zipDirectory(baseDir, zipFileName);
        unzip(zipFile.getAbsolutePath(), dirToUnuZip + File.separator + zipFile.getName().replace(".zip", ""));

        // File[] files = new File[3];
        // int index = 0;
        // files[index++] = new File(baseDir + "/toCompress/java.security");
        // files[index++] = new File(baseDir + "/toCompress/java.policy");
        // files[index++] = new File(baseDir + "/toCompress/a");
        // zipFiles(baseDir + File.separator + dirToZip, files);
    }

    static void unzipStudio() {
        String zipPath = "e:/Download/Talend-Studio-r118258-V5.5.1.zip";
        String targetPath = "E:/Download/Studio";

        File f = new File(targetPath);
        if (f.isFile()) {
            f.deleteOnExit();
        }

        setPrintLog(true);
        unzip(zipPath, targetPath);

    }
}
