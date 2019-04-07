/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex3;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.Certificate;

/**
 *
 * @author Maroine
 */
public class Reader {

    private String hashFileName = ".\\hash";
    private String fileName = ".\\message";
    private String keystore = ".\\keystore";
    private String password = "this is not a password";
    private Certificate certificate;

    public void getTheCertificate() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fs = new FileInputStream(new File(keystore));
        keyStore.load(fs, password.toCharArray());
        certificate = keyStore.getCertificate("my_cetificate");

    }

    public Reader() throws Exception {
        getTheCertificate();
        byte[] hash = Files.readAllBytes(Paths.get(hashFileName));
        byte[] message = Files.readAllBytes(Paths.get(fileName));
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(certificate.getPublicKey());
        sig.update(message);
        boolean verify = sig.verify(hash);
        System.out.println(verify);
    }

    public static void main(String[] args) throws Exception {
        new Reader();
    }
}
