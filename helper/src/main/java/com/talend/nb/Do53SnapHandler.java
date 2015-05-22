package com.talend.nb;

import java.util.ArrayList;
import java.util.List;

import com.talend.nb.SnapHandler.Action;
import com.talend.nb.SnapHandler.VersionType;

public class Do53SnapHandler {

    public static void main(String[] args) {
        do52();
    }

    private static void do52() {
        String version = "V5.2.4NB";
        String revision = "r118713";
        String sourceDir = "E:/Download";
        String licenceTag = "licenses_521_30days";
        String nbArchive = "E:/TUP/52";
        VersionType versionType = VersionType.V53;

        List<Action> actions = new ArrayList<Action>(4);

        actions.add(Action.MOVE_AND_UNZIP_LIB);
        // actions.add(Action.DELETE_AND_MOVE);

        SnapHandler.doing(version, revision, versionType, sourceDir, licenceTag, nbArchive, actions);
    }
}
