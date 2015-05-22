package com.talend.nb;

import java.util.ArrayList;
import java.util.List;

import com.talend.nb.SnapHandler.Action;
import com.talend.nb.SnapHandler.VersionType;

public class Do56SnapHandler {

    public static void main(String[] args) {
        do56();
    }

    private static void do56() {
        String version = "V5.6.2";
        String revision = "20150421_1934";
        String sourceDir = "E:/Download";
        String licenceTag = "licenses_562_30days";
        String nbArchive = "E:/TUP/56";
        VersionType versionType = VersionType.V56;

        List<Action> actions = new ArrayList<Action>();

        actions.add(Action.MOVE_AND_UNZIP_LIB);
        actions.add(Action.DELETE_AND_MOVE);

        SnapHandler.doing(version, revision, versionType, sourceDir, licenceTag, nbArchive, actions);
    }
}
