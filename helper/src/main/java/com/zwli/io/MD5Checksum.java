package com.zwli.io;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * zwli on May 12, 2014 10:13:22 AM<br>
 *
 * How to check virus and corrupted files in files transfer in socket programming<br>
 * You have to calculate the MD5 checksum for you file that you are sending, and after receiving the file, you again need to calculate the checksum, the previous checksum should be equal to the new one if the file has not been changes in between.
 */
public class MD5Checksum {

    private static final Logger logger = Logger.getLogger(MD5Checksum.class.getName());

    /**
     * Calculate checksum of a File using MD5 algorithm
     */
    public static String checkSum(String path) {
        String checksum = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                // Using MessageDigest update() method to provide input
                byte[] buffer = new byte[8192];
                int numOfBytesRead;
                while ((numOfBytesRead = fis.read(buffer)) > 0) {
                    md.update(buffer, 0, numOfBytesRead);
                }
                byte[] hash = md.digest();
                // checksum = new BigInteger(1, hash).toString(16); // don't use this, truncates leading zero
                checksum = String.format("%032x", new BigInteger(1, hash));
            } finally {
                if (null != fis) {
                    fis.close();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }

        return checksum;
    }

    /**
     * From Apache commons codec 1.7 md5() and md5Hex() method accepts InputStream as well.
     */
    public static String checkSumViaApache(String file) {
        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return checksum;
    }

    public static void main(String args[]) {
        String file = "E:/Temp/tmp.txt";

        System.out.println("MD5 checksum for file using Java :                          " + checkSum(file));
        System.out.println("MD5 checksum of file in Java using Apache commons codec:    " + checkSumViaApache(file));

    }

}
