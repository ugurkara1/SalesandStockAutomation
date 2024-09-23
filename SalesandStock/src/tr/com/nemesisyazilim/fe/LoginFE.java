package tr.com.nemesisyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import tr.com.nemesisyazilim.dal.AccountsDAL;
import tr.com.nemesisyazilim.dal.PersonelDAL;
import tr.com.nemesisyazilim.dal.Session;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.AccountsContract;
import tr.com.nemesisyazilim.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces {

    public static JComboBox emailBox;

    public LoginFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Lütfen sisteme giriş yapmak için bilgilerinizi giriniz."));
        add(panel);
        setTitle("Lütfen Giriş Yapınız");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
        panel.add(emailLabel);
        emailBox = new JComboBox(new PersonelDAL().GetALL().toArray());
        panel.add(emailBox);
        JLabel passwordLabel = new JLabel("Şifreniz:", JLabel.RIGHT);
        panel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField);

        JButton loginButton = new JButton("Giriş Yap");
        panel.add(loginButton);
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
                String sifre = new String(passwordField.getPassword()); // Şifreyi doğru şekilde al
                AccountsDAL accountsDAL = new AccountsDAL();
                AccountsContract account = accountsDAL.GetPersonelIdVeSifre(contract.getId(), sifre);
                if (account.getPersonelId() != 0) { // Şifre doğruysa
                    Session.loggedInUser = contract;

                    new AnaPencereFE();
                    dispose(); // Giriş penceresini kapat
                } else {
                    JOptionPane.showMessageDialog(null, "Giriş Başarısız");
                }
            }
        });

        iptalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // İptal butonuna tıklanırsa pencereyi kapat
            }
        });

        return panel;
    }

    @Override
    public JMenuBar initBar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
