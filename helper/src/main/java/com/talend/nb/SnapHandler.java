package com.talend.nb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SnapHandler {

    public enum Action {
        MOVE_AND_UNZIP_LIB,
        UNZIP,
        DELETE_AND_MOVE,
        DELETE_AND_MOVE_STUDIO,
        DELETE_AND_MOVE_JOBSERVER,
        DELETE_AND_MOVE_WEBAPPS, ;
    }

    private enum FileType {
        FILE,
        DIRECTORY, ;
    }

    public enum VersionType {
        V60("60"),
        V56("56"),
        V55("55"),
        V54("54"),
        V53("53"),
        V52("52"), ;

        private String value;

        private VersionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    private static final String SUFFIX_ZIP = ".zip";

    private static final String SUFFIX_WAR = ".war";

    private static final String PREFIX_LICENCE = "CLOUD_EE_MPX";

    public static final String STUDIO_TAG = "Talend-Studio";

    private static final String STUDIO_FOLDER_TAG = "Studio";

    public static final String JOBSERVER_TAG = "Talend-JobServer";

    private static final String JOBSERVER_FOLDER_TAG = "JobServer";

    private static final String JOBSERVER_RESERVED_FOLDER_TAG = "TalendJobServersFiles";

    public static final String TAC_TAG = "Talend-AdministrationCenter";

    private static final String WEBAPPS_TAG = "webapps";

    private static final String WEBAPPS_RESERVED_TAG = "configuration.properties";

    private boolean printLog;

    private String version;

    private String revision;

    private VersionType versionType;

    // private static String[] RESERVED_FILE_NAME = { "lib", "commandline.bat", "migration.bat" };

    private static String[] RESERVED_FILE_NAME = { "commandline.bat", "migration.bat" };

    private FileFilter nbFilter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return false;
            }
            String fileName = pathname.getName();
            if (!fileName.endsWith(SUFFIX_ZIP)) {
                return false;
            }
            boolean versionOk = fileName.contains(version);
            boolean revisionOk = fileName.contains(revision);

            if (versionOk && revisionOk) {
                return true;
            }

            return false;
        }
    };

    private FileFilter warFilter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return false;
            }
            String fileName = pathname.getName();
            if (fileName.endsWith(SUFFIX_WAR)) {
                return true;
            }
            return false;
        }
    };

    private FileFilter licenceFilter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            String fileName = pathname.getName();
            if (fileName.startsWith(PREFIX_LICENCE)) {
                return true;
            }
            return false;
        }
    };

    private static Comparator<File> SORT_FILES = new Comparator<File>() {

        @Override
        public int compare(File o1, File o2) {
            return o2.compareTo(o1);
        }
    };

    public SnapHandler(String version, String revision, VersionType versionType) {
        super();
        this.version = version;
        this.revision = revision;
        this.versionType = versionType;
    }

    public String findNbArchiveFolderPath(String nbArchive) {
        File dir = new File(nbArchive);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory() && f.getAbsolutePath().contains(version) && f.getAbsolutePath().contains(revision)) {
                return f.getAbsolutePath();
            }
        }
        return null;
    }

    public void deleteAndCopyWebapps(String nbArchive) throws NbException {
        String webappsPath = nbArchive + File.separator + WEBAPPS_TAG;
        File webappsFolder = new File(webappsPath);
        if (!webappsFolder.exists() && !webappsFolder.isDirectory()) {
            webappsFolder.mkdirs();
        }

        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);

        log("### start delete " + WEBAPPS_TAG + " ...", true);
        deleteFolderContent(webappsFolder, new String[] { WEBAPPS_RESERVED_TAG });
        log(">>> end delete" + WEBAPPS_TAG, true);

        log("### start move " + WEBAPPS_TAG + " ...", true);
        moveContentToTargetFolder(webappsFolder, nbArchiveFolderPath, TAC_TAG, new String[] { SUFFIX_WAR });
        log(">>> end move" + WEBAPPS_TAG, true);

        log("### start unzip war ...", true);
        unzipWar(webappsFolder);
        log(">>> end unzip war", true);
    }

    public void modifyConfigurationFileInWebapps(String nbArchive) throws NbException {
        log("### start changing configuration.properties ...", true);
        String webappsPath = nbArchive + File.separator + WEBAPPS_TAG;
        String configFilePath = webappsPath + "/org.talend.administrator/WEB-INF/classes/configuration.properties";
        File configFile = new File(configFilePath);
        if (!configFile.exists()) {
            throw new NbException("There is no [configuration.properties]");
        }

        String tmpConfigFilePath = configFilePath + "_tmp";
        File tmpConfigFile = new File(tmpConfigFilePath);
        if (!tmpConfigFile.exists()) {
            tmpConfigFile.delete();
        }

        final String du = "database.url=jdbc:mysql://localhost:3306/tis" + this.versionType.getValue();
        final String dd = "database.driver=org.gjt.mm.mysql.Driver";
        final String newLine = "\n";
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpConfigFile)));

                String t = null;
                while (br.ready()) {
                    t = br.readLine();
                    if (t.startsWith("database.url")) {
                        bw.write(du + newLine);
                    } else if (t.startsWith("database.driver")) {
                        bw.write(dd + newLine);
                    } else {
                        bw.write(t + newLine);
                    }
                }
                bw.flush();

            } finally {
                if (null != br) {
                    br.close();
                }
                if (null != bw) {
                    bw.close();
                }
            }
        } catch (Exception e) {
            throw new NbException(e.getMessage(), e);
        }

        configFile.delete();
        tmpConfigFile.renameTo(configFile);

        log(">>> end changing configuration.properties ", true);
    }

    private void unzipWar(File webappsFolder) throws NbException {
        File[] files = webappsFolder.listFiles(warFilter);
        if (null == files || files.length == 0 || files.length > 1) {
            throw new NbException("There is not war in [" + webappsFolder.getAbsolutePath() + "]");
        }
        File war = files[0];
        // // log(war.getAbsolutePath());
        ZipUtil.unzip(war.getAbsolutePath(), webappsFolder.getAbsolutePath() + "/org.talend.administrator");
    }

    public void deleteAndMoveStudio(String nbArchive) throws NbException {
        String studioPath = nbArchive + File.separator + STUDIO_FOLDER_TAG;
        File studioFolder = new File(studioPath);
        if (!studioFolder.exists() && !studioFolder.isDirectory()) {
            studioFolder.mkdirs();
        }

        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);

        log("### start delete " + STUDIO_FOLDER_TAG + " ...", true);
        deleteFolderContent(studioFolder, null);
        log(">>> end delete" + STUDIO_FOLDER_TAG, true);

        log("### start move " + STUDIO_FOLDER_TAG + " ...", true);
        moveContentToTargetFolder(studioFolder, nbArchiveFolderPath, STUDIO_TAG, null);
        log(">>> end move" + STUDIO_FOLDER_TAG, true);
    }

    public void deleteAndMoveJobServer(String nbArchive) throws NbException {
        String jobServerPath = nbArchive + File.separator + JOBSERVER_FOLDER_TAG;
        File jobServerFolder = new File(jobServerPath);
        if (!jobServerFolder.exists() && !jobServerFolder.isDirectory()) {
            jobServerFolder.mkdirs();
        }

        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);

        log("### start delete " + JOBSERVER_FOLDER_TAG + " ...", true);
        deleteFolderContent(jobServerFolder, new String[] { JOBSERVER_RESERVED_FOLDER_TAG });
        log(">>> end delete" + JOBSERVER_FOLDER_TAG, true);

        log("### start move " + JOBSERVER_FOLDER_TAG + " ...", true);
        moveContentToTargetFolder(jobServerFolder, nbArchiveFolderPath, JOBSERVER_TAG, null);
        log(">>> end move" + JOBSERVER_FOLDER_TAG, true);
    }

    private boolean moveTarget(String[] target, String absolutePath) {
        for (String t : target) {
            if (absolutePath.contains(t)) {
                return true;
            }
        }
        return false;
    }

    private void moveContentToTargetFolder(File targetFolder, String nbArchiveFolderPath, String tag, String[] target)
            throws NbException {
        File unzippedDir = find(FileType.DIRECTORY, tag, nbArchiveFolderPath);
        File dir = new File(nbArchiveFolderPath);
        File[] files = dir.listFiles();

        if (null == unzippedDir) {
            throw new NbException("There is no unzipped folder [" + nbArchiveFolderPath + "]");
        }

        List<String> failedToMove = new ArrayList<String>();
        files = unzippedDir.listFiles();
        int count = 0;
        for (File f : files) {
            String t = null;
            count++;
            try {
                if (null == target) {
                    if (f.isDirectory()) {
                        FileUtils.copyDirectoryToDirectory(f, targetFolder);
                        t = " directory ";
                    } else {
                        FileUtils.copyFileToDirectory(f, targetFolder);
                        t = " file ";
                    }
                } else if (moveTarget(target, f.getAbsolutePath())) {
                    if (f.isDirectory()) {
                        FileUtils.copyDirectoryToDirectory(f, targetFolder);
                        t = " directory ";
                    } else {
                        FileUtils.copyFileToDirectory(f, targetFolder);
                        t = " file ";
                    }
                }

                if (null != t) {
                    if (count <= 5) {
                        log("--- move" + t + f.getAbsolutePath());
                    } else {
                        print(count);
                    }
                }
            } catch (IOException e) {
                failedToMove.add(f.getAbsolutePath());
            }
        }
        System.out.println();
        if (!failedToMove.isEmpty()) {
            log("### failed to move " + failedToMove.size(), true);
            for (String s : failedToMove) {
                log(" --- " + s, true);
            }
        }
        try {
            FileUtils.deleteDirectory(unzippedDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFolderContent(File targetFolder, String[] reservedFile) {
        File[] files = targetFolder.listFiles();
        List<String> failedToDelete = new ArrayList<String>();
        int count = 0;
        for (File f : files) {
            String t = null;
            count++;
            try {
                if (f.isDirectory()) {
                    if (!isReservedFile(f.getAbsolutePath(), reservedFile)) {
                        FileUtils.deleteDirectory(f);
                        t = " directory ";
                    }
                } else {
                    if (!isReservedFile(f.getAbsolutePath(), reservedFile)) {
                        f.delete();
                        t = " file ";
                    }
                }

                if (null != t) {
                    if (count <= 5) {
                        log("--- delete" + t + f.getAbsolutePath());
                    } else {
                        print(count);
                    }
                }
            } catch (IOException e) {
                // throw new NbException(e);
                failedToDelete.add(f.getAbsolutePath());
            }
        }
        System.out.println();
        if (!failedToDelete.isEmpty()) {
            log("### failed to delete " + failedToDelete.size(), true);
            for (String s : failedToDelete) {
                log(" --- " + s, true);
            }
        }
    }

    private void print(int count) {
        if (count % 180 == 0) {
            System.out.println();
        } else {
            System.out.print('.');
        }
    }

    private boolean isReservedFile(String filePath, String[] reservedFile) {
        if (null == reservedFile) {
            return false;
        }
        for (String t : reservedFile) {
            if (filePath.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public void copyLicenseToStudio(String sourceDir, String licenseTag, String nbArchive) throws NbException {
        log("### start copy license to studio ...", true);
        File unzippedStudioDir = checkUnzippedStudioDir(nbArchive);
        File license = null;
        try {
            license = findLicense(sourceDir, licenseTag);
        } catch (Exception e) {
            log(e.getMessage());
            return;
        }
        File licenseInStudio = new File(unzippedStudioDir, "license");

        // log(licenseInStudio.getAbsolutePath());
        log(license.getAbsolutePath());

        try {
            FileUtils.copyFile(license, licenseInStudio);
        } catch (IOException e) {
            throw new NbException(e);
        }

        log("### end copy license to studio", true);
    }

    private File findLicense(String sourceDir, final String licenceTag) throws NbException {
        File[] dirs = new File(sourceDir).listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory() && pathname.getName().startsWith(licenceTag)) {
                    return true;
                }
                return false;
            }
        });

        if (null == dirs || dirs.length == 0) {
            throw new NbException("There is no licence folder in [" + sourceDir + "]");
        }

        if (dirs.length > 1) {
            Arrays.sort(dirs, SORT_FILES);
        }

        // for (File file : files) {
        // log(file.getAbsolutePath());
        // }
        return findLicence(dirs[0]);
    }

    private File findLicence(File dir) throws NbException {
        File[] files = dir.listFiles(licenceFilter);
        if (null == files || files.length == 0 || files.length > 1) {
            throw new NbException("There is no correct licence in [" + dir.getAbsolutePath() + "]");
        }
        return files[0];
    }

    public void copyStudioLibs(String nbArchive) throws NbException {
        log("### start copy studio libs ...", true);
        File unzippedStudioDir = checkUnzippedStudioDir(nbArchive);

        File f = null;
        String[] toCopyToStuido = new String[RESERVED_FILE_NAME.length];
        try {
            for (int i = 0; i < RESERVED_FILE_NAME.length; i++) {
                toCopyToStuido[i] = nbArchive + File.separator + RESERVED_FILE_NAME[i];
                // log(toCopyToStuido[i], printLog);

                f = new File(toCopyToStuido[i]);
                if (f.isDirectory()) {
                    FileUtils.copyDirectoryToDirectory(f, unzippedStudioDir);
                } else {
                    FileUtils.copyFileToDirectory(f, unzippedStudioDir);
                }
                log("--- copy to stuido " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new NbException(e);
        }
        log("### end copy studio libs", true);
    }

    private File checkUnzippedStudioDir(String nbArchive) throws NbException {
        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);
        File unzippedStudioDir = find(FileType.DIRECTORY, STUDIO_TAG, nbArchiveFolderPath);

        if (null == unzippedStudioDir) {
            throw new NbException("There is no unzipped Studio folder [" + nbArchiveFolderPath + "]");
        }
        return unzippedStudioDir;
    }

    public void unzip(String nbArchive) {
        log("### start unzip archives ...", true);
        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);
        File dir = new File(nbArchiveFolderPath);
        File[] nbs = dir.listFiles();
        for (File nb : nbs) {
            ZipUtil.unzip(nb.getAbsolutePath(), nbArchiveFolderPath);
        }
        log(">>> end unzip archives", true);
    }

    public void unzip(String nbArchive, String tag) throws NbException {
        log("### start unzip [" + tag + "] archives ...", true);
        String nbArchiveFolderPath = findNbArchiveFolderPath(nbArchive);
        File zipFile = find(FileType.FILE, tag, nbArchiveFolderPath);

        log(zipFile.getAbsolutePath());
        ZipUtil.unzip(zipFile.getAbsolutePath(), nbArchiveFolderPath);

        log(">>> end unzip [" + tag + "] archives", true);
    }

    private File find(FileType type, String tag, String nbArchiveFolderPath) throws NbException {
        File target = null;
        File dir = new File(nbArchiveFolderPath);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.getName().startsWith(tag)) {
                if ((FileType.FILE == type && !f.isFile()) || (FileType.DIRECTORY == type && !f.isDirectory())) {
                    continue;
                }
                target = f;
            }
        }

        if (null == target) {
            throw new NbException("There is no [" + tag + "] in [" + nbArchiveFolderPath + "]");
        }
        return target;
    }

    public String move(String sourceDir, String nbArchive) throws NbException {
        log("### start move the NB[" + version + ", " + revision + "] ###\n", true);

        checkSourceDir(sourceDir);
        String nbArchiveFolderName = parseNbArchiveFolderName(sourceDir);
        checkNbArchive(nbArchiveFolderName, nbArchive);

        String nbArchiveFolderPath = buildNbArchiveFolderPath(nbArchiveFolderName, nbArchive);
        moveNBToArchive(sourceDir, nbArchiveFolderPath);

        log("\n>>> end move the NB[" + version + ", " + revision + "] ###", true);
        return nbArchiveFolderPath;
    }

    private void moveNBToArchive(String sourceDir, String nbArchiveFolderPath) throws NbException {
        File[] nbs = listNBs(sourceDir);

        File f = new File(nbArchiveFolderPath);
        try {
            for (File nb : nbs) {
                // log(nb);
                // String filePath = nbArchiveFolderPath + File.separator + nb.getName();
                // f = new File(filePath);
                log("  --- move [" + nb.getAbsolutePath() + "] to [" + nbArchiveFolderPath + "]");
                FileUtils.copyFileToDirectory(nb, f);
                log("  --- DONE [" + nb.getAbsolutePath() + "] to [" + nbArchiveFolderPath + "]\n");
            }
        } catch (IOException e) {
            throw new NbException(e);
        }
    }

    private String parseNbArchiveFolderName(String sourceDir) {
        File dir = new File(sourceDir);
        String nbName = dir.listFiles(nbFilter)[0].getName();
        nbName = nbName.replace(SUFFIX_ZIP, "");
        String[] arr = nbName.split("-");
        int length = arr.length;
        nbName = arr[length - 2] + "-" + arr[length - 1];
        // log(nbName);
        return nbName;
    }

    private void checkNbArchive(String nbArchiveFolderName, String nbArchive) throws NbException {
        File dir = new File(nbArchive);
        if (!dir.isDirectory()) {
            throw new NbException("[" + nbArchive + "] is incorrent.");
        }
        String nbArchiveFolderPath = buildNbArchiveFolderPath(nbArchiveFolderName, nbArchive);
        File folder = new File(nbArchiveFolderPath);
        if (folder.exists()) {
            if (FileUtils.sizeOfDirectory(folder) > 0) {
                throw new NbException("[" + nbArchive + "] already have NB: " + nbArchiveFolderName);
            }
        } else {
            folder.mkdirs();
        }
    }

    private String buildNbArchiveFolderPath(String nbArchiveFolderName, String nbArchive) {
        String nbArchiveFolder = nbArchive + File.separator + nbArchiveFolderName;
        return nbArchiveFolder;
    }

    private void checkSourceDir(String sourceDir) throws NbException {
        File[] nbs = listNBs(sourceDir);
        // for (File f : nbs) {
        // log(f.getAbsolutePath());
        // }

        if (nbs.length < 3) {
            throw new NbException("[" + sourceDir + "] doesn't have NB: " + version + ", " + revision);
        }
    }

    private File[] listNBs(String sourceDir) throws NbException {
        File dir = new File(sourceDir);
        if (!dir.isDirectory()) {
            throw new NbException("[" + sourceDir + "] is incorrent.");
        }

        File[] nbs = dir.listFiles(nbFilter);
        return nbs;
    }

    private void log(String msg) {
        if (printLog) {
            log(msg, printLog);
        }
    }

    private void log(String msg, boolean printLog) {
        if (printLog) {
            System.out.println(msg);
        }
    }

    public void setPrintLog(boolean printLog) {
        this.printLog = printLog;
    }

    public static void doing(String version, String revision, VersionType versionType, String sourceDir, String licenseTag,
            String nbArchive, List<Action> actionList) {
        SnapHandler handler = new SnapHandler(version, revision, versionType);
        boolean toPrintLog = true;
        handler.setPrintLog(toPrintLog);
        ZipUtil.setPrintLog(toPrintLog);

        try {
            if (actionList.contains(Action.MOVE_AND_UNZIP_LIB)) {
                handler.move(sourceDir, nbArchive);
                handler.unzip(nbArchive, STUDIO_TAG);
                handler.copyStudioLibs(nbArchive);
                handler.copyLicenseToStudio(sourceDir, licenseTag, nbArchive);
            }

            if (actionList.contains(Action.DELETE_AND_MOVE_STUDIO) || actionList.contains(Action.DELETE_AND_MOVE)) {
                handler.deleteAndMoveStudio(nbArchive);
            }
            if (actionList.contains(Action.DELETE_AND_MOVE_JOBSERVER) || actionList.contains(Action.DELETE_AND_MOVE)) {
                handler.unzip(nbArchive, JOBSERVER_TAG);
                handler.deleteAndMoveJobServer(nbArchive);
            }
            if (actionList.contains(Action.DELETE_AND_MOVE_WEBAPPS) || actionList.contains(Action.DELETE_AND_MOVE)) {
                // ZipUtil.setPrintLog(toPrintLog);
                handler.unzip(nbArchive, TAC_TAG);
                handler.deleteAndCopyWebapps(nbArchive);
                handler.modifyConfigurationFileInWebapps(nbArchive);
            }
        } catch (NbException e) {
            e.printStackTrace();
        }
    }

    // public static void doing(String version, String revision, VersionType versionType, String sourceDir, String
    // licenseTag,
    // String nbArchive, List<Action> actionList) {
    // SnapHandler handler = new SnapHandler(version, revision, versionType);
    // boolean toPrintLog = true;
    // handler.setPrintLog(toPrintLog);
    // ZipUtil.setPrintLog(toPrintLog);
    //
    // // if (checkIfDeletionOperation(actionList)) {
    // // System.out.println("Are you sure you want to delete something? (YES to carry on)");
    // // Scanner sc = new Scanner(System.in);
    // // String input = sc.nextLine();
    // // sc.close();
    // //
    // // if (!"YES".equals(input)) {
    // // return;
    // // }
    // // }
    //
    // try {
    // if (actionList.contains(Action.MOVE_AND_UNZIP_LIB) && actionList.size() == 1) {
    // handler.move(sourceDir, nbArchive);
    // handler.unzip(nbArchive, STUDIO_TAG);
    // handler.copyStudioLibs(nbArchive);
    // handler.copyLicenseToStudio(sourceDir, licenseTag, nbArchive);
    // }
    //
    // if (!actionList.contains(Action.MOVE_AND_UNZIP_LIB)
    // && (actionList.contains(Action.DELETE_AND_MOVE_STUDIO) || actionList.contains(Action.DELETE_AND_MOVE))) {
    // handler.deleteAndMoveStudio(nbArchive);
    // }
    // if (!actionList.contains(Action.MOVE_AND_UNZIP_LIB)
    // && (actionList.contains(Action.DELETE_AND_MOVE_JOBSERVER) || actionList.contains(Action.DELETE_AND_MOVE))) {
    // handler.unzip(nbArchive, JOBSERVER_TAG);
    // handler.deleteAndMoveJobServer(nbArchive);
    // }
    // if (!actionList.contains(Action.MOVE_AND_UNZIP_LIB)
    // && (actionList.contains(Action.DELETE_AND_MOVE_WEBAPPS) || actionList.contains(Action.DELETE_AND_MOVE))) {
    // // ZipUtil.setPrintLog(toPrintLog);
    // handler.unzip(nbArchive, TAC_TAG);
    // handler.deleteAndCopyWebapps(nbArchive);
    // handler.modifyConfigurationFileInWebapps(nbArchive);
    // }
    // } catch (NbException e) {
    // e.printStackTrace();
    // }
    // }

    // private static boolean checkIfDeletionOperation(List<Action> actionList) {
    // if (actionList.size() > 1 && actionList.contains(Action.MOVE_AND_UNZIP_LIB)) {
    // System.out.println("Wrong command: [" + Action.MOVE_AND_UNZIP_LIB + "]");
    // return false;
    // }
    //
    // List<Action> actions = new ArrayList<Action>(4);
    // actions.add(Action.DELETE_AND_MOVE);
    // actions.add(Action.DELETE_AND_MOVE_JOBSERVER);
    // actions.add(Action.DELETE_AND_MOVE_STUDIO);
    // actions.add(Action.DELETE_AND_MOVE_WEBAPPS);
    // for (Action action : actions) {
    // if (actionList.contains(action)) {
    // return true;
    // }
    // }
    //
    // return false;
    // }

    // public static void main(String[] args) {
    // test();
    // }
    //
    // private static void test() {
    // String version = "V5.6.0SNAP";
    // String revision = "20140809_0740";
    // String sourceDir = "E:/Download";
    // String licenceTag = "licenses_560_30days";
    // String nbArchive = "E:/TUP/56";
    // VersionType versionType = VersionType.V56;
    //
    // SnapHandler handler = new SnapHandler(version, revision, versionType);
    // boolean toPrintLog = true;
    // handler.setPrintLog(toPrintLog);
    // ZipUtil.setPrintLog(toPrintLog);
    //
    // try {
    // handler.modifyConfigurationFileInWebapps(nbArchive);
    // } catch (NbException e) {
    // e.printStackTrace();
    // }
    // }
}
