package com.zwli.dbbackup;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbChecker {

    private static Logger log = Logger.getLogger(DbChecker.class);

    public static Driver checkDbDriver(String driverName) throws BusinessException {
        if (driverName == null) {
            throw new BusinessException("dbconfig.error.noDriverSpecified");
        }
        try {
            return (Driver) Class.forName(driverName).newInstance();
        } catch (Exception e) {
            throw new BusinessException("dbconfig.error.driverNotFound", driverName);
        }
    }

    public static void checkDbUrl(String driverName, String url) throws BusinessException {
        Driver driver = checkDbDriver(driverName);
        try {
            if (!driver.acceptsURL(url)) {
                throw new BusinessException("dbconfig.error.malformedUrl", url);
            }
        } catch (SQLException e) {
            throw new BusinessException("dbconfig.error.malformedUrl", url);
        }
    }

    public static void checkDbConnection(String driverName, String url, String userName, String password)
            throws BusinessException {
        Connection conn = null;
        try {
            // Test driver:
            Driver driver = checkDbDriver(driverName);

            // Test url:
            checkDbUrl(driverName, url);

            // Test connection:
            Properties props = new Properties();
            if (userName != null) {
                props.setProperty("user", userName);
            }
            if (password != null) {
                props.setProperty("password", password);
            }
            try {
                conn = driver.connect(url, props);
            } catch (SQLException e) {
                throw new BusinessException("dbconfig.error.cannotConnect", e.getMessage());
            }
        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            throw new BusinessException("dbconfig.error.cannotConnect", e.getMessage());
        } finally {
            // Close connection
            try {
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.warn(e);
            }
        }
    }
}
