/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex1;

import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.security.MessageDigest;

/**
 *
 * @author Maroine
 */
public class Creatror {

    private String messageToHash = "message to hash";
    private String hashFileName = ".\\hash";

    public Creatror() throws Exception {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        sha1.update(messageToHash.getBytes());
        byte[] digestA = sha1.digest();
        Files.write(Paths.get(hashFileName), digestA, CREATE);
    }

    public static void main(String[] args) throws Exception {
        new Creatror();
    }
}
