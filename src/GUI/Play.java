package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Play extends JFrame  {
    private int width;
    private int height;
    private String gametitle;
    private JButton backToMenu;
    private int mode;
    private GroupLayout groupLayout;
    private JLabel wave;


    public Play(String gametitle, int width, int height, int mode) {
        this.width = width;
        this.height = height;
        this.gametitle = gametitle;
        this.mode = mode;
        this.setTitle(gametitle);
        this.setBounds(0, 0, width, height);
        this.setResizable(false);
        this.setVisible(false);
        this.repaint();
        this.setUndecorated(true);
        getContentPane().setBackground(Color.black);
        setFocusable(true);
        initComponents();


    }

    public void initComponents() {
        backToMenu = new JButton("back");
        Container container = this.getContentPane();
        container.add(backToMenu);
        Board board = new Board(width,height, mode);
        container.add(board);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT) board.setIsMove(2);
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT) board.setIsMove(1);
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT) board.setIsMove(0);
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT) board.setIsMove(0);
            }
        });

        int buttonSizeHorizontal = 80;
        int buttonSizeVertical = 30;

        groupLayout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(groupLayout);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addComponent(backToMenu, 0, buttonSizeVertical, buttonSizeVertical))

        ;

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addComponent(backToMenu, 0, buttonSizeHorizontal, buttonSizeHorizontal))
        ;
        backToMenu.setBackground(new Color(32, 32, 32));
        backToMenu.setForeground(Color.white);
        backToMenu.addActionListener(actionEvent -> {
            new Main().setVisible(true);
            Play.super.dispose();
        });
    }
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon i = new ImageIcon("kosmos.png");
        i.paintIcon(this, g, 0, 0);
        g.setColor(new Color(0x373738));
        g.fillRect(45, 45, width - 90, height - 140);

    }
}