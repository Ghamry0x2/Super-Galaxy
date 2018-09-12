package gameproj;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

enum Dir {UP, DOWN};

public class PlayPanel extends JPanel {
    private GameConst gv;
    private MainFrame parent;
    
    private JLabel bckgrd;
    private JLabel bckgrd2;
    
    private int background;
    private int backgroundOL = 800;
    
    private Timer t;
    private Timer t2;
    
    private int tCount;
    
    private Rocket r;
    
    CollisionEffect collisionEffect = new CollisionEffect();
    CollisionEffect missileCollisionEffect = new CollisionEffect();
    
    boolean collisionDone = false;
    boolean missileCollisionDone = false;
    
    public static ArrayList<Missile> ms = Missile.ms;
    public static ArrayList<PlayObjects> ps = PlayObjects.ps;
    
    public PlayPanel(MainFrame pp, GameConst g) {
        gv = g;
        bckgrd = new JLabel(gv.background);
        bckgrd2 = new JLabel(gv.background);
        
        tCount = 1;
        parent = pp;
        init();
    }
    
    private void init(){
        setLayout(null);
        
        bckgrd.setBounds(0,0,800,450);
        bckgrd2.setBounds(0,0,800,450);
        
        add(bckgrd);
        add(bckgrd2);
        
        r = new Rocket();
        
        t = new Timer(15,new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                r.move();
                
                background -= 1;
                backgroundOL -= 1;
                if(background == -800)
                    background = 800;
                if(backgroundOL == -800)
                    backgroundOL = 800;
                
                bckgrd.setLocation(background, 0);
                bckgrd2.setLocation(backgroundOL, 0);
                
                for(Missile m: ms){
                    m.move();
                    if(m.hasCollision(ps)){
                        missileCollisionEffect.setCollisionLocation(m.getX()+m.getWidth(), m.getY()-14);
                        missileCollisionDone = true;
                        introMusic.play("sounds/shootwav.wav");
                        
                        GameConst.score += 10;
                        if((GameConst.score+1)%21 == 0) {
                            GameConst.level+=1;
                            PlayObjects.rate++;
                            GameConst.rockDelay = (int) (((GameConst.rockDelay - (GameConst.level-1)*0.2*30) <= 0)? 0.5*30 : GameConst.rockDelay - (GameConst.level-1)*0.2*30);
                            GameConst.newMissileDelay += (GameConst.level-1)*0.1*30;
                            GameConst.newLifeDelay += (GameConst.level-1)*0.1*30;
                            GameConst.newShieldDelay += (GameConst.level-1)*0.1*30;
                        }
                        break;
                    }
                }
                
                for(Missile m: ms){
                    if(m.getX() > 800) {
                        ms.remove(m);
                        break;
                    }
                }
                repaint();
           }
        });
        t.start();
        
        t2 = new Timer(15,new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                tCount++;
                
                if(tCount % 60 == 0)
                    GameConst.score += GameConst.level;
                if(tCount%GameConst.newLifeDelay == 0)
                    ps.add(new Life());
                if(tCount%GameConst.newMissileDelay == 0)
                    ps.add(new NewMissile());
                if(tCount%GameConst.newShieldDelay == 0)
                    ps.add(new Shield());
                if(tCount%GameConst.rockDelay == 0)
                    ps.add(new Rock());
                
                for(PlayObjects p: ps){
                    p.move();
                    if(r.hasCollision(p.x,p.y,p.w,p.h)){
                        if(p instanceof Rock) {
                            introMusic.play("sounds/shootwav.wav");
                            if(GameConst.shieldOn)
                                GameConst.shieldOn = false;
                            else {
                                collisionEffect.setCollisionLocation(p.x-20, p.y);
                                collisionDone = true;
                                
                                if( GameConst.healthCount-10 <= 0) {
                                    TopPanel.stopTimer();
                                    t.stop();
                                    t2.stop();
                                    
                                    parent.endGame();
                                }
                                else
                                    GameConst.healthCount -= 10;
                            }
                        }
                        if(p instanceof NewMissile) {
                            introMusic.play("sounds/goodwav.wav");
                            GameConst.missilesCount += 5;
                        }
                        
                        if(p instanceof Shield) {
                            introMusic.play("sounds/goodwav.wav");
                            GameConst.shieldOn = true;
                        }
                            
                        
                        if(p instanceof Life) {
                            introMusic.play("sounds/goodwav.wav");
                            GameConst.healthCount = (GameConst.healthCount+30 < 60)? GameConst.healthCount+30: 60;
                        }
                        PlayObjects.ps.remove(p);
                        break;
                    }
                }
                for(PlayObjects p: PlayObjects.ps){
                    if(p.x < -50) {
                        PlayObjects.ps.remove(p);
                        break;
                    }
                }
                repaint();
           }
        });
        t2.start();
    }
    
    public void setRockDir(Dir d, boolean b){
        r.setDir(d, b);
    }
    
    public int getRockY(){
        return r.getY();
    }
    
    public void stopTimers(){
        t.stop();
        t2.stop();
        TopPanel.stopTimer();
    }
    
    public void setRocketP(boolean b){
        r.setPressed(b);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(Missile m:ms)
            m.paint(g);
        
        for(PlayObjects p: PlayObjects.ps)
            p.paint(g);
        
        r.paint(g);
        
        if(collisionDone){
                collisionEffect.paint(g);
                
            collisionDone = false;
        }
        if(missileCollisionDone){
                missileCollisionEffect.paint(g);
            
            missileCollisionDone = false;
        }
    }
}