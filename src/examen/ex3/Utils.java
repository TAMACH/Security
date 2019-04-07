/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ex3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author tamac
 */
public class Utils {

    public static SecretKey generateSessionKey() throws Exception {
        return KeyGenerator.getInstance("DES").generateKey();
    }

    public static KeyPair generateKeyPair() throws Exception {
        return KeyPairGenerator.getInstance("RSA").genKeyPair();
    }

    public static PublicKey getPublicKey(String id) throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(new File("keystore")), "".toCharArray());
        return ks.getCertificate(id).getPublicKey();
    }

    public static byte[] encryptRSA(byte[] bytes, Key key) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(bytes);// no reason de do update cause the key is not so long
    }

    public static byte[] decryptRSA(InputStream in, Key key) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = new byte[in.available()];
        return c.doFinal(bytes);
    }

    public static byte[] signature(byte[] bytes, PrivateKey key) throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(key);
        sig.update(bytes);
        return sig.sign();

    }

    public static void verify(byte[] bytes, PublicKey key) throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(key);
        sig.update(bytes);
        if (!sig.verify(bytes)) {
            new Exception("not verified");
        }
    }

    public static byte[] encryptDES(InputStream os, Key key, String message) throws Exception {
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key);

        return c.doFinal(bytes);// no reason de do update cause the key is not so long
    }

    public static byte[] decryptDES(InputStream in, Key key) throws Exception {
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = new byte[in.available()];
        return c.doFinal(bytes);
    }
}
