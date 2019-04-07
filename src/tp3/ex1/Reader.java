/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author Maroine
 */
public class Reader {

    private String messageToHash = "message to hash";
    private String hashFileName = ".\\hash";

    public Reader() throws Exception {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        sha1.update(messageToHash.getBytes());
        byte[] digestB = sha1.digest();
        byte[] digestA = Files.readAllBytes(Paths.get(hashFileName));

        System.out.println(Arrays.toString(digestA));
        System.out.println(Arrays.toString(digestB));

        System.out.println(MessageDigest.isEqual(digestA, digestB));
    }

    public static void main(String[] args) throws Exception {
        new Reader();
    }
}
