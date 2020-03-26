package cn.itcast.day11.red;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 红包的框架 RedPacketFrame
 *
 * AWT / Swing / JavaFX
 *
 * @author 不是我
 */
public abstract class RedPacketFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final String DIR = "day11-code\\pic";

    private ArrayList<Integer> moneyList = null;

    private static int initMoney = 0;
    private static int totalMoney = 0; // 单位为“分”
    private static int count = 0;

    private static HashMap<JPanel, JLabel> panelLable = new HashMap<>();

    // 设置字体
    private static Font fontYaHei = new Font("微软雅黑", Font.BOLD, 20);
    private static Font msgFont = new Font("微软雅黑", Font.BOLD, 20);
    private static Font totalShowFont = new Font("微软雅黑", Font.BOLD, 40);
    private static Font nameFont = new Font("微软雅黑", Font.BOLD, 40);
    private static Font showNameFont = new Font("微软雅黑", Font.BOLD, 20);
    private static Font showMoneyFont = new Font("微软雅黑", Font.BOLD, 50);
    private static Font showResultFont = new Font("微软雅黑", Font.BOLD, 15);

    /**
     * 窗体大小 WIDTH:400 HEIGHT:600
     */
    private static final int FRAME_WIDTH = 416; // 静态全局窗口大小
    private static final int FRAME_HEIGHT = 650;
    private static JLayeredPane layeredPane = null;

    /// private static JPanel contentPane = null;

    /**
     * page1:输入页面 - InputPanel . 组件和初始化!
     */
    private static JPanel inputPanel = new JPanel();

    // private static JTextField input_total = new JTextField("200"); // 测试用
    // private static JTextField input_count = new JTextField("3"); // 测试用
    private static JTextField input_total = new JTextField();
    private static JTextField input_count = new JTextField();
    private static JTextField input_people = new JTextField("30");
    private static JTextField input_msg = new JTextField("恭喜发财  ,  大吉大利");
    private static JTextField input_total_show = new JTextField("$ " + input_total.getText().trim());
    private static JLabel input_inMoney = new JLabel(); // 不可见
    private static JLabel input_bg_label = new JLabel(new ImageIcon(DIR + "\\01_input.jpg"));

    static {

        // 设置位置
        input_total.setBounds(200, 90, 150, 50);
        input_count.setBounds(200, 215, 150, 50);
        input_people.setBounds(90, 275, 25, 30);
        input_msg.setBounds(180, 340, 200, 50);
        input_total_show.setBounds(130, 430, 200, 80);
        input_inMoney.setBounds(10, 535, 380, 65);
        input_bg_label.setBounds(0, 0, 400, 600); // 背景

        // 设置字体
        input_total.setFont(fontYaHei);
        input_count.setFont(fontYaHei);
        input_people.setFont(fontYaHei);
        input_msg.setFont(msgFont);
        input_msg.setForeground(new Color(255, 233, 38)); // 字体颜色 为金色
        input_total_show.setFont(totalShowFont);
        input_inMoney.setFont(fontYaHei);

        // 透明
        input_people.setOpaque(false);
        input_total_show.setOpaque(false);
        // 编 辑 -- 不可编辑
        input_people.setEditable(false);
        input_total_show.setEditable(false);

        // 边界 -- 无
        input_total.setBorder(null);
        input_count.setBorder(null);
        input_people.setBorder(null);
        input_msg.setBorder(null);
        input_total_show.setBorder(null);

    }

    /**
     * page2:打开页面 - openPanel . 组件和初始化!
     */
    private static JPanel openPanel = new JPanel();

    private static JTextField open_ownerName = new JTextField("谁谁谁");
    private static JLabel open_label = new JLabel(new ImageIcon(DIR + "\\02_open_2.gif"));
    private static JLabel open_bg_label = new JLabel(new ImageIcon(DIR + "\\02_open_1.jpg"));

    static {

        // 设置 位置.
        open_ownerName.setBounds(0, 110, 400, 50);
        open_bg_label.setBounds(0, 0, 400, 620);
        open_label.setBounds(102, 280, 200, 200);
        open_ownerName.setHorizontalAlignment(JTextField.CENTER);

        // 设置字体
        open_ownerName.setFont(nameFont);
        open_ownerName.setForeground(new Color(255, 200, 163)); // 字体颜色 为金色

        // 背景色
        // open_name.setOpaque(false);
        open_ownerName.setBackground(new Color(219, 90, 68));

        // 不可编辑
        open_ownerName.setEditable(false);
        // 边框
        open_ownerName.setBorder(null);

    }

    /**
     * page3:展示页面 - showPanel . 组件和初始化!
     */
    private static JPanel showPanel = new JPanel();
    private static JPanel showPanel2 = new JPanel();
    private static JScrollPane show_jsp = new JScrollPane(showPanel2);

    private static JLabel show_bg_label = new JLabel(new ImageIcon(DIR + "\\03_money_1.jpg"));

    private static JTextField show_name = new JTextField("用户名称");
    private static JTextField show_msg = new JTextField("祝福信息");
    private static JTextField show_money = new JTextField("99.99");
    private static JTextField show_result = new JTextField(count + "个红包共" + (totalMoney / 100.0) + "元,被抢光了");

    static {
        // 分别设置水平和垂直滚动条自动出现
        // jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        /*
         * 两部分 页面 . 1.本人获得的红包-- showPanel 2.别人获得的红包-- show_jsp
         */
        show_name.setBounds(125, 180, 100, 30);
        show_name.setOpaque(false);
        show_name.setBorder(null);
        show_name.setFont(showNameFont);

        show_msg.setBounds(0, 220, 400, 30);
        show_msg.setOpaque(false);
        show_msg.setBorder(null);
        show_msg.setFont(msgFont);
        show_msg.setHorizontalAlignment(JTextField.CENTER);

        show_money.setBounds(0, 270, 250, 40);
        show_money.setOpaque(false);
        show_money.setBorder(null);
        show_money.setFont(showMoneyFont);
        show_money.setForeground(new Color(255, 233, 38)); // 字体颜色 为金色
        show_money.setHorizontalAlignment(SwingConstants.RIGHT);

        show_result.setBounds(10, 460, 400, 20);
        show_result.setOpaque(false);
        show_result.setBorder(null);
        show_result.setFont(showResultFont);
        show_result.setForeground(new Color(170, 170, 170)); // 字体颜色 为灰色

        // 设置 图片.
        show_bg_label.setBounds(0, 0, 400, 500);

    }

    static {

        // 页面和 背景的对应关系.
        panelLable.put(inputPanel, input_bg_label);
        panelLable.put(openPanel, open_bg_label);
        panelLable.put(showPanel, show_bg_label);
    }

    private void init() {
        // 层次面板-- 用于设置背景
        layeredPane = this.getLayeredPane();
//        System.out.println("层次面板||" + layeredPane);
        // System.out.println(layeredPane);

        // 初始化框架 -- logo 和基本设置
        initFrame();
        // 初始化 三个页面 -- 准备页面
        initPanel();

        // 2.添加 页面 --第一个页面, 输入 panel 设置到 页面上.
        setPanel(inputPanel);

        // 3.添加 监听
        addListener();
    }


    /**
     * 初始化框架 -- logo 和基本设置
     */
    private void initFrame() {
        // logo
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(DIR + "\\logo.gif"));
//        System.out.println("LOGO初始化...");

        // 窗口设置
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT); // 设置界面大小
        this.setLocation(280, 30); // 设置界面出现的位置
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        // 测试期 注释 拖 拽 , 运行放开
        // this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * 初始化页面-- 准备三个页面
     */

    private void initPanel() {
//        System.out.println("页面初始化...");
        initInputPanel();
        initOpenPanel();
        initShowPanel();

    }

    private void initInputPanel() {

        inputPanel.setLayout(null);
        inputPanel.setBounds(0, -5, 400, 600);

        // this.add(bg_label);
        inputPanel.add(input_total);
        inputPanel.add(input_count);
        inputPanel.add(input_people);
        inputPanel.add(input_msg);
        inputPanel.add(input_total_show);
        inputPanel.add(input_inMoney);

//        System.out.println("输入页面||" + inputPanel);

    }

    private void initOpenPanel() {
        openPanel.setLayout(null);
        openPanel.setBounds(0, 0, 400, 600);
        // this.add(bg_label);
        openPanel.add(open_ownerName);
        openPanel.add(open_label);
//        System.out.println("打开页面||" + openPanel);
    }

    private void initShowPanel() {
        showPanel.setLayout(null);
        showPanel.setBounds(10, 10, 300, 600);

        // ==============
        showPanel.add(show_name);
        showPanel.add(show_msg);
        showPanel.add(show_money);
        showPanel.add(show_result);
//        System.out.println("展示页面||" + showPanel);
        // ====================================
        // showPanel2.setLayout(null);
        // showPanel2.setBounds(0, 500, 401, 300);

        showPanel2.setPreferredSize(new Dimension(300, 1000));
        showPanel2.setBackground(Color.white);

        show_jsp.setBounds(0, 500, 400, 110);

    }

    /**
     * 每次打开页面, 设置 panel的方法
     */
    private void setPanel(JPanel panel) {
        // 移除当前页面
        layeredPane.removeAll();

//        System.out.println("重新设置:新页面");
        // 背景lable添加到layeredPane的默认层
        layeredPane.add(panelLable.get(panel), JLayeredPane.DEFAULT_LAYER);

        // 面板panel设置为透明
        panel.setOpaque(false);

        // 面板panel 添加到 layeredPane的modal层
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);
    }

    // private void setShowPanel(JPanel show) {
    // setPanel(show);
    // layeredPane.add(show_jsp, JLayeredPane.MODAL_LAYER);
    //
    // }

    /**
     * 设置组件的监听器
     */
    private void addListener() {

        input_total.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // System.out.println(e);
                String input_total_money = input_total.getText();
                input_total_show.setText("$ " + input_total_money);
            }
        });

        input_count.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                // System.out.println(e);
//                System.out.println("个数:" + input_count.getText());
            }
        });
        input_msg.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                // System.out.println(e);
//                System.out.println("留言:" + input_msg.getText());
            }
        });

        input_inMoney.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    // 获取页面的值.
                    totalMoney = (int) (Double.parseDouble(input_total.getText()) * 100); // 转换成"分"
                    count = Integer.parseInt(input_count.getText());
                    if (count > 30) {
                        JOptionPane.showMessageDialog(null, "红包个数不得超过30个", "红包个数有误", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    initMoney = totalMoney;

                    System.out.println("总金额：[" + totalMoney + "]分");
                    System.out.println("红包个数：[" + count + "]个");

                    input_inMoney.removeMouseListener(this);

//                    System.out.println("跳转-->打开新页面");

                    // 设置群主名称
                    open_ownerName.setText(ownerName);
                    // 设置打开页面
                    setPanel(openPanel);

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "请输入正确【总金额】或【红包个数】", "输入信息有误", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        // open_ownerName ,点击 [名称],触发的方法 , 提示如何设置群主名称.

        open_ownerName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                JOptionPane.showMessageDialog(null, "请通过【setOwnerName】方法设置群主名称", "群主名称未设置",
                        JOptionPane.QUESTION_MESSAGE);
            }
        });

        // open label , 点击 [开],触发的方法,提示如何设置打开方式.
        open_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (openWay == null) {
                    JOptionPane.showMessageDialog(null, "请通过【setOpenWay】方法设置打开方式", "打开方式未设置",
                            JOptionPane.QUESTION_MESSAGE);
                    return;
                }

//                System.out.println("跳转-->展示页面");

                moneyList = openWay.divide(totalMoney, count);

//                System.out.println(moneyList);
                /*
                 * showPanel 添加数据
                 *
                 */
                show_name.setText(ownerName);
                show_msg.setText(input_msg.getText());
                if (moneyList.size() > 0) {
                    show_money.setText(moneyList.get(moneyList.size() - 1) / 100.0 + "");
                }
                show_result.setText(count + "个红包共" + (initMoney / 100.0) + "元,被抢光了");

                open_label.removeMouseListener(this);

                setPanel(showPanel);

                // 添加数据
                for (int i = 0; i < moneyList.size(); i++) {

                    JTextField tf = new JTextField();
                    tf.setBorder(null);
                    tf.setFont(showNameFont);
                    tf.setHorizontalAlignment(JTextField.LEFT);
                    if (i == moneyList.size() - 1) {
                        tf.setText(ownerName + "：\t" + moneyList.get(i) / 100.0 + "元");

                    } else {

                        tf.setText("群成员-" + i + "：\t" + moneyList.get(i) / 100.0 + "元");
                    }
                    showPanel2.add(tf);
                }

                layeredPane.add(show_jsp, JLayeredPane.MODAL_LAYER);
            }

        });

    }

    /* ======================================================================
     * **********************************************************************
     * * 以上代码均为页面部分处理,包括布局/互动/跳转/显示等,大家							*
     * *																	*
     * *																	*
     * **********************************************************************
     * ======================================================================
     */

    /**
     * ownerName : 群主名称
     */
    private String ownerName = "谁谁谁"; // 群主名称
    /**
     * openWay : 红包的类型 [普通红包/手气红包]
     */
    private OpenMode openWay = null;


    /**
     * 构造方法：生成红包界面。
     *
     * @param title 界面的标题
     */

    public RedPacketFrame(String title) {
        super(title);

        // 页面相关的初始化
        init();
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOpenWay(OpenMode openWay) {
        this.openWay = openWay;
    }


}