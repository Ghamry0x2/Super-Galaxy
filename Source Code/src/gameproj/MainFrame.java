package gameproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class  MainFrame extends JFrame {
    private GameConst gv;
    
    private HomePanel home;
    private HallofFame hallofFame;
    private PlayPanel gamePanel;
    private TopPanel pnlTop;
    private HighScore highScore;
    private Container c;
    
    public MainFrame(GameConst g) {
        gv = g;
        init();
    }
    
    private void init(){
        this.setTitle("Super Galaxy");
        this.setIconImage(gv.gameIcon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        c = getContentPane();
        home = new HomePanel(this, gv);
        home.setPreferredSize(new Dimension(800,500));
        
        hallofFame = new HallofFame(home, gv);
        hallofFame.setVisible(false);
        c.add(hallofFame);
        
        c.add(home);
        pack();
    }
    
    public void newGame(){
        pnlTop = new TopPanel(gv);
        gamePanel = new PlayPanel(this,gv);
        home.setVisible(false);
        
        pnlTop.setPreferredSize(new Dimension(800,50));
        
        c.add(gamePanel);
        c.add(pnlTop, BorderLayout.NORTH);
        
        System.out.println(gamePanel.getHeight());
        setFocusable(true);
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(gamePanel != null){
                    
                    if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        if( GameConst.missilesCount != 0){
                            gamePanel.ms.add(new Missile(gamePanel.getRockY()+25));
                            gamePanel.ms.add(new Missile(gamePanel.getRockY()+85));
                            introMusic.play("sounds/Punchwav.wav");
                            GameConst.missilesCount--;
                        }
                    }
                    if(e.getKeyCode() == KeyEvent.VK_UP)
                        gamePanel.setRockDir(Dir.UP, true);
                    else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                        gamePanel.setRockDir(Dir.DOWN, true);
                    
                    else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                        endGame();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                if((gamePanel != null) && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN))
                    gamePanel.setRocketP(false);
            }
        });
    }
    
    public void endGame(){
        gamePanel.setVisible(false);
        pnlTop.setVisible(false);
        
        for(int i=0; i<PlayObjects.ps.size(); i++)
            PlayObjects.ps.remove(i);
        for(int i=0; i<Missile.ms.size(); i++)
            Missile.ms.remove(i);
        
        for(Missile m: Missile.ms){
            m.setX(m.getX()+800);
        }
        
        for(PlayObjects p: PlayObjects.ps){
            p.setX(p.getX()+800);
        }
        
        gamePanel.collisionEffect.setX(gamePanel.collisionEffect.getX()+800);
        gamePanel.missileCollisionEffect.setX(gamePanel.missileCollisionEffect.getX()+800);
        
        highScore = new HighScore(home, gv);
        highScore.setVisible(true);
        add(highScore);
        
        gamePanel.stopTimers();
        
        remove(gamePanel);
        remove(pnlTop);
        
        gamePanel = null;
        pnlTop = null;   
        
        GameConst.healthCount = 60;
        GameConst.missilesCount = 10;
        GameConst.shieldOn = false;
        GameConst.score = 0;
        GameConst.level = 1;
    
        GameConst.rockDelay = 3*30;
        GameConst.newMissileDelay = 9*30;
        GameConst.newLifeDelay = 18*30;
        GameConst.newShieldDelay = 51*30;
        
        PlayObjects.rate = 0;
    }

    public void openHallofFame() {
        home.setVisible(false);
        c.add(hallofFame);
        hallofFame.refresh();
        hallofFame.setVisible(true);
    }
}