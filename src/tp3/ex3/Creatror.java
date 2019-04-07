/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex3;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Scanner;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;

/**
 *
 * @author Maroine
 */
public class Creatror {

    private String messageToHash = "message to hash";
    private String hashFileName = ".\\hash";
    private String fileName = ".\\message";
    private String keystore = ".\\keystore";
    private String password = "this is not a password";

    private KeyPair key;

    public void generateTheKey() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        key = kg.genKeyPair();
    }

    private void readStringFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your message : \n");
        messageToHash = sc.next();
    }

    public Creatror() throws Exception {
        generateTheKey();
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(key.getPrivate());
        //readStringFromConsole();
        Files.write(Paths.get(fileName), messageToHash.getBytes(), CREATE);// message
        sig.update(messageToHash.getBytes());
        byte[] sign = sig.sign();
        Files.write(Paths.get(hashFileName), sign, CREATE);// signature

        X509V3CertificateGenerator generator = new X509V3CertificateGenerator();
        generator.setSerialNumber(BigInteger.ONE);
        generator.setSignatureAlgorithm(sig.getAlgorithm());
        generator.setNotAfter(new Date("06/03/2019"));
        generator.setNotBefore(new Date("10/03/2019"));
        generator.setPublicKey(key.getPublic());
        generator.setIssuerDN(new X500Principal("CN=Duke"));
        generator.setSubjectDN(new X500Principal("CN=Duke"));

        X509Certificate cetificate = generator.generate(key.getPrivate());
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        FileOutputStream os = new FileOutputStream(new File(keystore));
        keyStore.setCertificateEntry("my_cetificate", cetificate);
        keyStore.store(os, password.toCharArray());

    }

    public static void main(String[] args) throws Exception {
        new Creatror();
    }
}
