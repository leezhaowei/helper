package com.zwli.svn;

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationProvider;
import org.tmatesoft.svn.core.auth.SVNAuthentication;
import org.tmatesoft.svn.core.auth.SVNPasswordAuthentication;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

@SuppressWarnings("deprecation")
public class DisplayRepositoryTree {

    public static void main(String[] args) {
        final String repoUrl = "https://talendvm-PC.talend.com:443/svn/toto";
        final String userName = "talend\\tacBJ";
        final String password = "talend";

        setupLibrary();
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repoUrl));
        } catch (SVNException e) {
            e.printStackTrace();
            return;
        }

        ISVNAuthenticationManager authManager = new BasicAuthenticationManager(userName, password);
        authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);

        ISVNAuthenticationProvider authProvider = new ISVNAuthenticationProvider() {

            @Override
            public SVNAuthentication requestClientAuthentication(String kind, SVNURL url, String realm,
                    SVNErrorMessage errorMessage, SVNAuthentication previousAuth, boolean authMayBeStored) {
                SVNPasswordAuthentication svnAuth = null;
                try {
                    svnAuth = new SVNPasswordAuthentication(userName, password, false, SVNURL.parseURIEncoded(repoUrl), false);
                } catch (SVNException e) {
                    e.printStackTrace();
                }
                return svnAuth;
            }

            @Override
            public int acceptServerAuthentication(SVNURL url, String realm, Object certificate, boolean resultMayBeStored) {
                return ISVNAuthenticationProvider.ACCEPTED;
            }
        };
        authManager.setAuthenticationProvider(authProvider);
        repository.setAuthenticationManager(authManager);

        try {
            SVNNodeKind nodeKind = repository.checkPath("", -1);
            if (nodeKind == SVNNodeKind.NONE) {
                System.err.println("There is no entry at '" + repoUrl + "'.");
                return;
            } else if (nodeKind == SVNNodeKind.FILE) {
                System.err.println("The entry at '" + repoUrl + "' is a file while a directory was expected.");
                return;
            }
            System.out.println("Repository Root: " + repository.getRepositoryRoot(true));
            System.out.println("Repository UUID: " + repository.getRepositoryUUID(true));
            System.out.println("");

            listEntries(repository, "");
        } catch (SVNException e) {
            e.printStackTrace();
            return;
        }

        long latestRevision = -1;
        try {
            latestRevision = repository.getLatestRevision();
        } catch (SVNException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("Repository latest revision: " + latestRevision);
    }

    private static void setupLibrary() {
        DAVRepositoryFactory.setup();
        // SVNRepositoryFactoryImpl.setup();
        // FSRepositoryFactory.setup();
    }

    public static void listEntries(SVNRepository repository, String path) throws SVNException {
        Collection<?> entries = repository.getDir(path, -1, null, (Collection<?>) null);
        Iterator<?> iterator = entries.iterator();
        while (iterator.hasNext()) {
            SVNDirEntry entry = (SVNDirEntry) iterator.next();
            System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName() + " (author: '" + entry.getAuthor()
                    + "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");
            /*
             * Checking up if the entry is a directory.
             */
            if (entry.getKind() == SVNNodeKind.DIR) {
                listEntries(repository, (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
            }
        }
    }
}
