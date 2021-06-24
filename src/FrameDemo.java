import Dao.AccountDao;
import Dao.MenuDao;
import Service.AccountService;
import Service.MenuService;
import entity.Dish;
import entity.DishDetail;
import entity.User;
import utils.R;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * @author guan
 */

public class FrameDemo extends JFrame {
    JButton loginButton = null;
    JButton landButton = null;

    JButton exitButton = null;

    JPanel mainPanel = null;

    JDialog dialog = null;


    public FrameDemo() {
        loginButton = new JButton("         注册          ");
        landButton = new JButton("           登陆          ");
        exitButton = new JButton("          注销          ");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDialog_1(); //显示窗口
                FrameDemo a = new FrameDemo();
            }

        });

        landButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDialog_2(); //显示窗口
            }

        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //关闭窗口

            }

        });

        mainPanel = new JPanel();
        mainPanel.add(loginButton);
        mainPanel.add(landButton);
        mainPanel.add(exitButton);

        this.add(mainPanel); //将主面板添加到frame中

    }

    /**
     * 显示对话框
     */

    private void showDialog_1() {
//        this.setVisible(false);
//
//        dialog = new JDialog(this, true);
//
//        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
//
//        dialog.setSize(300,180);
//
//        dialog.setTitle("DialogTest");
//
//        dialog.add(new JLabel("这个是对话框"));
//
//        dialog.setLocationRelativeTo(this);
//
//        dialog.setVisible(true); //显示对话框，窗口阻塞，不往下执行，只有等到对话框关闭了才往下执行。
//
////判断主窗口是否是隐藏的，如果是隐藏的就显示
//
//        if (!this.isVisible()) {
//            this.setVisible(true);
//
//        }
        ComputerFrame_1 fr = new ComputerFrame_1();
        fr.setLocationRelativeTo(null);
    }

    private void showDialog_2() {

        ComputerFrame_2 fr = new ComputerFrame_2();
        fr.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {


        JFrame frame = new FrameDemo();

        frame.setTitle("食谱管理系统");

        frame.setSize(250, 150);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
}

class ComputerFrame_1 extends JFrame implements ActionListener {

    JTextField text_1, text_2, text_3;
    JButton buttonAdd, buttonSub;
    JLabel label;
    JPasswordField passwordField;
    public ComputerFrame_1() {
        setLayout(new FlowLayout());

        text_1 = new JTextField(23);
        text_2 = new JTextField(23);
        passwordField = new JPasswordField(23);
        text_3 = new JTextField(23);
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(new JLabel("用户名:"));
        add(text_1);
        add(label);
        add(new JLabel(" 账号:"));
        add(text_2);
        add(label);
        add(new JLabel(" 密码:"));
        add(passwordField);
        add(label);
        buttonAdd = new JButton("提交");
        buttonSub = new JButton("取消");

        add(buttonAdd);
        add(buttonSub);

        buttonAdd.addActionListener(this);
        buttonSub.addActionListener(this);

        setSize(300, 170);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonAdd) {
            String n1, n2, n3;
            try {
                AccountDao accountDao = new AccountDao();
                AccountService accountService = new AccountService(accountDao);
                n1 = text_1.getText();
                n2 = text_2.getText();
                char[] pass = passwordField.getPassword();
                String password = new String(pass);

                User user = new User(n1, n2, password);
                R R = accountService.register(user);
                if (R.getCode() != 200) {
                    label.setText("注册失败" + R.getMessage());
                } else {
                    label.setText("注册成功");
                    dispose();
                }

            } catch (NumberFormatException ee) {
                text_3.setText("请输入数字字符");
            }
            ;
        } else if (e.getSource() == buttonSub) {
            dispose();
        }
        validate();
    }
}

class ComputerFrame_2 extends JFrame implements ActionListener {

    JTextField text_2, text_3;
    JButton buttonAdd, buttonSub;
    JLabel label;
    JPasswordField passwordField;
    public ComputerFrame_2() {

        setLayout(new FlowLayout());

        text_2 = new JTextField(23);
        text_3 = new JTextField(23);
        passwordField = new JPasswordField(23);
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(new JLabel(" 账号:"));
        add(text_2);
        add(label);
        add(new JLabel("  密码:"));
        add(passwordField);
        add(label);
        buttonAdd = new JButton("登陆");
        buttonSub = new JButton("取消");

        add(buttonAdd);
        add(buttonSub);

        buttonAdd.addActionListener(this);
        buttonSub.addActionListener(this);

        setSize(300, 170);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonAdd) {
            String n1, n2, n3;
            try {
                AccountDao accountDao = new AccountDao();
                AccountService accountService = new AccountService(accountDao);
                n2 = text_2.getText();
                char[] pass = passwordField.getPassword();
                String password = new String(pass);
                R R = accountService.login(n2, password);
                if (R.getCode() != 200) {
                    label.setText("=====登陆失败" + R.getMessage()+"=====");
                } else {
                    label.setText("成功");
                    dispose();
                    ComputerFrame_3 fr = new ComputerFrame_3();
                    fr.setLocationRelativeTo(null);
                }
            } catch (NumberFormatException ee) {
                text_3.setText("请输入数字字符");
            }
            ;
        } else if (e.getSource() == buttonSub) {
            dispose();
        }
        validate();
    }
}

class ComputerFrame_3 extends JFrame implements ActionListener {
    JTextField text1;
    JButton button_1, button_2, button_3, button_4, button_5, button_6;
    JLabel label;

    public ComputerFrame_3() {
        setLayout(new FlowLayout());


        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);


        button_1 = new JButton("       查看所有的食谱       ");
        button_2 = new JButton("            添加食谱             ");
        button_3 = new JButton("            删除食谱             ");
        button_4 = new JButton("     设置早中晚餐食谱       ");
        button_5 = new JButton("     公布早中晚餐食谱       ");
        button_6 = new JButton("         ==返   回==          ");


        add(button_1);
        add(button_2);
        add(button_3);
        add(button_4);
        add(button_5);
        add(button_6);
        button_1.addActionListener(this);
        button_2.addActionListener(this);
        button_3.addActionListener(this);
        button_4.addActionListener(this);
        button_5.addActionListener(this);
        button_6.addActionListener(this);

        setSize(300, 240);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        double n;

        if (e.getSource() == button_1) {
            dispose();
            ComputerFrame_4 fr = new ComputerFrame_4();
            fr.setLocationRelativeTo(null);
        } else if (e.getSource() == button_2) {
            dispose();
            ComputerFrame_5 fr = new ComputerFrame_5();
            fr.setLocationRelativeTo(null);
        } else if (e.getSource() == button_3) {
            ComputerFrame_6 fr = new ComputerFrame_6();
            fr.setLocationRelativeTo(null);
            dispose();
        } else if (e.getSource() == button_4) {
            ComputerFrame_7 fr = new ComputerFrame_7();
            fr.setLocationRelativeTo(null);
            dispose();
        } else if (e.getSource() == button_5) {
            ComputerFrame_8 fr = new ComputerFrame_8();
            fr.setLocationRelativeTo(null);
            dispose();
        } else if (e.getSource() == button_6) {
            dispose();
        }
        validate();
    }
}

//============================================查看所有菜谱打开窗口==========================================================
class ComputerFrame_4 extends JFrame implements ActionListener {


    JButton buttonAdd, buttonSub;
    JLabel label;

    public ComputerFrame_4() {
        buttonAdd = new JButton("         刷新         ");
        buttonSub = new JButton("         返回         ");
        add(buttonAdd);
        add(buttonSub);
        buttonAdd.addActionListener(this);
        buttonSub.addActionListener(this);

        setLayout(new FlowLayout());
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(label);

//        buttonAdd=new JButton("提交");
//        buttonSub=new JButton("取消");

        MenuDao menuDao_1 = new MenuDao();
        MenuService menuService_1 = new MenuService(menuDao_1);
        R R = menuService_1.queryAll();
        ArrayList arrayList;
        arrayList = (ArrayList) R.getData();
        String string_1 = "<html><body>";
        for (Object dish : arrayList) {
            Dish dish1 = (Dish) dish;
            JLabel jLabel = new JLabel(" ", JLabel.CENTER);
            jLabel.setBackground(Color.green);
            add(jLabel);
            string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
            //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
            //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());

        }
        string_1 = string_1 + "<body></html>";
        JLabel jLabel = new JLabel(" ", JLabel.CENTER);
        jLabel.setBackground(Color.green);
        add(jLabel);
        jLabel.setText(string_1);

//        add(buttonAdd);
//        add(buttonSub);

//        buttonAdd.addActionListener(this);
//        buttonSub.addActionListener(this);


        setSize(300, 320);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonAdd) {
            dispose();
            ComputerFrame_4 fr = new ComputerFrame_4();
            fr.setLocationRelativeTo(null);
        } else if (e.getSource() == buttonSub) {
            dispose();
            ComputerFrame_3 fr = new ComputerFrame_3();
            fr.setLocationRelativeTo(null);
        }
        validate();
    }
}

//====================================添加食谱==============================================
class ComputerFrame_5 extends JFrame implements ActionListener {
    JTextField text1, text2, text3;
    JButton buttonset, buttonF5, buttonBack;
    JLabel label;

    public ComputerFrame_5() {
        setLayout(new FlowLayout());
        text1 = new JTextField(20);
        text2 = new JTextField(20);
        text3 = new JTextField(20);
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(new JLabel("  食谱id:"));
        add(text1);
        add(label);
        add(new JLabel(" 食谱名称:"));
        add(text2);
        add(label);
        add(new JLabel(" 食谱价格:"));
        add(text3);
        add(label);
        buttonset = new JButton("提交");
        buttonF5 = new JButton("刷新");
        buttonBack = new JButton("退出");

        add(buttonset);
        add(buttonF5);
        add(buttonBack);
        buttonset.addActionListener(this);
        buttonF5.addActionListener(this);
        buttonBack.addActionListener(this);

        setSize(300, 150);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonset) {
            MenuDao menuDao = new MenuDao();
            MenuService menuService = new MenuService(menuDao);
            String n1, n2, n3;
            n1 = text1.getText();
            n2 = text2.getText();
            n3 = text3.getText();
            DishDetail dishDetail = new DishDetail(n2, n3);
            Dish dish = new Dish(n1, dishDetail);
            R R = menuService.addDish(dish);
            if (R.getCode() != 200) {
                label.setText(R.getMessage());
            } else {
                label.setText("添加成功");

            }

        } else if (e.getSource() == buttonF5) {
            dispose();
            ComputerFrame_5 fr = new ComputerFrame_5();
            fr.setLocationRelativeTo(null);
        } else if (e.getSource() == buttonBack) {
            dispose();
            ComputerFrame_3 fr = new ComputerFrame_3();
            fr.setLocationRelativeTo(null);
        }
        validate();
    }

}

//====================================删除食谱==============================================
class ComputerFrame_6 extends JFrame implements ActionListener {
    JTextField text1;
    JButton buttonSet, buttonBack;
    JLabel label;

    public ComputerFrame_6() {


        setLayout(new FlowLayout());
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(label);

//        buttonAdd=new JButton("提交");
//        buttonSub=new JButton("取消");

        MenuDao menuDao_1 = new MenuDao();
        MenuService menuService_1 = new MenuService(menuDao_1);
        R R = menuService_1.queryAll();
        ArrayList arrayList;
        arrayList = (ArrayList) R.getData();
        String string_1 = "<html><body>";
        for (Object dish : arrayList) {
            Dish dish1 = (Dish) dish;
            JLabel jLabel = new JLabel(" ", JLabel.CENTER);
            jLabel.setBackground(Color.green);
            add(jLabel);
            string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
            //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
            //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());

        }
        string_1 = string_1 + "<body></html>";
        JLabel jLabel = new JLabel(" ", JLabel.CENTER);
        jLabel.setBackground(Color.green);
        add(jLabel);
        jLabel.setText(string_1);

//        add(buttonAdd);
//        add(buttonSub);

//        buttonAdd.addActionListener(this);
//        buttonSub.addActionListener(this);


        setSize(300, 200);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        text1 = new JTextField(23);
        add(text1);
        add(label);

        add(new JLabel("==========删除食谱id=========="));
        buttonSet = new JButton("      提交       ");
        buttonBack = new JButton("      返回       ");

        add(buttonSet);
        add(buttonBack);

        buttonSet.addActionListener(this);
        buttonBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonSet) {
            MenuDao menuDao = new MenuDao();
            MenuService menuService = new MenuService(menuDao);
            String n1;
            n1 = text1.getText();
            R R2 = menuService.deleteDish(n1);
            if (R2.getCode() != 200) {
                label.setText(R2.getMessage());

            } else {
                label.setText("删除成功");
                dispose();
                ComputerFrame_6 fr = new ComputerFrame_6();
                fr.setLocationRelativeTo(null);
            }

        } else if (e.getSource() == buttonBack) {
            dispose();
            ComputerFrame_3 fr = new ComputerFrame_3();
            fr.setLocationRelativeTo(null);
        }
        validate();
    }
}

//====================================设置早中晚餐==============================================
class ComputerFrame_7 extends JFrame implements ActionListener {
    JTextField text1, text2, text3;
    JButton buttonSet, buttonBack;
    JLabel label;

    public ComputerFrame_7() {


        setLayout(new FlowLayout());
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(label);

//        buttonAdd=new JButton("提交");
//        buttonSub=new JButton("取消");

        MenuDao menuDao_1 = new MenuDao();
        MenuService menuService_1 = new MenuService(menuDao_1);
        R R = menuService_1.queryAll();
        ArrayList arrayList;
        arrayList = (ArrayList) R.getData();
        String string_1 = "<html><body>";
        for (Object dish : arrayList) {
            Dish dish1 = (Dish) dish;
            JLabel jLabel = new JLabel(" ", JLabel.CENTER);
            jLabel.setBackground(Color.green);
            add(jLabel);
            string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
            //                    System.out.println('\t' + dish1.getId() + '\t' + dish1.getDishDetail().toString());
            //System.out.println(dish1.getId() + "\t" + dish1.getDishDetail().toString());

        }
        string_1 = string_1 + "<body></html>";
        JLabel jLabel = new JLabel(" ", JLabel.CENTER);
        jLabel.setBackground(Color.green);
        add(jLabel);
        jLabel.setText(string_1);

//        add(buttonAdd);
//        add(buttonSub);

//        buttonAdd.addActionListener(this);
//        buttonSub.addActionListener(this);


        setSize(300, 320);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("==========早餐食谱设定id=========="));
        text1 = new JTextField(23);
        add(text1);
        add(label);

        add(new JLabel("==========午餐食谱设定id=========="));
        text2 = new JTextField(23);
        add(text2);
        add(label);

        add(new JLabel("==========晚餐食谱设定id=========="));
        text3 = new JTextField(23);
        add(text3);
        add(label);


        buttonSet = new JButton("      提交       ");
        buttonBack = new JButton("      返回       ");

        add(buttonSet);
        add(buttonBack);

        buttonSet.addActionListener(this);
        buttonBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonSet) {
            MenuDao menuDao = new MenuDao();
            MenuService menuService = new MenuService(menuDao);
            String n1, n2, n3;
            String string_1 = "<html><body>";
            n1 = text1.getText();
            n2 = text2.getText();
            n3 = text3.getText();
            //=================================
            R R_1 = new R(200,""),R_2 =  new R(200,""),R_3 =  new R(200,"");
            if (!n1.equals("")){
                 R_1 = menuService.setMorning(n1);
                if (R_1.getCode() == 200) {

                } else {
                    string_1 = string_1 + "===未找到早餐食谱id,请重新输入===" + "<br>";

                }
            }
            //=================================
            if (!n2.equals("")){
                 R_2 = menuService.setAfternoon(n2);
                if (R_2.getCode() == 200) {

                } else {
                    string_1 = string_1 + "===未找到午餐食谱id,请重新输入===" + "<br>";

                }
            }
            //=================================
            if (!n3.equals("")){
                 R_3 = menuService.setEvening(n3);
                if (R_3.getCode() == 200) {

                } else {
                    string_1 = string_1 + "===未找到晚餐食谱id,请重新输入===" + "<br>";

                }
            }
            if (R_1.getCode() == 200 && R_2.getCode() == 200 && R_3.getCode() == 200) {
                label.setText("============设置成功============");
            } else {
                string_1 = string_1 + "<body></html>";
                label.setText(string_1);
            }

        } else if (e.getSource() == buttonBack) {
            dispose();
            ComputerFrame_3 fr = new ComputerFrame_3();
            fr.setLocationRelativeTo(null);
        }
        validate();
    }
}

//====================================展示早中晚餐==============================================
class ComputerFrame_8 extends JFrame implements ActionListener {

    JButton buttonF5, buttonBack;
    JLabel label;

    public ComputerFrame_8() {

        setLayout(new FlowLayout());
        label = new JLabel(" ", JLabel.CENTER);
        label.setBackground(Color.green);
        add(label);

        setSize(300, 320);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        MenuDao menuDao = new MenuDao();
        MenuService menuService = new MenuService(menuDao);
        ArrayList arrayList_1_1 = (ArrayList) menuService.queryAll().getData();
        add(new JLabel("=============早餐食谱公布============="));
        for (Object dish_1_1 : arrayList_1_1) {
            Dish dish1 = (Dish) dish_1_1;
            if (dish1.isMorning()) {
                String string_1 = "<html><body>";
                string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
                string_1 = string_1 + "<body></html>";
                add(new JLabel(string_1));
            }
        }

        add(new JLabel("=============午餐食谱公布============="));
        for (Object dish_1_1 : arrayList_1_1) {
            Dish dish1 = (Dish) dish_1_1;
            if (dish1.isAfternoon()) {
                String string_1 = "<html><body>";
                string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
                string_1 = string_1 + "<body></html>";
                add(new JLabel(string_1));
            }
        }

        add(new JLabel("=============晚餐食谱公布============="));
        for (Object dish_1_1 : arrayList_1_1) {
            Dish dish1 = (Dish) dish_1_1;
            if (dish1.isEvening()) {
                String string_1 = "<html><body>";
                string_1 = string_1 + ("[" + dish1.getId() + "]" + "            " + dish1.getDishDetail().toString_1() + "    " + dish1.getDishDetail().toString_2() + "<br>");
                string_1 = string_1 + "<body></html>";
                add(new JLabel(string_1));
            }
        }
        add(new JLabel("===================================="));

        buttonF5 = new JButton("      刷新       ");
        buttonBack = new JButton("      返回       ");

        add(buttonF5);
        add(buttonBack);

        buttonF5.addActionListener(this);
        buttonBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        double n;
        if (e.getSource() == buttonF5) {
            dispose();
            ComputerFrame_8 fr = new ComputerFrame_8();
            fr.setLocationRelativeTo(null);
        } else if (e.getSource() == buttonBack) {
            dispose();
            ComputerFrame_3 fr = new ComputerFrame_3();
            fr.setLocationRelativeTo(null);
        }
        validate();
    }
}