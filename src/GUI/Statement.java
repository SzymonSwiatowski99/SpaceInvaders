package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statement extends JFrame {
    private JButton ok;
    private int x;
    public Statement (int x,int width, int height){
        this.setBounds(0, 0, width, height);
        this.setUndecorated(true);
        this.x=x;
        initComponents();
    }
    public Statement (int x,int width, int height,int time){
        this.setBounds(0, 0, width, height);
        this.setUndecorated(true);
        this.x=x;
        initComponents();
    }
    public void initComponents() {
        if(x==1)ok = new JButton("Congratulation You Win");
        if(x==0)ok = new JButton("You Lose");
        ok.setBackground(new Color(36, 48, 72));
        ok.setForeground(Color.white);

        Container container = this.getContentPane();
        container.add(ok,BorderLayout.CENTER);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Statement.super.dispose();
                new Main().setVisible(true);

            }
        });
    }
}
