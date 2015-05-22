package lzw.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * @author 李赵伟 Create: 2:13:25 PM Apr 3, 2008
 */
public class Fileutils {

    /**
     * The number of bytes in a kilobyte.
     */
    public static final long ONE_KB = 1024;

    /**
     * The number of bytes in a megabyte.
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The file copy buffer size (30 MB)
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
        copyFileToDirectory(srcFile, destDir, true);
    }

    public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (destDir.exists() && destDir.isDirectory() == false) {
            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
        }
        File destFile = new File(destDir, srcFile.getName());
        copyFile(srcFile, destFile, preserveFileDate);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (srcFile.exists() == false) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' exists but is a directory");
        }
        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
        }
        File parentFile = destFile.getParentFile();
        if (parentFile != null) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            }
        }
        if (destFile.exists() && destFile.canWrite() == false) {
            throw new IOException("Destination '" + destFile + "' exists but is read-only");
        }
        doCopyFile(srcFile, destFile, preserveFileDate);
    }

    private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' exists but is a directory");
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } finally {
            closeQuietly(output);
            closeQuietly(fos);
            closeQuietly(input);
            closeQuietly(fis);
        }

        if (srcFile.length() != destFile.length()) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
        }
        if (preserveFileDate) {
            destFile.setLastModified(srcFile.lastModified());
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * 文件大小，单位：KB
     * 
     * @param fileName 文件名称
     * @return
     */
    public static long fileSizeToKB(String fileName) {
        File f = new File(fileName);
        boolean exist = f.exists();
        boolean isfile = f.isFile();
        if (exist && isfile) {
            long fileSize = f.length();
            long fileKB = fileSize / 1024;
            return fileKB;
        } else {
            throw new IllegalArgumentException("文件可能不存在或者是目录！");
        }
    }

    /**
     * 把内容写道文件
     * 
     * @param fileName 文件名称
     * @param content 文件内容
     * @param append true，写入文件末尾；false，覆盖文件
     * @throws IOException
     */
    public static void write(String fileName, String content, boolean append) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        write(fileName, content.getBytes(), append);
    }

    /**
     * 复制文件
     * 
     * @param fileName
     * @param buf
     * @param append
     * @throws IOException
     */
    public static void write(String fileName, byte buf[], boolean append) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(f, append));
            dos.write(buf);
            dos.flush();
            System.out.println("写文件完毕：" + fileName);
        } finally {
            if (null != dos) {
                dos.close();
            }
        }
    }

    /**
     * 读取文件
     * 
     * @param fileName
     * @return 文件内容
     * @throws IOException
     */
    public static String read(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            System.err.println("文件：" + fileName + " 不存在！");
        }

        return read(new FileInputStream(f));
    }

    /**
     * 从数据流中读取文件内容
     * 
     * @param is
     * @return 文件内容
     * @throws IOException
     */
    public static String read(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String s;
            while (br.ready()) {
                s = br.readLine();
                if (null != s && 0 != s.trim().length()) {
                    sb.append(s.trim() + "\n");
                }
            }
            return sb.toString();
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    /**
     * 读取文件获得字节数组
     * 
     * @param fileName
     * @return 文件内容的字节形式
     * @throws IOException
     */
    public static byte[] readForByte(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            System.err.println("文件：" + fileName + " 不存在！");
        }
        return readForByte(new FileInputStream(f));
    }

    /**
     * 从数据流中获得字节数组
     * 
     * @param is
     * @return 数据流中的字节
     * @throws IOException
     */
    public static byte[] readForByte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            byte buf[] = new byte[8192];
            baos = new ByteArrayOutputStream();
            int len = is.read(buf);
            while (-1 != len) {
                baos.write(buf, 0, len);
                len = is.read(buf);
            }
            buf = baos.toByteArray();
            return buf;
        } finally {
            if (null != is) {
                is.close();
            }
            if (null != baos) {
                baos.close();
            }
        }
    }

}
