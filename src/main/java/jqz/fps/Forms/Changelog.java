package jqz.fps.Forms;

import jqz.fps.Utilities.FileManager;
import jqz.fps.Utilities.Images;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Changelog extends JDialog {

    private JPanel jpPrincipal;
    private JScrollPane jsChangelog;
    private JTextPane jtpChangelog;
    private JButton jcbClose;

    public Changelog(){
        start_form();
        jtpChangelog.setContentType("text/html"); // this pane will have a html file 💀
        jtpChangelog.setText(FileManager.read_txt_file("assets\\changelog.html"));
        jtpChangelog.setCaretPosition(0);
        jcbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void start_form(){
        setTitle("Changelog");
        setResizable(true);
        setModal(true);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(420, 433);
        setLocationRelativeTo(null);
        setIconImage(Images.get_asset_icon("pokeball").getImage());
    }

}
