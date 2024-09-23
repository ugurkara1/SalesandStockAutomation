
package tr.com.nemesisyazilim.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.nemesisyazilim.dal.KategoriDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;

public class KategoriDüzenleFE extends JDialog implements FeInterfaces{

    public KategoriDüzenleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel=initPanel();
        add(panel);
        setTitle("Kategori Düzenle");
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel =new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Düzenleme işlemleri"));
        JPanel ustPanel=new JPanel(new GridLayout(3,2));
        ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Düzenle"));
        JLabel kategoriAdiLabel=new JLabel("Kategori Adı:",JLabel.RIGHT);
        ustPanel.add(kategoriAdiLabel);
        JTextField kategoriAdiField=new JTextField(10);
        ustPanel.add(kategoriAdiField);
        JLabel ustKategoriLabel=new JLabel("Üst Kategorisi:",JLabel.RIGHT);
        ustPanel.add(ustKategoriLabel);
        JComboBox ustKategoriBox=new JComboBox(new KategoriDAL().GetALL().toArray());
        ustPanel.add(ustKategoriBox);
        JList kategoriList=new JList();
        kategoriList.setListData(new KategoriDAL().GetALL().toArray());

        JScrollPane pane=new JScrollPane(kategoriList);
        pane.setBorder(BorderFactory.createTitledBorder("Düzenlecek liste"));
        kategoriList.setSelectedIndex(0);
        kategoriAdiField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                kategoriList.setListData(new KategoriDAL().GetSearchKategori(kategoriAdiField.getText()).toArray());
                kategoriList.setSelectedIndex(0);

            }
            
        });
        
        

        panel.add(ustPanel,BorderLayout.NORTH);
        panel.add(pane,BorderLayout.CENTER);
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
