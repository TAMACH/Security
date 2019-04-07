/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2.ex1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Maroine
 */
public final class AliceAndBobDES {

    private SecretKey key;
    private String messageToSend = "my message to send";
    private String alog = "DES";

    public AliceAndBobDES() throws Exception {
        generateTheKey();
    }

    public void generateTheKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        key = kg.generateKey();
    }

    public byte[] encryptAlice() throws Exception {
        Cipher c = Cipher.getInstance(alog);
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(messageToSend.getBytes());
    }

    public String decryptBob(byte[] aliceMessage) throws Exception {
        Cipher c = Cipher.getInstance(alog);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] doFinal = c.doFinal(aliceMessage);
        return new String(doFinal);
    }

    public static void main(String[] args) throws Exception {
        AliceAndBobDES AB = new AliceAndBobDES();
        byte[] encryptAlice = AB.encryptAlice();
        String decryptBob = AB.decryptBob(encryptAlice);
        System.out.println(decryptBob + " ===> " + AB.messageToSend);

    }
}
