/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ex3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Maroine
 */
public class Bob {

    private SecretKey key;
    private final int BUFFSIZE = 255;
    private byte[] buff = new byte[BUFFSIZE];
    private String encryptfile = ".\\encryptfile";
    private String keyfile = ".\\keyfile";
    private String decryptfile = ".\\decryptfile";
    private String algo = "DES";

    public Bob() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        byte[] keyBytes = Files.readAllBytes(Paths.get(keyfile));
        SecretKeyFactory kf = SecretKeyFactory.getInstance(algo);
        key = kf.generateSecret(new DESKeySpec(keyBytes));
    }

    public void decryptBobFile() throws Exception {
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        InputStream is = new FileInputStream(new File(encryptfile));
        OutputStream os = new FileOutputStream(new File(decryptfile));
        while (is.available() >= BUFFSIZE) {
            is.read(buff);
            os.write(c.update(buff));
        }
        int available = is.available();
        is.read(buff);
        os.write(c.doFinal(buff, 0, available));
        is.close();
        os.close();
    }

    public static void main(String[] args) throws Exception {
        Bob bob = new Bob();
        bob.decryptBobFile();
    }
}
