package jqz.fps.Forms;

import jqz.fps.DAO.DAOConfig;
import jqz.fps.Utilities.Language;
import jqz.fps.Main;

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
    private JComboBox jcbDarkMode;
    private JComboBox jcbLanguage;

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
        jcbLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbLanguage.getSelectedIndex() == 0) return;
                DAOConfig.update_language((byte) (jcbLanguage.getSelectedIndex() - 1));
                dispose();
                Language.language = Language.get_language();
                Main.main(null);
            }
        });
        jcbDarkMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbDarkMode.getSelectedIndex() == 0) return;
                DAOConfig.update_dark_mode((byte) (jcbDarkMode.getSelectedIndex() - 1));
                dispose();
                Main.main(null);
            }
        });
    }

    private void start_form(){
        setTitle(Language.language.get(1));
        setResizable(false);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(323, 349);
        setLocationRelativeTo(null);

        // Translations
        jcbDarkMode.removeAllItems();
        jcbDarkMode.addItem(Language.language.get(34));
        jcbDarkMode.addItem(Language.language.get(35));
        jcbDarkMode.addItem(Language.language.get(36));
        jcbLanguage.removeAllItems();
        jcbLanguage.addItem(Language.language.get(37));
        jcbLanguage.addItem("English");
        jcbLanguage.addItem("Español");
        jcbLanguage.addItem("Français");
        jcbLanguage.addItem("Portugués");
        jbStart.setText(Language.language.get(3));
        jbClose.setText(Language.language.get(4));
        jlGithub.setText(Language.language.get(2));

    }

}
