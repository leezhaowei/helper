package com.zwli.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

public class CompressArchiveUtil {

    public static File archiveTARFiles(File baseDir, String dirToArchive, String archiveNameWithOutExtension) {

        File tarFile = null;

        try {
            File[] list = (new File(baseDir, dirToArchive)).listFiles();
            tarFile = new File(baseDir, archiveNameWithOutExtension + ".tar");

            byte[] buf = new byte[1024];
            int len;

            // --------- TAR
            {
                TarArchiveOutputStream tos = new TarArchiveOutputStream(new FileOutputStream(tarFile));
                tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
                for (File file : list) {
                    TarArchiveEntry tarEntry = new TarArchiveEntry(file.getName());
                    tarEntry.setSize(file.length());

                    // Création du flux d'entrée
                    FileInputStream fin = new FileInputStream(file);
                    BufferedInputStream in = new BufferedInputStream(fin);
                    tos.putArchiveEntry(tarEntry);

                    // Lecture du fichier
                    while ((len = in.read(buf)) != -1) {
                        tos.write(buf, 0, len);
                    }

                    // Fermeture du flux d'entrée
                    in.close();

                    // Fermeture de l'entrée du tar
                    tos.closeArchiveEntry();

                    // on supprime le fichier
                    // file.delete();
                }// end-for
                tos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tarFile;
    }

    public static File compressGZIPFiles(File baseDir, File fileToCompressWithExtension, String compressNameWithOutExtension) {
        File tgzFile = null;

        try {
            tgzFile = new File(baseDir, compressNameWithOutExtension + ".tar.gz");

            byte[] buf = new byte[1024];
            int len;

            // --------- GZIP
            {
                GZIPOutputStream gz = new GZIPOutputStream(new FileOutputStream(tgzFile));
                FileInputStream in = new FileInputStream(fileToCompressWithExtension);

                // Transfer bytes from the input file to the GZIP output stream
                while ((len = in.read(buf)) > 0) {
                    gz.write(buf, 0, len);
                }

                in.close();

                // Complete the GZIP file
                gz.finish();
                gz.close();

                // Delete the TAR file
                // tarFile.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tgzFile;
    }

    public static File unCompressGZIPFiles(File baseDir, File fileToUnCompressWithExtension, String unCompressNameWithOutExtension) {
        File tgzFile = null;

        /**
         * The implementation of this package is provided by the java.util.zip package of the Java class library.
         * Uncompressing a given gzip compressed file (you would certainly add exception handling and make sure all
         * streams get closed properly)
         */
        try {

            FileInputStream fin = new FileInputStream(fileToUnCompressWithExtension);
            BufferedInputStream in = new BufferedInputStream(fin);
            tgzFile = new File(baseDir, unCompressNameWithOutExtension + ".tar");
            FileOutputStream out = new FileOutputStream(tgzFile);
            GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = gzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
            out.close();
            gzIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tgzFile;
    }

    private static String getRandomLocalFilename() {
        /*
         * long millisec = Calendar.getInstance().getTimeInMillis(); double randomDigit = Math.random(); StringBuffer
         * tempFileName = new StringBuffer(Long.toString(millisec)).append(randomDigit); return tempFileName.toString();
         */
        // OR
        return UUID.randomUUID().toString();
    }

    /**
     * Check the result in test_compress1\bin\jUnitResources
     * 
     * @param args
     */
    public static void main(String[] args) {

        // ARCHIVE TAR
        // = test_compress1\bin\jUnitResources
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("jUnitResources").getPath();
        String archiveNameWithOutExtension = getRandomLocalFilename();
        // = test_compress1\bin\jUnitResources\toCompress
        String dirToArchive = "toCompress"; // Folder containing the files to archive
        File tarFile = CompressArchiveUtil.archiveTARFiles(new File(baseDir), dirToArchive, archiveNameWithOutExtension);

        // COMPRESS GZIP
        String compressNameWithOutExtension = getRandomLocalFilename();
        File tgzFile1 = CompressArchiveUtil.compressGZIPFiles(new File(baseDir), tarFile, compressNameWithOutExtension);

        // UNCOMPRESS GZIP
        String unCompressNameWithOutExtension = getRandomLocalFilename();
        File tarFile2 = CompressArchiveUtil.unCompressGZIPFiles(new File(baseDir), tgzFile1, unCompressNameWithOutExtension);

        if (tarFile.length() == tarFile2.length()) { // SAME SIZE
            System.out.println("OK The files " + tarFile.getAbsolutePath() + " (" + tarFile.length() + ") and the file "
                    + tarFile2.getAbsolutePath() + " (" + tarFile2.length() + ") have the same size!!!");
        } else {
            System.out.println("NOK The files " + tarFile.getAbsolutePath() + " (" + tarFile.length() + ") and the file "
                    + tarFile2.getAbsolutePath() + " (" + tarFile2.length() + ") have NOT the same size!!!");
        }
    }
}
