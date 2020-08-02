package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
        private JButton playButton;
        private JButton ScoreBoardButton;
        private JButton infinityModeButton;
        private GroupLayout groupLayout;
        private JLabel titleLabel;
        private String gametitle;
        private int width;
        private int height;
        public Main(){
            this.width = Toolkit.getDefaultToolkit().getScreenSize().width;
            this.height = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.gametitle = "Fly and shot";
            this.setTitle(gametitle);
            this.setBounds(0,0,width,height);
            this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
            this.setDefaultCloseOperation(3);
            this.setResizable(false);
            this.setVisible(true);
            this.repaint();
            initComponents();


        }


        public void initComponents(){
            playButton = new JButton("Play");
            ScoreBoardButton = new JButton("score table");
            infinityModeButton = new JButton("infinity");
            int buttonSizeHorizontal = 200;
            int buttonSizeVertical = 100;
            groupLayout = new GroupLayout(getContentPane());
            this.getContentPane().setLayout(groupLayout);
            groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(0,height/2-buttonSizeVertical/2)
                    .addComponent(infinityModeButton,0,buttonSizeVertical,buttonSizeVertical)
                    .addComponent(ScoreBoardButton,0,buttonSizeVertical,buttonSizeVertical)
                    .addComponent(playButton,0,buttonSizeVertical,buttonSizeVertical)
                    .addContainerGap(0,height/2-buttonSizeVertical/2))

            ;

            groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(0,width/2-buttonSizeHorizontal/2)
                    .addGroup(groupLayout.createParallelGroup()
                    .addComponent(infinityModeButton,0,buttonSizeHorizontal,buttonSizeHorizontal)
                    .addComponent(ScoreBoardButton,0,buttonSizeHorizontal,buttonSizeHorizontal)
                    .addComponent(playButton,0,buttonSizeHorizontal,buttonSizeHorizontal))
                    .addContainerGap(0,width/2-buttonSizeHorizontal/2))
            ;

            groupLayout.setAutoCreateGaps(true);
            Color buttonColor = new Color(36, 48, 72);
            Color textColor = Color.white;
            playButton.setBackground(buttonColor);
            playButton.setForeground(textColor);
            ScoreBoardButton.setBackground(buttonColor);
            ScoreBoardButton.setForeground(textColor);
            infinityModeButton.setBackground(buttonColor);
            infinityModeButton.setForeground(textColor);


            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new Play(gametitle,width,height,0).setVisible(true);
                    Main.super.dispose();
                }
            });
            infinityModeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new Play(gametitle,width,height,1).setVisible(true);
                    Main.super.dispose();
                }
            });
            ScoreBoardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new ScoreBoard(gametitle,width,height).setVisible(true);
                    Main.super.dispose();
                }
            });
        }

    public static void main(String[] args) {
        new Main().setVisible(true);

    }
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon i = new ImageIcon("kosmos.png");
        i.paintIcon(this, g, 0, 0);
    }
}