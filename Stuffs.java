import java.awt.*;
import java.util.Random;

public class Stuffs {
    int x, y, size, speed;

    public Stuffs(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, size, size);
    }

    public void move(){
        y += speed;
        if( y > 500){
            y = 0;
            x = new Random().nextInt(500);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, size, size);
    }
}
