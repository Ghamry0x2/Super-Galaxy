package gameproj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TopPanel extends JPanel{
    private GameConst gv;
    
    private Font font = GameConst.font;
    
    private JLabel first;
    private JLabel overLap;
    
    private int background;
    private int backgroundOL = 800;
    
    private BufferedImage lifeIcon;
    private BufferedImage bar;
    private BufferedImage life;
    private BufferedImage newMissile;
    
    private static Timer t;

    public TopPanel(GameConst g) {
        gv = g;
        init();
    }
        
    private void init(){
        this.setLayout(null);
        
        lifeIcon = GameConst.lifeIcon;
        bar = GameConst.bar;
        life = GameConst.upLife;
        newMissile = GameConst.newMissile;
        
        first = new JLabel(gv.topBackground);
        first.setBounds(background,0,800,50);
        add(first);
        
        overLap = new JLabel(gv.topBackground);
        overLap.setBounds(backgroundOL,0,800,50);
        add(overLap);
        
        t = new Timer(33,new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                background -= 1;
                backgroundOL -= 1;
                if(background == -800)
                    background = 800;
                if(backgroundOL == -800)
                    backgroundOL = 800;
                first.setLocation(background, 0);
                overLap.setLocation(backgroundOL, 0);
                
                repaint();
                }
        });
        t.start();
    }
    
    public static void stopTimer(){
        t.stop();
    }
     
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawImage(lifeIcon, 18, 8, null);
        
        g.drawImage(bar, 52 , 2, null);
        for (int i = 0; i < GameConst.healthCount; i++) {
            g.drawImage(life, 50+ i*5 , 0, null);
        }
        
        g.drawImage(newMissile, 380, 10, null);
        
        g.setColor(Color.white);
        g.setFont(font);
        
        g.drawString("x " +GameConst.missilesCount, 425, 38);
        g.drawString("Level " +GameConst.level, 530, 38);
        g.drawString("Score "+GameConst.score, 650, 38);
    }
}