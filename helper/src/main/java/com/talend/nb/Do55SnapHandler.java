package com.talend.nb;

import java.util.ArrayList;
import java.util.List;

import com.talend.nb.SnapHandler.Action;
import com.talend.nb.SnapHandler.VersionType;

public class Do55SnapHandler {

    public static void main(String[] args) {
        do55();
    }

    private static void do55() {
        String version = "V5.5.2SNAP";
        String revision = "20150119_1933";
        String sourceDir = "E:/Download";
        String licenceTag = "licenses_552_30days";
        String nbArchive = "E:/TUP/55";
        VersionType versionType = VersionType.V55;

        List<Action> actions = new ArrayList<Action>();

        actions.add(Action.MOVE_AND_UNZIP_LIB);
        actions.add(Action.DELETE_AND_MOVE);

        SnapHandler.doing(version, revision, versionType, sourceDir, licenceTag, nbArchive, actions);
    }
}
