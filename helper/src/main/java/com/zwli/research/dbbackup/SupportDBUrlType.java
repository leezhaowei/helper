package com.zwli.research.dbbackup;

public enum SupportDBUrlType {
    H2_LOCAL("H2 Local", "org.h2.Driver", "jdbc:h2:file:{dir_path/}<db_name>;MVCC=TRUE;AUTO_SERVER=TRUE;LOCK_TIMEOUT=15000"),
    H2_REMOTE(
              "H2 Remote",
              "org.h2.Driver",
              "jdbc:h2:tcp://{ip_address}{:port}/{dir_path/}db_name;MVCC=TRUE;AUTO_SERVER=TRUE;LOCK_TIMEOUT=15000"),
    MYSQL("MySql", "org.gjt.mm.mysql.Driver", "jdbc:mysql://{ip_address}:3306/{db_name}"),
    ORACLE("Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@{ip_address}:1521:{db_name}"),
    SQLSERVER("SqlServer", "net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver://{ip_address}:1433/{db_name}"),

    POSTGRESQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://{ip_address}:5432/{db_name}");

    private String url;

    private String driver;

    private String name;

    SupportDBUrlType(String name, String driver, String url) {
        this.name = name;
        this.driver = driver;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getName() {
        return this.name;
    }

    public static SupportDBUrlType getTypeByDriverAndUrl(String driver, String url) {
        for (final SupportDBUrlType type : SupportDBUrlType.values()) {
            if (type.getDriver().equals(driver)) {
                if (type == SupportDBUrlType.H2_LOCAL || type == SupportDBUrlType.H2_REMOTE) {
                    if (url.startsWith("jdbc:h2:tcp")) {
                        return SupportDBUrlType.H2_REMOTE;
                    } else {
                        return SupportDBUrlType.H2_LOCAL;
                    }
                } else {
                    return type;
                }
            }
        }
        return null;
    }

    public static SupportDBUrlType getTypeByName(String name) {
        for (final SupportDBUrlType type : SupportDBUrlType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
