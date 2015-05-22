package com.zwli.svn;

import java.net.MalformedURLException;
import org.tmatesoft.svn.core.SVNAuthenticationException;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

@SuppressWarnings("deprecation")
public class SVNRepositoryTester {

    public static final String PASSWORD = "talend";

    public static final String USER_NAME = "TALEND\\tacBJ";

    public static final String SVN_URL = "https://talendvm-PC.talend.com/svn/toto/";

    private SVNRepository repository;

    private String userName;

    private String password;

    private String svnUrl;

    public static void main(final String[] args) {
        String userName = USER_NAME;
        String password = PASSWORD;
        String svnUrl = SVN_URL;
        // if (null == args || args.length == 0) {
        // System.out.println("Your windows username/password: ");
        // Scanner sc = new Scanner(System.in);
        // String input = sc.nextLine();
        // sc.close();
        //
        // if (null != input && input.trim().length() != 0) {
        // userName = input.split(",")[0];
        // password = input.split(",")[1];
        // svnUrl = input.split(",")[2];
        // }
        // } else if (args.length == 1) {
        // userName = args[0];
        // } else if (args.length == 2) {
        // userName = args[0];
        // svnUrl = args[1];
        // } else if (args.length == 3) {
        // userName = args[0];
        // password = args[1];
        // svnUrl = args[2];
        // }

        if (null == userName || userName.trim().length() == 0) {
            userName = USER_NAME;
        }
        if (null == password || password.trim().length() == 0) {
            password = PASSWORD;
        }
        if (null == svnUrl || svnUrl.trim().length() == 0) {
            svnUrl = SVN_URL;
        }

        SVNRepositoryTester repositoryTester = new SVNRepositoryTester(userName, password, svnUrl);
        repositoryTester.initialize();
        repositoryTester.testConnection();
        System.out.println("Done");
    }

    public SVNRepositoryTester(String userName, String password, String svnUrl) {
        super();
        this.userName = userName;
        this.password = password;
        this.svnUrl = svnUrl;
    }

    private void initialize() {
        try {
            DAVRepositoryFactory.setup();
            setHTTPNTLMProperty();
            repository = SVNRepositoryFactory.create(getSVNURL());

            repository.setAuthenticationManager(getAuthenticationManager());
        } catch (SVNException e) {
            throw new RuntimeException(e);
        }

    }

    private void testConnection() {
        try {
            repository.testConnection();
        } catch (SVNAuthenticationException e) {
            throw new RuntimeException(String.format("Issue %s %s", getUserName(), getSvnUrl()), e);
        } catch (SVNException e) {
            SVNErrorCode errorCode = e.getErrorMessage().getErrorCode();
            if (errorCode.getCategory() == SVNErrorCode.RA_CATEGORY) {
                throw new RuntimeException(String.format("Invalid credentials: %s %s", getUserName(), getSvnUrl()), e);
            }
            throw new RuntimeException(String.format("Invalid URL %", SVN_URL), e);
        }
    }

    private void setHTTPNTLMProperty() {
        System.setProperty("svnkit.http.ntlm", "java");

        // System.setProperty("svnkit.http.ntlm", "jna");

        System.setProperty("svnkit.http.ntlm.promptUser", "true");
        System.setProperty("svnkit.http.methods", "NTLM");
    }

    private ISVNAuthenticationManager getAuthenticationManager() {
        ISVNAuthenticationManager authentication = null;
        authentication = new BasicAuthenticationManager(getUserName(), getPassword());
        // authentication = SVNWCUtil.createDefaultAuthenticationManager();
        return authentication;
    }

    private SVNURL getSVNURL() throws SVNException {
        try {
            java.net.URL urlObject = new java.net.URL(getSvnUrl());
            return SVNURL.create(urlObject.getProtocol(), urlObject.getUserInfo(), urlObject.getHost(), urlObject.getPort(),
                    urlObject.getPath(), false);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSvnUrl() {
        return this.svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

}
