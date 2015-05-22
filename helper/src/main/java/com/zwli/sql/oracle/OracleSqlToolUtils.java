package com.zwli.sql.oracle;

public class OracleSqlToolUtils {

    public static void main(String[] args) {
        makeDropSqlOfOracleForTac();
    }

    private static void makeDropSqlOfOracleForTac() {
        // drop table "SYSTEM"."EXECUTIONTASKJOBPRM" cascade constraints;

        String username = "TALEND";

        String tables = "ARTIFACTNOTIFICATION,COLUMNFILTER,CONFIGURATION,DASHBOARDCONNECTION,EXECUTIONPLANPART,EXECUTIONPLANPARTCMDPRM,"
                + "EXECUTIONPLANPARTJOBPRM,EXECUTIONPLANPRM,EXECUTIONSERVER,EXECUTIONTASK,EXECUTIONTASKCMDPRM,EXECUTIONTASKJOBPRM,"
                + "EXECUTIONTASKPROPERTIES,EXECUTIONVIRTUALSERVER,EXECUTIONVIRTUALSERVER_EXECUR,FILETRIGGERMASK,FILTER,LICENSES,LOCKS,"
                + "NOTIFICATION,PROJECT,PROJECTREFERENCE,QRTZ_BLOB_TRIGGERS,QRTZ_CALENDARS,QRTZ_CRON_TRIGGERS,QRTZ_FIRED_TRIGGERS,"
                + "QRTZ_JOB_DETAILS,QRTZ_JOB_LISTENERS,QRTZ_LOCKS,QRTZ_PAUSED_TRIGGER_GRPS,QRTZ_SCHEDULER_STATE,QRTZ_SIMPLE_TRIGGERS,"
                + "QRTZ_TRIGGER_LISTENERS,QRTZ_TRIGGERS,ROLELIMITATION,SCHEMAINFORMATION,SOAINPUTPARAMETER,SOAOPERATION,SOASERVICE,"
                + "SQLPLUS_PRODUCT_PROFILE,TALENDTRIGGER,TASKEXECUTIONHISTORY,TECHNICALVARIABLE,USERPROJECTAUTHORIZATION,USERROLE,"
                + "USERROLEREFERENCE,USERS,BACKUP,EXECUTIONTASKBUNDLES";
        String[] keyArr = tables.split(",");
        String sql = "";
        for (String key : keyArr) {
            sql = "drop table \"" + username + "\".\"" + key + "\" cascade constraints;";
            // sql = key + ",";
            System.out.println(sql);
        }
        sql = "drop sequence \"" + username + "\".\"HIBERNATE_SEQUENCE\";";
        System.out.println(sql);

        // System.out.println(keyArr.length);
    }
}
