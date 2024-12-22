import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private ArrayList<Balls> balls;
    private ArrayList<Stuffs> stuffs;
    private boolean gameOver = false;
    private int score = 0;
    private int bestscore = 0;
    private int level = 1;
    private int bestlevel = 1;
    private int levelUpScore = 10;
    private Timer widthResetTimer;

    public Game() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        this.player = new Player(200, 450, 100, 10);
        this.balls = new ArrayList<>();
        this.stuffs = new ArrayList<>();
        spawnBalls();
        spawnStuffs();
        timer = new Timer(30, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    private void spawnStuffs(){
        Random rand = new Random();
        int newX = rand.nextInt(500);
        int newSize = rand.nextInt(25) + 10;
        int newSpeed = rand.nextInt(10) + 5 + (level+1) * 2;

        stuffs.add(new Stuffs(newX, 0, newSize, newSpeed));
    }

    private void spawnBalls(){
        Random rand = new Random();
        int nrBile = 5;
        for(int i=0; i < nrBile; i++){
            int newX = rand.nextInt(500);
            int newSize = rand.nextInt(25) + 10;
            int newSpeed = rand.nextInt(5) + 5 + (level-1) * 2;
            balls.add(new Balls(newX, 0, newSize, newSpeed));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       if(gameOver == true) {
           if(score > bestscore) {
               bestscore = score;
               bestlevel = level;
           }
           // Score
           g.setColor(Color.WHITE);
           g.setFont(new Font("Arial", Font.ITALIC, 25));
           g.drawString("Your best score:" + bestscore, 100, 50);
           g.drawString("Your actual score: " + score, 100, 100);

           // Game Over
           g.setColor(Color.RED);
           g.setFont(new Font("Calibri", Font.BOLD, 50));
           g.drawString("Game Over", 100, 200);

           // SPACE to restart
           g.setColor(Color.WHITE);
           g.setFont(new Font("Arial", Font.ITALIC, 20));
           g.drawString("Press SPACE to restart!", 100, 300);
           timer.stop();
       } else {
           g.setColor(Color.WHITE);
           g.setFont(new Font("Tahoma", Font.BOLD, 10));
           g.drawString("Level " + level+ " - SCORE: " + score, 15, 20);
           player.draw(g);
           for(Balls ball : balls) {
               ball.draw(g);
           }
           for(Stuffs stuff : stuffs) {
               stuff.draw(g);
           }
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameOver == false){
            for(Balls ball : balls){
                score = score + ball.move();
            }
            for(Stuffs stuff : stuffs){
                stuff.move();
            }
        }

        if(score >= levelUpScore * level){
            level++;
        }

        checkCollision();
        repaint();
    }

    public void checkCollision(){
        for(Balls ball : balls){
            if(ball.getBounds().intersects(player.getBounds())){
                gameOver = true;
                break;
            }
        }
        for(Stuffs stuff : stuffs){
            if(stuff.getBounds().intersects(player.getBounds())){
                player.changeWidth();
                startWidthResetTimer();
            }
        }
    }

    public void startWidthResetTimer(){
        if(widthResetTimer != null && widthResetTimer.isRunning() == true){
            widthResetTimer.stop();
        }

        widthResetTimer = new Timer(5000, new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                player.changeWidthtoOriginal();
                widthResetTimer.stop();
            }
        });
        widthResetTimer.start();
    }

    private void resetGame(){
        gameOver = false;
        balls.clear();
        score = 0;
        level = 0;
        player.resetPosition();
        spawnBalls();
        timer.start();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            player.moveLeft();
        } else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            player.moveRight();
        } else if(gameOver == true || key == KeyEvent.VK_SPACE){
            resetGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
