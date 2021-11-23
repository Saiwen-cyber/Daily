
package soa_lab4.cs_3.UI;

import soa_lab4.cs_3.BLL.LinkedManBLL;
import soa_lab4.cs_3.DAL.DateBase;
import soa_lab4.cs_3.BLL.LinkedMan;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainView extends JFrame {
    public MainView() throws SQLException {
        initComponents();
        this.setVisible(true);
    }
    private String headTitle[]=new String[]{"ID","名字","地址","电话号码"};
    private DefaultTableModel dtm=null;


        public Object[][] makeTable() throws SQLException {
        List<LinkedMan> list= LinkedManBLL.query();
        Object [][] data=new Object[list.size()][4];
        for(int i=0;i<list.size();i++){
            data[i][0]=list.get(i).getID();
            data[i][1]=list.get(i).getName();
            data[i][2]=list.get(i).getAddress();
            data[i][3]=list.get(i).getPhoneNumber();

        }
        return data;
    }
    private void deletButton2ActionPerformed(ActionEvent e) {
        int id = Integer.parseInt(textField1.getText());
        LinkedManBLL.delete(id);
        refreshButton.doClick();
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        LinkedMan man = new LinkedMan();
        man.setID(Integer.parseInt(textField1.getText()));
        man.setName(textField2.getText());
        man.setAddress(textField3.getText());
        man.setPhoneNumber(textField4.getText());
        LinkedManBLL.update(man);
        refreshButton.doClick();
    }

    private void refreshButtonActionPerformed(ActionEvent e){

        try {
            dtm=new DefaultTableModel(makeTable(),headTitle);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        table1.setModel(dtm);
    }

    private void addButtonActionPerformed(ActionEvent e) {
        LinkedMan man = new LinkedMan();
        //id可以自动生成。
        man.setID(Integer.parseInt(textField1.getText()));
        man.setName(textField2.getText());
        man.setAddress(textField3.getText());
        man.setPhoneNumber(textField4.getText());
        LinkedManBLL.add(man);
        refreshButton.doClick();
    }
    private void initComponents() throws SQLException {
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label5 = new JLabel();
        textField4 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        deletButton2 = new JButton();
        updateButton = new JButton();
        refreshButton = new JButton();
        addButton = new JButton();
        label1 = new JLabel();
        label7 = new JLabel();

        setTitle("Contacts");
        setBackground(Color.black);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        label2.setText("联系人ID");
        label2.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
        contentPane.add(label2);
        label2.setBounds(10, 285, 75, 19);
        contentPane.add(textField1);
        textField1.setBounds(95, 275, 80, textField1.getPreferredSize().height);

        label3.setText("联系人姓名");
        label3.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(180, 280), label3.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(265, 275, 90, textField2.getPreferredSize().height);

        label4.setText("电话号码");
        label4.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
        contentPane.add(label4);
        label4.setBounds(365, 270, label4.getPreferredSize().width, 35);
        contentPane.add(textField3);
        textField3.setBounds(440, 275, 95, textField3.getPreferredSize().height);

        label5.setText("联系地址");
        label5.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 16));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(10, 310), label5.getPreferredSize()));
        contentPane.add(textField4);
        textField4.setBounds(90, 305, 445, textField4.getPreferredSize().height);

        {
            scrollPane1.setBackground(new Color(211, 158, 214));

            //---- table1 ----
            table1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 14));
            table1.setBackground(Color.white);
            table1.setForeground(Color.black);
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 45, 530, 230);

        deletButton2.setText("删除");
        deletButton2.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
        deletButton2.addActionListener(e -> deletButton2ActionPerformed(e));
        contentPane.add(deletButton2);
        deletButton2.setBounds(new Rectangle(new Point(195, 340), deletButton2.getPreferredSize()));

        updateButton.setText("更新");
        updateButton.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
        updateButton.addActionListener(e -> updateButtonActionPerformed(e));
        contentPane.add(updateButton);
        updateButton.setBounds(new Rectangle(new Point(285, 340), updateButton.getPreferredSize()));

        refreshButton.setText("刷新");
        refreshButton.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
        refreshButton.addActionListener(e -> refreshButtonActionPerformed(e));
        contentPane.add(refreshButton);
        refreshButton.setBounds(new Rectangle(new Point(370, 340), refreshButton.getPreferredSize()));

        addButton.setText("增加");
        addButton.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
        addButton.addActionListener(e -> addButtonActionPerformed(e));
        contentPane.add(addButton);
        addButton.setBounds(new Rectangle(new Point(105, 340), addButton.getPreferredSize()));

        label1.setText("信息管理");
        label1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 18));
        contentPane.add(label1);
        label1.setBounds(10, 340, 100, 30);

        label7.setText("个人通讯录");
        label7.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 22));
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(210, 10), label7.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(545, 410));
        pack();
        setLocationRelativeTo(getOwner());

        dtm=new DefaultTableModel(makeTable(),headTitle);
        table1.setModel(dtm);
    }

    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label5;
    private JTextField textField4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton deletButton2;
    private JButton updateButton;
    private JButton refreshButton;
    private JButton addButton;
    private JLabel label1;
    private JLabel label7;
}
