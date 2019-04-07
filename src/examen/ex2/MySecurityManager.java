/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.ex2;

/**
 *
 * @author Maroine
 */
public class MySecurityManager extends SecurityManager {

    private String repo = "C:\\netbeansProjects";

    public MySecurityManager() {
    }

    @Override
    public void checkRead(String file) {
        if (file.startsWith(repo)) {
            throw new SecurityException("You can't read file in this directory" + repo);
        }
    }
}
