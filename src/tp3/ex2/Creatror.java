/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex2;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Scanner;

/**
 *
 * @author Maroine
 */
public class Creatror {

    private String messageToHash = "message to hash";
    private String hashFileName = ".\\hash";
    private String publickeyFileName = ".\\publickey";
    private String fileName = ".\\message";

    private KeyPair key;

    public void generateTheKey() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        key = kg.genKeyPair();
    }

    private void readStringFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your message : \n");
        messageToHash = sc.nextLine();
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
        ObjectOutput os = new ObjectOutputStream(new FileOutputStream(publickeyFileName));
        os.writeObject(key.getPublic()); // public key
    }

    public static void main(String[] args) throws Exception {
        new Creatror();
    }
}
