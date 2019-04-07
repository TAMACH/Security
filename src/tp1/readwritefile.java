package tp1;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class readwritefile extends JApplet {

    private final String nomFichierLire = "../fichierlocal.txt"; // fichier local
    private final String nomFichierEcrire = "fichierecrire.txt"; // fichier à écrire
    private final JTextArea zoneTexte = new JTextArea(10, 50);

    @Override
    public void init() {
        //System.out.println("Nom du fichier local dans lequel on va lire : " + nomFichierLire);
        //System.out.println("Nom du fichier dans lequel on va éccrire : " + nomFichierEcrire);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(new JScrollPane(zoneTexte), BorderLayout.CENTER);

        JPanel panelBoutons = new JPanel();
        JButton lire = new JButton("Lire");
        lire.setToolTipText("Lire les lignes du fichier distant");
        lire.addActionListener(e -> lireLignes());
        panelBoutons.add(lire);

        JButton ecrire = new JButton("Enregistrer");
        ecrire.setToolTipText("Enregistrer localement les lignes de la zone de texte");
        ecrire.addActionListener(e -> enregistrerLignes());
        panelBoutons.add(ecrire);
        cp.add(panelBoutons, BorderLayout.SOUTH);
    }

    private void lireLignes() {
        String sep = System.getProperty("line.separator");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nomFichierLire));
            zoneTexte.setText("");
            String ligne;
            while ((ligne = br.readLine()) != null) {
                zoneTexte.append(ligne + sep);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showStatus("Impossible d'ouvrir le fichier" + nomFichierLire);
        } catch (IOException e) {
            e.printStackTrace();
            showStatus("Erreur pendant la lecture du fichier" + nomFichierLire);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Probléme pour fermer le fichier distant");
                    e.printStackTrace();
                }
            }
        } // finally
    }

    private void enregistrerLignes() {
        // Fichier placÃ© sous le rÃ©pertoire HOME de l'utilisateur
        String home = System.getProperty("user.home");
        String fs = System.getProperty("file.separator");
        String nomAbsoluFichierEcrire = home + fs + nomFichierEcrire;
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomFichierEcrire)));
            pw.print(zoneTexte.getText());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 300);
        JApplet applet = new readwritefile();
        applet.init();
        f.setContentPane(applet.getContentPane());
        f.setTitle("Un applet");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
