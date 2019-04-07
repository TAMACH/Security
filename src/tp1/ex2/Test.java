/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.ex2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;

/**
 *
 * @author Maroine
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            System.setSecurityManager(new Fich1SecurityManager());
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("fich1.txt"));
        } catch (Exception e) {
            System.out.println("New file please");
        }

    }

}
