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
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Maroine
 */
public class AliceAndBobDESFile {

    private SecretKey key;
    private final int BUFFSIZE = 255;
    private byte[] buff = new byte[BUFFSIZE];
    private String fileName = "C:\\Users\\tamac\\OneDrive\\Desktop\\coq\\coq.docx";
    private String fileNameDec = "C:\\Users\\tamac\\OneDrive\\Desktop\\coq\\coq2.docx";
    private String tmp = "C:\\Users\\tamac\\OneDrive\\Desktop\\coq\\tmp";
    private String algo = "DES";

    public AliceAndBobDESFile() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        key = kg.generateKey();
    }

    public void encryptAliceFile() throws Exception {
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        InputStream is = new FileInputStream(new File(fileName));
        OutputStream os = new FileOutputStream(new File(tmp));
        while (is.available() >= BUFFSIZE) {
            is.read(buff);
            os.write(c.update(buff));
        }
        int available = is.available();
        is.read(buff);
        os.write(c.doFinal(buff, 0, available));
    }

    public void decryptBobFile() throws Exception {
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        InputStream is = new FileInputStream(new File(tmp));
        OutputStream os = new FileOutputStream(new File(fileNameDec));
        while (is.available() >= BUFFSIZE) {
            is.read(buff);
            os.write(c.update(buff));
        }
        int available = is.available();
        is.read(buff);
        os.write(c.doFinal(buff, 0, available));
    }

    public static void main(String[] args) throws Exception {
        AliceAndBobDESFile AB = new AliceAndBobDESFile();
        AB.encryptAliceFile();
        AB.decryptBobFile();
    }
}
