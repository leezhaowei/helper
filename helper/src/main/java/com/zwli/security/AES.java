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
package com.zwli.security;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * created by zwli on Feb 27, 2013 Detailed comment
 */
public class AES {

    public static void main(String[] args) throws Exception {
        // String cloudserver =
        // "{\"accessKeyId\":\"AKIAIEY636YNKX7J7RYA\",\"activeAmiSelection\":true,\"imageId\":\"ami-e6b528d6\",\"instanceId\":\"us-west-2/i-0511880d\",\"instanceLogin\":\"ubuntu\",\"instanceType\":\"t1.micro\",\"jobServerZipName\":\"Talend-JobServer-r114292-V5.4.2NB.zip\",\"jobServerZipPath\":\"E:\\\\TAC\\\\build\\\\Administrator\\\\WEB-INF\\\\jobServerToAwsEC2\\\\Talend-JobServer-r114292-V5.4.2NB.zip\",\"keyPair\":\"zwli2\",\"privateKey\":\"-----BEGIN RSA PRIVATE KEY-----\\nMIIEpQIBAAKCAQEApyl051YWQcqmRjNQOkLEePpnVIM5tMQ/0XAAXi+bfoTQXjcYFehbeFJzm8u8\\n2PTcxEGqq3fM6exAa1PUyOKkGdRABfs8DoEcXts9T4SZqQcfIrlnNUj5Qown2zUOVlURJl+CWxbr\\ni7MeH8BSzRK5gakOTXr5Pq1A4mgJ7GuScmbRHdQ3wUSW46z7ouqmQuJqtKuBb+rB2OoGjomYpBq4\\nIvACH6c8fBjl5d8FWGucbmoxCUPIncBI4QV/Pu4PPZYbUmYmPSeyQjquvtVmQPjE1v+5PCP/N+ba\\n/EqN9p/LWH5XJ/FYDF3DiisGYjO9w4DR9ewHpn1HZ21V3pFSx42+ZQIDAQABAoIBAQCmyXh5Ma33\\naylINf7AgsniI4ZiDqTPNliusd/MGhg9vmrg9nuzsClD/pCXTEta9cYok5SkjV4JsM39yKEw5uHx\\nW5Od5DTiC/SwuHOOGbHvsslgLjlKM0txGMLTtzZc8XexH+7C0DWpJ8jQyWZUc3BgbLt2l1KFNPze\\nagwstpBiT5McabxoxDPaiSl2hyTAU9U+KrRQMKusraynpVX0dxAkXJ6jApb6kFYkGhS6Lg69+1cF\\nBJeDAR4/MsEMfky/WPpPCba2DvElZgqTWsOMQoEwWR7opbKWUGMZe8y3f/Nh7qKlxEP7WIFtsFOp\\nju0huQ+WGV7ngej0WWk2RvbSfq0pAoGBANYl1D7UMaFWbBWqOs63GYHUIJ1LdU6sZgDTrAEYLLq2\\n9U0wbWyyPVDHSVwof/6q47t/S/bRBde9zRoz7qqqkKbI0iUVEFK5oSSF76s7c4zudApczMgrZbBx\\neQs95tx210nMGvQm7rGtZLrIK+amGw2rgt5SLDxset0S3whhkeDLAoGBAMfU2RPr97dlEPaigBUH\\nYM/wUfEvAHqZZsW3CWrwsG+1vAItpi+bWb5dq2rED1Ez0mvcFwsTzFgYn1mDLJUbDU9cXDGT7zlu\\nh3vzXLv9DFWElFyQobhONaoRrYX/SoMGQekYwTgS1HjrWtr56e9elL/GrRMOvIvUK/4J/6VqbueP\\nAoGBAJkxuLM1k0eKjom1HJqyi5G/0ZEU4Rdl5xaiVkL2K1Fq/vK4OR9wJsL5vbYq1t21HHMOLqaj\\ngx2CDw62pf77Q728nfCfL+0uFGQPWJPTVOc10ldzaZ+sw/NEScZMAzIl5f9J/uI4U7tRgmTIO/el\\nTmcqEdW/TRvwsrgh/uF9AyGfAoGAJKtAMlGooTSgikGDc1hpQX796l4Pnm+r1jgiOEd2eOlGWLf7\\nuNyu+SnDHNJhZSs3P6ehatz1MG1EQG67rZjLNnbiTA6zZTyNDAe6SpzCuKadeubnZiSj4FNiHiac\\ni7KIQbfTLPD3oEC/6VLWxJ/8d7yDYe3gV2BVukYnbTk8pK0CgYEAl1asMGqB1IeELDs+RScMTLSb\\nQ2crr4F/PuHfes3hM8za/hkYjt+2LEcYJoVTZZBLAuf1PI8BYxQ7DZEEewuwt9ctpu1nEBX3uPcW\\nVbJsDcWNrRHFlv9PupAYONU7EG2I3H3QG/PMYVntlO+j3bTWKXCpED4/JiXKgXN51ZU9U4Q=\\n-----END RSA PRIVATE KEY-----\",\"regionId\":\"us-west-2\",\"secretAccessKey\":\"4kzKhKCKaATg+Jqj+FClbJDFLylsECcZWPlIXok1\",\"securityGroup\":\"zwli\",\"securityGroupPorts\":\"8000,8001,8888\",\"selectedAmiOS\":\"Ubuntu 12.04 t1.micro\",\"shutdownBehavior\":\"Stop\"}";
        // String encryptedText = new AES().encrypt(cloudserver);
        // System.out.println(encryptedText);
        // System.out.println("===========================");
        // encryptedText =
        // "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";
        // String text = new AES().decrypt(encryptedText);
        // System.out.println(text);

        AES aes = new AES();
        String str = aes.encrypt("tisadmin");
        System.out.println(str);
        str = aes.encrypt("tadmin");
        System.out.println(str);

        String[] arr = {
                "bt4AUzTV14kK8FwkcK/BNg==",
                "3IqdoqEElsy8Dzz9iP3HVQ==",
                "eyJhY3Rpb25OYW1lIjoiY3JlYXRlUHJvamVjdFJlZmVyZW5jZSIsImF1dGhQYXNzIjoiYWRtaW4iLCJhdXRoVXNlciI6InRhcmdldEBjb21wYW55LmNvbSIsImJyYW5jaCI6InRydW5rIiwicHJvamVjdE5hbWUiOiJ0ZXN0IiwicmVmZXJlbmNlUHJvamVjdHMiOiJXVy90cnVuayJ9" };
        for (String t : arr) {
            System.out.println(aes.decrypt(t));
        }
    }

    private static Logger log = Logger.getLogger(AES.class);

    private static final String OS_NAME = "SunOS";

    private static final String RANDOM_SHA1PRNG = "SHA1PRNG";

    private static final String ENCRYPTION_ALGORITHM = "AES";

    private static final String EMPTY_STRING = "";

    private static final String UTF8 = "UTF8";

    // 8-byte
    private static final byte[] KeyValues = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
            (byte) 0xE3, (byte) 0x03 };

    private static AES instance;

    private Cipher ecipher;

    private Cipher dcipher;

    public static synchronized AES getInstance() {
        if (null == instance) {
            instance = new AES();
        }
        return instance;
    }

    private AES() {
        try {
            String osName = System.getProperty("os.name");
            boolean isSunOS = false;
            if (null != osName) {
                isSunOS = osName.contains(OS_NAME);
            }

            Provider providerSunJCE = null;
            // TDI-28380: Database password in tac db configuration page becomes empty once restart tomcat on Solaris.
            // To solve this problem, there are two ways:
            // Security.removeProvider("SunPKCS11-Solaris");
            // or: providerSunJCE = Security.getProvider("SunJCE");
            if (isSunOS) {
            }
            providerSunJCE = Security.getProvider("SunJCE");

            KeyGenerator keyGen = null;
            SecureRandom random = null;

            if (isSunOS) {
                keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
            } else {
                keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            }

            random = SecureRandom.getInstance(RANDOM_SHA1PRNG);
            random.setSeed(KeyValues);
            keyGen.init(128, random);

            Key key = keyGen.generateKey();

            if (isSunOS) {
                ecipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
                dcipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, providerSunJCE);
            } else {
                ecipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
                dcipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            }

            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            // log the error to avoid that break GWT service
            log.error(e.getMessage(), e);
        }
    }

    public String encrypt(String data) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (EMPTY_STRING.equals(data)) {
            return EMPTY_STRING;
        }
        byte[] enc = ecipher.doFinal(data.getBytes(UTF8));
        String encryptedData = new String(Base64.encodeBase64(enc), UTF8);
        return encryptedData;
    }

    public String decrypt(String encryptedData) throws UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException {
        if (EMPTY_STRING.equals(encryptedData)) {
            return EMPTY_STRING;
        }
        byte[] dec = Base64.decodeBase64(encryptedData.getBytes(UTF8));
        dec = dcipher.doFinal(dec);
        String decryptedData = new String(dec, UTF8);
        return decryptedData;
    }
}
