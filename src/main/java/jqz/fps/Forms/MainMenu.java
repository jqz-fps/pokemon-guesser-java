package jqz.fps.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenu extends JFrame {
    private JPanel jpPrincipal;
    private JButton jbStart;
    private JButton jbClose;
    private JLabel jlGithub;
    private JButton jbChangelog;

    public MainMenu() {
        start_form();
        jbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameScreen newGame = new GameScreen();
                newGame.setVisible(true);
                dispose();
            }
        });
        jlGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // so in this listener we make a connection to the desktop
                // and search the github page from the java app
                if(java.awt.Desktop.isDesktopSupported()){
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

                    if(desktop.isSupported(Desktop.Action.BROWSE)){
                        try {
                            java.net.URI uri = new java.net.URI("https://github.com/jqz-fps/pokemon-guesser-java");
                            try {
                                desktop.browse(uri);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        });
        jbChangelog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Changelog changelog = new Changelog();
                changelog.setVisible(true);
            }
        });
    }

    private void start_form(){
        setTitle("Main Menu");
        setResizable(false);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(302, 276);
        setLocationRelativeTo(null);
    }

}
