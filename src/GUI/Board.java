package GUI;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Board extends JPanel implements Runnable {
    private Thread animator;
    private BufferedImage image;
    public Thing player;
    private int width;
    private int height;
    private Thing[] enemy;
    private int mode;
    private BufferedImage imageEnemy1;
    private BufferedImage imageEnemy2;
    private BufferedImage imageEnemy3;
    private BufferedImage imageEnemy4;
    private BufferedImage imageEnemy5;
    private BufferedImage imageEnemy6;
    private BufferedImage imageEnemy7;
    private BufferedImage imageBullet1;
    private BufferedImage imageBullet2;
    private BufferedImage imageBullet3;
    private int time;
    private int sec;
    private int numberWave;
    private int nextWave;
    private ArrayList<Thing> shootList;
    private boolean shootOn;
    private boolean pause;
    private int bestTime;
    private int bestWave;
    private int timeToWave;
    private static File brawa = new File("bum2.WAV");;



    public Board(int width, int height, int mode) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("standardMode"));
            String load = reader.readLine();
            if(load != null) { this.bestTime = Integer.parseInt(load);
            }else bestTime = 0;
            reader.close();
            reader = new BufferedReader(new FileReader("infinityMode"));
            load = reader.readLine();
            if(load != null) { this.bestWave = Integer.parseInt(load);
            }else bestWave = 0;
            reader.close();
        }catch (IOException ex){ System.out.println(ex.getMessage());}

        this.pause = false;
        this.sec=0;
        this.width = width;
        this.timeToWave = 0;
        this.shootOn = true;
        this.nextWave = 0;
        this.shootList = new ArrayList<Thing>();
        this.numberWave=1;
        this.height = height;
        this.mode = mode;
        this.time = 0;
        this.enemy = new Thing[5];
        this.setBackground(new Color(36, 48, 72));
        try {
            image = ImageIO.read(new File("icon_2.png"));
            imageEnemy1 = ImageIO.read(new File("enemy1.png"));
            imageEnemy2 = ImageIO.read(new File("enemy2.png"));
            imageEnemy3 = ImageIO.read(new File("enemy3.png"));
            imageEnemy4 = ImageIO.read(new File("enemy4.png"));
            imageEnemy5 = ImageIO.read(new File("enemy5.png"));
            imageEnemy6 = ImageIO.read(new File("enemy6.png"));
            imageEnemy7 = ImageIO.read(new File("enemy7.png"));
            imageBullet1 = ImageIO.read(new File("bullet1_small.png"));
            imageBullet2 = ImageIO.read(new File("bullet2.png"));
            imageBullet3 = ImageIO.read(new File("bullet3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }



        this.setVisible(true);
        this.setBounds(50, 50, width - 100, height - 150);
        player = new Thing(width / 2, height - 180, width / 195);
        animator = new Thread(this);
        animator.start();
        wave();
        ActionListener waveTimerListener = new gameTimer();
        Timer waveTimer = new Timer(10,waveTimerListener);
        waveTimer.start();

    }
    @Override
    public void run() {

        while (true) {
                repaint();
                try {
                    Thread.sleep(14);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (player.isAlive()) g.drawImage(image, player.getX() - 22, player.getY() - 25, this);
        if (player.isAlive())  for(Thing shot: shootList){
        if (shootList !=null&&shot.isAlive())g.drawImage(bulletImage(shot.getView()), shot.getX()-4,shot.getY()-10, this);
    }
            for (int i = 0; i < enemy.length; i++) {
        if (enemy[i]!=null&&enemy[i].isAlive())
            g.drawImage(randomImage(enemy[i].getView()), enemy[i].getX() - 22, enemy[i].getY() - 25, this);
        else if(enemy[i]!=null && !enemy[i].isAlive() &&enemy[i].getAfterDie()<8){
            g.drawImage(randomImage(enemy[i].getView()+3), enemy[i].getX() - 22, enemy[i].getY() - 25, this);
            enemy[i].setAfterDie(enemy[i].getAfterDie()+1);
        }else if(enemy[i]!=null && !enemy[i].isAlive() && enemy[i].getAfterDie()<15 && enemy[i].getAfterDie()>7){
            g.drawImage(randomImage(7), enemy[i].getX() - 22, enemy[i].getY() - 25, this);
            enemy[i].setAfterDie(enemy[i].getAfterDie()+1);
        }
    }
}

    public void moveRight() {
        if (player.getX() < width - 130) player.setX(player.getX() + player.getSpeed());
    }
    public void moveLeft() {
        if (player.getX() > 40) player.setX(player.getX() - player.getSpeed());
    }
    public void setIsMove(int i) { player.setIsMove(i); }
    public void wave() {

        if(player.isAlive()&&numberWave<10&&mode==0) {
                int position = (int) (Math.random() * width - 170);
                if (position < 40) position = 40;
                for (int i = 0; i < enemy.length; i++) {
                    enemy[i] = new Thing(position, (-(i * 90)), height / 250 + (height / 250) * (numberWave/ 10), (int) (Math.random() * 3 + 1));
                }
            numberWave++;
        }else if(player.isAlive()&&mode==0) {
            player.setAlive(false);
            if(time<bestTime||bestTime==0){
                try {
                    PrintWriter writer = new PrintWriter(new FileWriter("standardMode"));
                    writer.print(time);
                    writer.close();
                }catch (IOException ex){ System.out.println(ex.getMessage());}

            }

            new Statement(1,width,height).setVisible(true);
        }
        if(player.isAlive()&&mode == 1) {
            int position = (int) (Math.random() * width - 170);
            if (position < 40) position = 40;
            for (int i = 0; i < enemy.length; i++) {
                enemy[i] = new Thing(position, (-(i * 90)), height / 250 + (height / 600) * (numberWave/ 10), (int) (Math.random() * 3 + 1));
            }
            numberWave ++;
        }
    }
    public BufferedImage randomImage(int im){
        if(im==1){
            return imageEnemy1;
        }else if(im==2){
            return imageEnemy2;
        }else if(im==3) {
            return imageEnemy3;
        }else if(im==4){
            return imageEnemy4;
        }else if(im==5){
            return imageEnemy5;
        }else if(im==6) {
            return imageEnemy6;
        }else{
            return imageEnemy7;
        }
    }
    public BufferedImage bulletImage(int im){
        if(im==1){
            return imageBullet1;
        }else if(im==2){
            return imageBullet2;
        }else
            return imageBullet3;
    }
    public void moveEnemy(){
        int k = 0;
        for(int i = 0; i<enemy.length ; i++) {
            if(k==0)
            if(shootList !=null)
           try{ for (Thing shootTable: shootList) {
              if( enemy[i].isAlive()&& shootTable.isAlive())
                  if (enemy[i].getX()-29<=shootTable.getX()&&shootTable.getX()<=enemy[i].getX()+29)
                      if(shootTable.getY() <= enemy[i].getY()&&enemy[i].getY()>=shootTable.getY()){
                  enemy[i].setAlive(false);
                  playSound(brawa);
                  enemy[i].setAfterDie(2);
                  shootList.remove(shootTable);
                  k++;
              }
            }}catch (Exception e){e.getMessage();}

            if(enemy[i]!=null&&enemy[i].isAlive()) {
                enemy[i].setY(enemy[i].getY() + enemy[i].getSpeed());
                if(enemy[i].getY()>=player.getY()) {
                    player.setAlive(false);
                    System.out.println("time: "+ time+" s");
                    new Statement(0,width,height).setVisible(true);
                }
            }
        }
    }

    public boolean isPause() { return pause; }
    public void setPause(boolean pause) { this.pause = pause; }

    private class gameTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(player.isAlive()&& pause == false) {
                moveEnemy();
                moveShot();
                if(player.getIsMove()==1)moveLeft();
                if(player.getIsMove()==2)moveRight();
                sec++;
                if(sec%50==0)time++;
                if(sec%20==0&& shootOn){
                    atack();
                }
                if(!enemy[enemy.length-1].isAlive()&&nextWave==0) {
                    nextWave = 1;
                    timeToWave = 60;
                }
                if(!(timeToWave==0))timeToWave--;
                if(!enemy[enemy.length-1].isAlive())
                if(nextWave==1 && timeToWave==0){
                    wave();
                    nextWave=0;
                }

            }else if(!player.isAlive()&&mode ==1){
                if(numberWave>bestWave){
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter("infinityMode"));
                        writer.print(numberWave-1);
                        writer.println();
                        writer.print(time);
                        writer.close();
                    }catch (IOException ex){ System.out.println(ex.getMessage());}
                }
            }
        }
    }
    public void atack(){
        shootList.add(new Thing(player.getX(),player.getY()-50,height/160, 1));
    }
    public void moveShot(){
    try{ for(Thing shot : shootList) {
        if (shot.isAlive())
            shot.setY(shot.getY() - shot.getSpeed());
        if (shot.getY() <= 0) shootList.remove(shot);
            }}catch (Exception e){
        e.getMessage();}
    }
    public static void playSound(File sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        }catch (Exception e){
        }
    }

}
