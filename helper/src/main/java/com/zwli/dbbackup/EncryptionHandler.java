// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.zwli.dbbackup;

import org.apache.log4j.Logger;

import com.zwli.security.AES;

/**
 * created by talend on Jan 14, 2013 Detailled comment
 *
 */
public class EncryptionHandler {

    private static Logger log = Logger.getLogger(EncryptionHandler.class);

    private static EncryptionHandler instance;

    public static EncryptionHandler getInstance() {
        if (instance == null) {
            instance = new EncryptionHandler();
        }
        return instance;
    }

    private EncryptionHandler() {
    }

    public String encrypt(String text) {
        String encryptedCode = "";
        try {
            if (text != null && !"".equals(text)) {
                encryptedCode = AES.getInstance().encrypt(text);
            } else {
                log.info("The encrypt text is null or empty");
            }
        } catch (Exception e) {
            log.error("The" + text + "can't be encrypted", e);
        }
        return encryptedCode;
    }

    public String decrypt(String encryptedCode) {
        String text = "";
        try {
            if (encryptedCode != null && !"".equals(encryptedCode)) {
                text = AES.getInstance().decrypt(encryptedCode);
            } else {
                log.info("The decrypt code is null or empty");
            }
        } catch (Exception e) {
            log.error("The" + encryptedCode + "can't be decrypted", e);
        }
        return text;
    }

    public String encryptOneWay(String text) {
        String encryptedCode = "";
        try {
            if (text != null && !"".equals(text)) {
                encryptedCode = SHA.getSHA256(text.getBytes());
            } else {
                log.info("The encrypt text is null or empty");
            }
        } catch (Exception e) {
            log.error("The" + text + "can't be encrypted", e);
        }
        return encryptedCode;
    }

}
