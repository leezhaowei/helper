package com.zwli.jdbc;

import com.zwli.pattern.builder.ObjBuilder;

public class JdbcSource {
    private final String url;
    private final String driver;
    private final String username;
    private final String password;

    private JdbcSource(final Builder builder) {
        this.url = builder._url;
        this.driver = builder._driver;
        this.username = builder._username;
        this.password = builder._password;
    }

    public static class Builder implements ObjBuilder<JdbcSource> {
        private String _url;
        private String _driver;
        private String _username;
        private String _password;

        @Override
        public JdbcSource build() {
            return new JdbcSource(this);
        }

        public Builder url(final String url) {
            _url = url;
            return this;
        }

        public Builder driver(final String driver) {
            _driver = driver;
            return this;
        }

        public Builder username(final String username) {
            _username = username;
            return this;
        }

        public Builder password(final String password) {
            _password = password;
            return this;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
