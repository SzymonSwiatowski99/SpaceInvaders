package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Play extends JFrame  {
    private final int width;
    private final int height;
    private JButton backToMenu;
    private final int mode;
    private JButton pauseButton;


    public Play(String gametitle, int width, int height, int mode) {
        this.width = width;
        this.height = height;
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
        backToMenu = new JButton("back (b)");
        pauseButton = new JButton("Pause (P), Start (S)");
        Container container = this.getContentPane();
        container.add(backToMenu);
        container.add(pauseButton);
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
                if(keyEvent.getKeyCode()==KeyEvent.VK_P)board.setPause(true);
                if(keyEvent.getKeyCode()==KeyEvent.VK_S)board.setPause(false);
                if(keyEvent.getKeyCode()==KeyEvent.VK_B)backToMenu.doClick();
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT) board.setIsMove(0);
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT) board.setIsMove(0);
            }
        });

        int buttonSizeHorizontal = 80;
        int buttonSizeVertical = 30;

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(groupLayout);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(pauseButton,0,buttonSizeVertical,buttonSizeVertical)
                        .addComponent(backToMenu, 0, buttonSizeVertical, buttonSizeVertical)))

        ;

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addComponent(pauseButton,0, (int)(2.5*buttonSizeHorizontal),(int)(2.5*buttonSizeHorizontal))
                .addComponent(backToMenu, 0, buttonSizeHorizontal, buttonSizeHorizontal))
        ;
        groupLayout.setAutoCreateGaps(true);
        pauseButton.setForeground(Color.white);
        pauseButton.setBackground(new Color(32, 32, 32));
        pauseButton.addActionListener(e -> {
            if(board.isPause()) {
                board.setPause(false);
                this.requestFocus();
            }else {
                board.setPause(true);
                this.requestFocus();
            }
        });
        backToMenu.setBackground(new Color(32, 32, 32));
        backToMenu.setForeground(Color.white);
        backToMenu.addActionListener(actionEvent -> {
            board.setPause(true);
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
        backToMenu.repaint();
        pauseButton.repaint();
    }

}