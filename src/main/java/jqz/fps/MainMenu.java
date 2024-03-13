package jqz.fps;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JPanel jpPrincipal;
    private JButton startGameButton;
    private JButton closeButton;

    public MainMenu() {
        start_form();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameScreen newGame = new GameScreen();
                newGame.setVisible(true);
                dispose();
            }
        });
    }

    private void start_form(){
        setTitle("Game");
        setResizable(false);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(302, 276);
        setLocationRelativeTo(null);
    }

}
