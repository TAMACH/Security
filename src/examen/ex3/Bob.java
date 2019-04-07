/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ex3;

import static examen.ex3.Utils.decryptRSA;
import static examen.ex3.Utils.encryptRSA;
import static examen.ex3.Utils.getPublicKey;
import static examen.ex3.Utils.signature;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tamac
 */
public class Bob {

    InputStream in;// stream to send
    OutputStream os; // stream to receive
    SecretKey sessionKey;
    KeyPair keyPair;
    PublicKey aliceKey;
    int sizeOfSha;

    public Bob(InputStream in, OutputStream os) throws Exception {
        this.in = in;
        this.os = os;
        this.aliceKey = getPublicKey("Bob");
    }

    public void receiveMessage() throws Exception {
        sessionKey = new SecretKeySpec(decryptRSA(in, keyPair.getPrivate()), "DES");
        byte[] b = new byte[sizeOfSha];
        in.read(b);
        Utils.verify(b, aliceKey);
    }

}
