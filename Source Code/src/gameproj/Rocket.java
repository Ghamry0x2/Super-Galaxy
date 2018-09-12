package gameproj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;


public class Rocket extends JPanel {
    private BufferedImage rocket;
    private BufferedImage rocketWithShield;
    
    private int y;
    private boolean pressed;
    private Dir d = Dir.UP;
    
    public Rocket() {
        init();
    }
    
    private void init(){
        rocket = GameConst.rocket;
        rocketWithShield = GameConst.rocketWithShield;
    }
    
    public void move(){
        if(pressed) {
            switch(d){
                case UP:
                    if(y-10 >= 0) {
                        y-=10;
                        repaint();
                    }
                    break;
                case DOWN:
                    if( GameConst.shieldOn == false && y+24+10 <= 450 - 120) {
                        y+=10;
                        repaint();
                    }
                    else if( GameConst.shieldOn == true && y+24+10 <= 450 - 120) {
                        y+=10;
                        repaint();
                    }

            }
        }
    }
    
    public void setDir(Dir d, boolean p){
        this.d = d;
        pressed = p;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    
    public boolean hasCollision(int rx, int ry, int rw, int rh){
      
        if ( GameConst.shieldOn ){
            if( rx <= 146 && ry >= y && ry <= y+24){
                System.out.println("Collision 1");
                return true;
            }
            
            if( rx <= 124 && ry >= y-50 && ry <= y+132){
                System.out.println("Collision 2");
                return true;
            }

            if( rx >= 30 && rx <= 54 && ry >= y-50 && ry <= y-174){
                System.out.println("Collision 3");
                return true;
            }
        }
        else{
            if( rx <= 124 && ry >= y-24 && ry <= y+114){
                System.out.println("Collision 1");
                return true;
            }

            if( rx <= 40 && ry >= y-50 && ry <= y-174){
                System.out.println("Collision 2");
                return true;
            }
        }
        return false;
    }

    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if( GameConst.shieldOn ){
            g.drawImage(rocketWithShield, 0, y, this);
        }
        else{
            g.drawImage(rocket, 0+22, y+14, this);
        }
    }
}


class Missile extends JPanel {
    private BufferedImage m;
    
    private int y,x = 90;

    public static ArrayList<Missile> ms = new ArrayList();
    
    public Missile(int y){
        this.y = y+14;
        init();
    }
    
    private void init(){
        m = GameConst.rMissile;
    }
    
    public boolean hasCollision(ArrayList<PlayObjects> ps){
        for(PlayObjects p : ps){
            if(p instanceof Rock && this.x < p.x && p.x <= (this.x + m.getWidth()) && this.y > p.y && this.y < (p.y + p.h)){
                ms.remove(this);
                ps.remove(p);
                return true;
            }
        }
        return false;
    }
    
    public void move(){
        x+=6;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(m, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
}