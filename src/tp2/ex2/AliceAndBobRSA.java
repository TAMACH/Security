/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author Maroine
 */
public final class AliceAndBobRSA {

    private String messageToSend;
    private String algo = "RSA";
    private String padding = "BC";
    private KeyPair key;
    private String tmpEncript = ".\\tmp_encript";
    private String tmpDecript = ".\\tmp_decript";
    private final int BUFFSIZE = 255;
    private byte[] buff = new byte[BUFFSIZE];

    public AliceAndBobRSA() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        generateTheKey();
        readStringFromConsole();
    }

    public void readStringFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your message");
        messageToSend = sc.nextLine();
    }

    public void generateTheKey() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance(algo, padding);
        key = kg.genKeyPair();
    }

    public void encryptAlice() throws Exception {
        Cipher c = Cipher.getInstance(algo, padding);
        c.init(Cipher.ENCRYPT_MODE, key.getPublic());
        OutputStream os = new FileOutputStream(new File(tmpEncript));
        os.write(messageToSend.getBytes());
    }

    public void decryptBob() throws Exception {
        Cipher c = Cipher.getInstance(algo, padding);
        c.init(Cipher.DECRYPT_MODE, key.getPrivate());
        InputStream is = new FileInputStream(new File(tmpEncript));
        OutputStream os = new FileOutputStream(new File(tmpDecript));
        while (is.available() >= BUFFSIZE) {
            is.read(buff);
            os.write(c.update(buff));
        }
        int available = is.available();
        is.read(buff);
        os.write(c.doFinal(buff, 0, available));
    }

    private void readFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }

    public void show() throws IOException {
        System.out.println("\n Enc   :  \n");

        readFile(tmpEncript);

        System.out.println("\n Dec   : \n");

        readFile(tmpDecript);

    }

    public static void main(String[] args) throws Exception {
        AliceAndBobRSA AB = new AliceAndBobRSA();
        AB.encryptAlice();
        AB.decryptBob();
        AB.show();

    }
}
