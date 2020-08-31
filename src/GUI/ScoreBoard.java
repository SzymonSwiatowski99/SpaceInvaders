package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoreBoard extends JFrame {
    private int bestTime;
    private int bestWave;
    private int bestTimeInfinity;
    private JButton bestStandardMode;
    private JButton bestTimeInf;
    private JButton bestWaveInf;
    private JButton backToMenu;
    private int width;
    private int height;
    private GroupLayout groupLayout;

    public ScoreBoard(String gametitle,int width,int height){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("standardMode"));
            String load = reader.readLine();
            if(load != null) { this.bestTime = Integer.parseInt(load);
            }else bestTime = 0;
            reader.close();
            reader = new BufferedReader(new FileReader("infinityMode"));
            load = reader.readLine();
            if(load != null) { this.bestWave = Integer.parseInt(load);
                load = reader.readLine();
                this.bestTimeInfinity = Integer.parseInt(load);
            }else {
                bestWave = 0;
                bestTimeInfinity = 0;
            }
            reader.close();
        }catch (IOException ex){ System.out.println(ex.getMessage());}
        this.width = width;
        this.repaint();
        this.height = height;
        this.setTitle(gametitle);
        this.setBounds(0,0,width,height);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setVisible(false);
        this.setUndecorated(true);
        initComponents();
    }
    public void initComponents() {
        bestStandardMode = new JButton("Standard Mode Best time: " + (int)bestTime/60 +"m " + bestTime%60 +"s");
        bestTimeInf =  new JButton("Infinity Mode Best Wave: " + bestWave);
        bestWaveInf = new JButton("Infinity Mode Best time: " + (int)bestTimeInfinity/60 +"m " + bestTimeInfinity%60 +"s");
        backToMenu = new JButton("back");
        Color buttonColor = new Color(36, 48, 72);
        bestTimeInf.setBackground(buttonColor);
        bestStandardMode.setBackground(buttonColor);
        bestWaveInf.setBackground(buttonColor);
        bestTimeInf.setForeground(Color.white);
        bestStandardMode.setForeground(Color.white);
        bestWaveInf.setForeground(Color.white);
        backToMenu.setBackground(new Color(32, 32, 32));
        backToMenu.setForeground(Color.white);
        Container container = this.getContentPane();
        container.add(backToMenu);
        int sizeXB = 80;
        int sizeY = 30;
        int sizeYI = 80;
        int sizeX = 400;
        groupLayout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(groupLayout);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(width/2-200,width/2-200)
                .addGroup(groupLayout.createParallelGroup()
                                .addComponent(bestStandardMode,0,sizeX,sizeX)
                                .addComponent(bestWaveInf,0,sizeX,sizeX)
                                .addComponent(bestTimeInf,0,sizeX,sizeX)
                )
                .addContainerGap(0,Short.MAX_VALUE)
                .addComponent(backToMenu,0,sizeXB,sizeXB));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addContainerGap(height/2-140,height/2-140)
                  .addComponent(bestStandardMode,0,sizeYI,sizeYI)
                .addComponent(bestWaveInf,0,sizeYI,sizeYI)
                .addComponent(bestTimeInf,0,sizeYI,sizeYI)
                .addContainerGap(0,Short.MAX_VALUE)
                .addComponent(backToMenu,0,sizeY,sizeY))
        ;

        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Main().setVisible(true);
                ScoreBoard.super.dispose(); }
        });
    }
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon i = new ImageIcon("kosmos.png");
        i.paintIcon(this, g, 0, 0);
        bestStandardMode.repaint();
        bestTimeInf.repaint();
        bestWaveInf.repaint();
        backToMenu.repaint();

    }
}
