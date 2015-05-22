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

import java.security.MessageDigest;

/**
 * created by zwli on Feb 1, 2013 Detailed comment
 */
public class SHA {

    private static final String SHA_256 = "SHA-256";

    private static final char HexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private SHA() {
    }

    public static String getSHA256(byte[] source) {
        String toReturn = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            md.update(source);
            byte tmp[] = md.digest();
            byte element = -1;
            int ind = 0;
            char arr[] = new char[16 * 2];
            for (int i = 0; i < 16; i++) {
                element = tmp[i];
                arr[ind++] = HexDigits[element >>> 4 & 0xf];
                arr[ind++] = HexDigits[element & 0xf];
            }
            toReturn = new String(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public static void main(String[] args) {
        String s = "admin";
        String t = SHA.getSHA256(s.getBytes());
        System.out.println(t);
    }
}
