package jqz.fps.Forms;

import jqz.fps.Utilities.Images;
import jqz.fps.Utilities.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class Credits extends JDialog{
    private JPanel jpPrincipal;
    private JScrollPane jspPanels;
    private JButton jbClose;
    private JPanel jpPanels;

    public Credits(){
        add_credits(new String[]{"Pokemon Data", "All the data and sprites was obtained from PokeApi", "https://pokeapi.co/"});
        add_credits(new String[]{"Types Icons", "6th gen icons was obtained from the Bulbapedia's types page", "https://bulbapedia.bulbagarden.net/wiki/Type"});
        add_credits(new String[]{"App Icon", "The app's icon (pokeball) was obtained from flaticon.es", "https://www.flaticon.es/iconos-gratis/pokemon"});
        jbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        start_form();
    }

    private void add_credits(String[] credits) {
        jpPanels.setLayout(new BoxLayout(jpPanels, BoxLayout.Y_AXIS));

        JPanel creditPanel = new JPanel();
        creditPanel.setLayout(new BoxLayout(creditPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("<html><b>" + credits[0] + "</b></html>");
        titleLabel.setFont(new Font(jpPrincipal.getFont().getFontName(), Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditPanel.add(titleLabel);

        creditPanel.add(Box.createVerticalStrut(10));

        JLabel descriptionLabel = new JLabel(credits[1]);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditPanel.add(descriptionLabel);

        creditPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        creditPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() != MouseEvent.BUTTON1) return;
                if (java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        try {
                            java.net.URI uri = new java.net.URI(credits[2]);
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

        jpPanels.add(creditPanel);
        jpPanels.add(Box.createVerticalStrut(20));
    }

    private void start_form(){
        setTitle(Language.language.get(39));
        setResizable(false);
        setModal(true);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(457, 320);
        setLocationRelativeTo(null);
        setIconImage(Images.get_asset_icon("pokeball").getImage());

        jbClose.setText(Language.language.get(4));
    }

}
