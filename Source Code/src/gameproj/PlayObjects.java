package gameproj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayObjects extends JPanel{
    public static ArrayList<PlayObjects> ps = new ArrayList();
    static int rate = 0;
    
    protected int x,y,w,h;
    protected Random rand = new Random();
    
    protected BufferedImage b;
    
    public PlayObjects(){
        x = GameConst.widthPixels*GameConst.pixelDim;
        y = rand.nextInt(350);
    }
    
    public void move(){
        if( x >= GameConst.rockWidth )
            x -= (5+rate);
        repaint();
    }
    
    public boolean objectsCollision(){
        for(PlayObjects p: ps){
            if((p.x>720 && p.y < this.y && this.y < (p.y+p.h)))
                return true;
        }
    
        return false;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(b, x, y, this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}

class Shield extends PlayObjects {
    
    public Shield(){
        init();
    }
    
    private void init(){
        b = GameConst.shield;
        
        w = b.getWidth();
        h = b.getHeight();
        while(objectsCollision())
            y = rand.nextInt(400-h);
    }
    
}

class Rock extends PlayObjects {
    
    public Rock() {
        init();
    }
    
    private void init(){
        b = GameConst.rock;
        
        w = b.getWidth();
        h = b.getHeight();
        System.out.println(y);
        while(objectsCollision())
            y = rand.nextInt(400-h);
    }
}

class NewMissile extends PlayObjects {

    public NewMissile() {
        init();
    }
    
    private void init(){
        b = GameConst.missile;
        
        w = b.getWidth();
        h = b.getHeight();
        while(objectsCollision())
            y = rand.nextInt(450-h);
    }
}

class Life extends PlayObjects {
    
    public Life() {
        init();
    }
    
    private void init(){
        b = GameConst.life;
        
        w = b.getWidth();
        h = b.getHeight();
        while(objectsCollision())
            y = rand.nextInt(450-h);
    }
}

class CollisionEffect extends JPanel{
    BufferedImage b;    
    
    private int x,y,w,h;
    
    public CollisionEffect(){
        super();
        init();
    }
    
    private void init(){
        b = GameConst.collisionEffect;
        
        w = b.getWidth();
        h = b.getHeight();
    }
    
    public void setCollisionLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(b, x, y, this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}