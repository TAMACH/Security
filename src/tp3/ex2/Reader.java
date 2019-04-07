/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ex2;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author Maroine
 */
public class Reader {

    private String hashFileName = ".\\hash";
    private String publickeyFileName = ".\\publickey";
    private String fileName = ".\\message";

    PublicKey publickey;

    public void getTheKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(publickeyFileName));
        ObjectInput os = new ObjectInputStream(new FileInputStream(publickeyFileName));
        publickey = (PublicKey) os.readObject();
    }

    public Reader() throws Exception {
        getTheKey();
        byte[] hash = Files.readAllBytes(Paths.get(hashFileName));
        byte[] message = Files.readAllBytes(Paths.get(fileName));
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(publickey);
        sig.update(message);
        boolean verify = sig.verify(hash);
        System.out.println(verify);
    }

    public static void main(String[] args) throws Exception {
        new Reader();
    }
}
