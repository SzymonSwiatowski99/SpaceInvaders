package GUI;

public class Thing {
    private int x;
    private int y;
    private int speed;
    private boolean isAlive;
    private int view;
    private int power;
    private int isMove;
    private int afterDie;
    public Thing(){ }

    public Thing(int x, int y, int speed){
    this.speed = speed;
    this.x = x;
    this.y = y;
    this.isAlive = true;
    this.isMove = 0;
    }
    public Thing(int x, int y, int speed, int view){
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.view = view;
        this.afterDie = 1;
    }
    public Thing(int x, int y, int speed, int view, int power){
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.view = view;
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getY() { return y; }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isAlive() { return isAlive; }

    public void setAlive(boolean alive) { isAlive = alive; }

    public int getView() { return view; }

    public void setView(int view) { this.view = view; }

    public int getIsMove() {
        return isMove;
    }

    public void setIsMove(int isMove) {
        this.isMove = isMove;
    }

    public int getAfterDie() {
        return afterDie;
    }

    public void setAfterDie(int afterDie) {
        this.afterDie = afterDie;
    }
}
