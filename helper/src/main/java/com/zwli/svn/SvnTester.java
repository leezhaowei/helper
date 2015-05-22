package com.zwli.svn;

import java.util.Scanner;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationProvider;
import org.tmatesoft.svn.core.auth.SVNAuthentication;
import org.tmatesoft.svn.core.auth.SVNPasswordAuthentication;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


@SuppressWarnings("deprecation")
public class SvnTester {

    public static void main(String[] args) {
        System.out.println("Your windows username/password: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        test(input);
    }

    // public static String repoUrl = "https://talendvm-PC.talend.com:443/svn/toto/";
    public static String repoUrl = "https://192.168.31.106:554/svn/TestSVN/project01";

    public static String userName = "admin";

    public static String password = "admin";

    public static ISVNAuthenticationProvider authProvider = new ISVNAuthenticationProvider() {

        @Override
        public SVNAuthentication requestClientAuthentication(String kind, SVNURL url, String realm, SVNErrorMessage errorMessage,
                SVNAuthentication previousAuth, boolean authMayBeStored) {
            SVNAuthentication svnAuth = null;
            svnAuth = new SVNPasswordAuthentication(userName, password, false, url, true);
            return svnAuth;
        }

        @Override
        public int acceptServerAuthentication(SVNURL url, String realm, Object certificate, boolean resultMayBeStored) {
            return ISVNAuthenticationProvider.ACCEPTED;
        }
    };

    public static void test(String input) {
        // System.setProperty("svnkit.http.ntlm", "jna");
        System.setProperty("svnkit.http.ntlm", "java");
        // System.setProperty("svnkit.http.ntlm.promptUser", "true");
        // System.setProperty("svnkit.http.methods", "Digest,Basic,Negotiate,NTLM");
        System.setProperty("svnkit.http.methods", "NTLM");

        // System.setProperty("svnkit.http.sslProtocols", "SSLv3");

        try {
            SVNRepository svnRepo = null;
            try {
                FSRepositoryFactory.setup();
                DAVRepositoryFactory.setup();
                SVNRepositoryFactoryImpl.setup();

                SVNURL svnUrl = SVNURL.parseURIEncoded(repoUrl);
                ISVNAuthenticationManager authManager = null;

                String un = null;
                String pwd = null;

                if (null != input && input.trim().length() != 0) {
                    un = input.split(",")[0];
                    pwd = input.split(",")[1];
                }

                if (null != un && null != pwd) {
                    authManager = SVNWCUtil.createDefaultAuthenticationManager(un, pwd);
                    authManager.acknowledgeAuthentication(true, ISVNAuthenticationManager.SSL, "", null,
                            new SVNPasswordAuthentication(un, pwd, false, svnUrl, false));
                } else {
                    authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);
                }

                // if (null != un && null != pwd) {
                // authManager = new BasicAuthenticationManager(un, pwd);
                // } else {
                // authManager = new BasicAuthenticationManager(userName, password);
                // }

                // authManager.setAuthenticationProvider(authProvider);

                svnRepo = SVNRepositoryFactory.create(svnUrl);
                svnRepo.setAuthenticationManager(authManager);

                System.out.println("Repository Root: " + svnRepo.getRepositoryRoot(true));
                System.out.println("Repository UUID: " + svnRepo.getRepositoryUUID(true));

                svnRepo.testConnection();

            } finally {
                if (null != svnRepo) {
                    svnRepo.closeSession();
                }
            }

            // SvnOperationFactory svnOperFactory = new SvnOperationFactory();
            // svnOperFactory.setAuthenticationManager(authManager);
            // try {
            // final SvnCheckout checkout = svnOperFactory.createCheckout();
            // checkout.setSource(SvnTarget.fromURL(svnUrl));
            // checkout.setSingleTarget(SvnTarget.fromFile(new File("E:/TestSVN/target")));
            // checkout.run();
            // } finally {
            // svnOperFactory.dispose();
            // }
        } catch (SVNException e) {
            e.printStackTrace();
        }
    }
}
