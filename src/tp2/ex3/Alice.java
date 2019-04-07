/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Maroine
 */
public class Alice {

    private SecretKey key;
    private final int BUFFSIZE = 255;
    private byte[] buff = new byte[BUFFSIZE];
    private String encryptfile = ".\\encryptfile";
    private String keyfile = ".\\keyfile";
    private String algo = "DES";
    private String message = "alice message";

    public Alice() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        key = kg.generateKey();
        Files.write(Paths.get(keyfile), key.getEncoded(), StandardOpenOption.CREATE);
    }

    public void encryptAliceMessage() throws Exception {
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        OutputStream os = new FileOutputStream(new File(encryptfile));
        os.write(c.doFinal(message.getBytes()));
        os.close();
    }

    public static void main(String[] args) throws Exception {
        Alice alice = new Alice();
        alice.encryptAliceMessage();
    }
}
