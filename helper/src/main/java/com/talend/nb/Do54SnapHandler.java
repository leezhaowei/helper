package com.talend.nb;

import java.util.ArrayList;
import java.util.List;

import com.talend.nb.SnapHandler.Action;
import com.talend.nb.SnapHandler.VersionType;

public class Do54SnapHandler {

    public static void main(String[] args) {
        do54();
    }

    private static void do54() {
        String version = "V5.4.2SNAP";
        String revision = "20141227_1931";
        String sourceDir = "E:/Download";
        String licenceTag = "licenses_542_30days";
        String nbArchive = "E:/TUP/54";
        VersionType versionType = VersionType.V54;

        List<Action> actions = new ArrayList<Action>(4);

        actions.add(Action.MOVE_AND_UNZIP_LIB);
        actions.add(Action.DELETE_AND_MOVE);

        SnapHandler.doing(version, revision, versionType, sourceDir, licenceTag, nbArchive, actions);
    }
}
