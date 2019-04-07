/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ex3;

import static examen.ex3.Utils.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Maroine
 */
public class Alice {

    InputStream in;// stream to send
    OutputStream os; // stream to receive
    SecretKey sessionKey;
    KeyPair keyPair;
    String password = "pass";
    PublicKey bobKey;

    public Alice(InputStream in, OutputStream os) throws Exception {
        this.in = in;
        this.os = os;
        this.bobKey = getPublicKey("Bob");
    }

    public void sendMessageTo() throws Exception {
        byte[] encryptRSA = encryptRSA(sessionKey.getEncoded(), bobKey);
        byte[] signature = signature(encryptRSA, keyPair.getPrivate()); //size of sinature is fixed (size of sha1)
        os.write(encryptRSA);
        os.write(signature);
    }

    public static void main(String[] args) throws Exception {
    }

}
