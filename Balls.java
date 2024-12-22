import java.awt.*;
import java.util.Random;

public class Balls {
    int x, y, size, speed;

    public Balls(int x, int y, int size, int speed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public int move(){
        y +=speed;
        if(y > 500){
            y = 0;
            x = new Random().nextInt(400);
            return 1;
        }
        return 0;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, size, size);
    }
}
