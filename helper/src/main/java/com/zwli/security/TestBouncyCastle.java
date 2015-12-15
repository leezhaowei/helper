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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class TestBouncyCastle {

    private static final String RANDOM_SHA1PRNG = "SHA1PRNG";

    private static final String ENCRYPTION_ALGORITHM = "AES";

    private static final String EMPTY_STRING = "";

    private static final String UTF8 = "UTF8";

    private static final byte[] KeyValues = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56,
            (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

    private static Cipher[] init() {
        try {
            Provider p = Security.getProvider("BC");
            if (null == p) {
                p = new BouncyCastleProvider();
                Security.addProvider(p);
                System.out.println(">>> add BC provider");
            }
            KeyGenerator keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, p);
            SecureRandom random = SecureRandom.getInstance(RANDOM_SHA1PRNG);
            random.setSeed(KeyValues);
            keyGen.init(128, random);
            Key key = keyGen.generateKey();

            Cipher ecipher = null;
            Cipher dcipher = null;

            ecipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, p);
            dcipher = Cipher.getInstance(ENCRYPTION_ALGORITHM, p);

            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

            return new Cipher[] { ecipher, dcipher };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encrypt(Cipher ecipher, String data) throws IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        if (EMPTY_STRING.equals(data)) {
            return EMPTY_STRING;
        }
        byte[] enc = ecipher.doFinal(data.getBytes(UTF8));
        String encryptedData = new String(Base64.encodeBase64(enc), UTF8);
        return encryptedData;
    }

    private static String decrypt(Cipher dcipher, String encryptedData) throws UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {
        if (EMPTY_STRING.equals(encryptedData)) {
            return EMPTY_STRING;
        }
        byte[] dec = Base64.decodeBase64(encryptedData.getBytes(UTF8));
        dec = dcipher.doFinal(dec);
        String decryptedData = new String(dec, UTF8);
        return decryptedData;
    }

    public static void main(String[] args) {
        testA();
        System.out.println("======================================");
        testB();
    }

    static void testA() {
        String data = "Hello World!";
        Cipher ecipher = null;
        Cipher dcipher = null;
        try {
            Cipher[] c = init();
            ecipher = c[0];
            dcipher = c[1];

            String text = encrypt(ecipher, data);
            System.out.println(text);
            text = decrypt(dcipher, text);
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void testB() {
        Cipher dcipher = null;
        try {
            String text = "bt4AUzTV14kK8FwkcK/BNg==";
            text = decrypt(dcipher, text);
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
