/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.ex2;

import java.io.FilePermission;
import java.security.Permission;
import sun.security.util.SecurityConstants;

/**
 *
 * @author Maroine
 */
public class Fich1SecurityManager extends SecurityManager {

    private String fileName = "fich1.txt";

    @Override
    public void checkRead(String file) {
        if (!file.endsWith(fileName)) {
            throw new SecurityException("You can only read " + fileName);
        } else {
            super.checkRead(file);
        }
    }

}
