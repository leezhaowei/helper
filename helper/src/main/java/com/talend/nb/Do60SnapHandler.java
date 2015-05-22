package com.talend.nb;

import java.util.ArrayList;
import java.util.List;

import com.talend.nb.SnapHandler.Action;
import com.talend.nb.SnapHandler.VersionType;

public class Do60SnapHandler {

    public static void main(String[] args) {
        do60();
    }

    private static void do60() {
        String version = "V6.0.0SNAPSHOT";
        String revision = "20150520_1932";
        String sourceDir = "E:/Download";
        String licenceTag = "licenses_600_30days";
        String nbArchive = "E:/TUP/60";
        VersionType versionType = VersionType.V60;

        List<Action> actions = new ArrayList<Action>();

        actions.add(Action.MOVE_AND_UNZIP_LIB);
        actions.add(Action.DELETE_AND_MOVE);

        SnapHandler.doing(version, revision, versionType, sourceDir, licenceTag, nbArchive, actions);
    }
}
