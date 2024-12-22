import java.awt.*;

public class Player {
    private int x, y, width, height;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void resetPosition(){
        this.x = 200;
        this.y = 450;
        this.width = 100;
        this.height = 10;
    }

    public void changeWidth(){
        this.width = 50;
    }

    public void changeWidthtoOriginal(){
        this.width = 100;
    }


    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft(){
        if(x > 0)
            x = x - 20;
    }

    public void moveRight(){
        if(x < 400){
            x = x + 20;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
