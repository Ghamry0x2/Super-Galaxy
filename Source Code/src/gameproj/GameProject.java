package gameproj;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class GameConst {
    public final static int widthPixels = 400;
    public final static int heightPixels = 225;
    public final static int pixelDim = 2;
    
    public static int rockDelay = 3*30;
    public static int newMissileDelay = 9*30;
    public static int newLifeDelay = 18*30;
    public static int newShieldDelay = 51*30;
    
    public static int healthCount = 60;
    public static int missilesCount = 10;
    public static boolean shieldOn = false;
    public static int score = 0;
    public static int level = 1;
    
    public static final int rockWidth = -71;
    public static final int missileWidth = -49;
    public static final int lifeWidth = -54;
    public static final int shieldWidth = -63;
    
    public static Font font;
    
    
    public ImageIcon gameIcon = new ImageIcon(getClass().getResource("/icons/rocket.png"));
    
    public ImageIcon background = new ImageIcon(getClass().getResource("/image/ha.png"));
    public ImageIcon fullBackground = new ImageIcon(getClass().getResource("/image/ha2.png"));
    public ImageIcon topBackground = new ImageIcon(getClass().getResource("/image/pnlNorth.png"));
    
    public ImageIcon ng = new ImageIcon(getClass().getResource("/image/new game.png"));
    public ImageIcon ngg = new ImageIcon(getClass().getResource("/image/new gameglow.png"));
    public ImageIcon htp = new ImageIcon(getClass().getResource("/image/Howtoplay.png"));
    public ImageIcon htpg = new ImageIcon(getClass().getResource("/image/Howtoplayglow.png"));
    public ImageIcon ex = new ImageIcon(getClass().getResource("/image/Exit.png"));
    public ImageIcon exg = new ImageIcon(getClass().getResource("/image/Exitglow.png"));
    public ImageIcon bk = new ImageIcon(getClass().getResource("/image/Back.png"));
    public ImageIcon bkg = new ImageIcon(getClass().getResource("/image/Backglow.png"));
    public ImageIcon hof = new ImageIcon(getClass().getResource("/image/halloffame.png"));
    public ImageIcon hofg = new ImageIcon(getClass().getResource("/image/halloffameglow.png"));
    
    public ImageIcon inst = new ImageIcon(getClass().getResource("/image/inst.png"));
    
    public ImageIcon met = new ImageIcon(getClass().getResource("/image/me.png"));
    public ImageIcon met1 = new ImageIcon(getClass().getResource("/image/me.png"));
    public ImageIcon met2 = new ImageIcon(getClass().getResource("/image/me.png"));
    
    public ImageIcon crd = new ImageIcon(getClass().getResource("/image/credits.png"));
    public ImageIcon crdg = new ImageIcon(getClass().getResource("/image/creditsglow.png"));
    public ImageIcon tri = new ImageIcon(getClass().getResource("/image/trio.png"));
    
    public static BufferedImage shield;
    public static BufferedImage rock;
    public static BufferedImage missile;
    public static BufferedImage life;
    public static BufferedImage collisionEffect;
    
    public static BufferedImage rMissile;
    
    public static BufferedImage rocket;
    public static BufferedImage rocketWithShield;
    
    public static BufferedImage lifeIcon;
    public static BufferedImage bar;
    public static BufferedImage upLife;
    public static BufferedImage newMissile;
    
    //Background variables
    public int xf = 800, xo = 0;
    public int xm = 750;
    public int ym = -100;
    public int m = 790;
    
    public GameConst(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("damit_0.ttf")));
            font = font.deriveFont(Font.PLAIN, 30);
        } 
        catch (IOException|FontFormatException e) {
            System.out.println("Could not load font");
        }
        
        try{
            shield = ImageIO.read(getClass().getResourceAsStream("/image/ld.png"));
            rock = ImageIO.read(getClass().getResourceAsStream("/image/spaceMeteors soghayar.png"));
            missile = ImageIO.read(getClass().getResourceAsStream("/image/newMissile.png"));
            life = ImageIO.read(getClass().getResourceAsStream("/image/life2.png"));
            collisionEffect = ImageIO.read(getClass().getResourceAsStream("/image/effect.png"));
            
            rMissile = ImageIO.read(getClass().getResourceAsStream("/image/myMisslepng.png"));
            
            rocket = ImageIO.read(getClass().getResourceAsStream("/image/spmodified.png"));
            rocketWithShield = ImageIO.read(getClass().getResourceAsStream("/image/spmodifiedwithShield3.png"));
            
            lifeIcon = ImageIO.read(getClass().getResourceAsStream("/image/life2.png"));
            bar = ImageIO.read(getClass().getResourceAsStream("/image/HealthBarFrame.png"));
            upLife = ImageIO.read(getClass().getResourceAsStream("/image/Health.png"));
            newMissile = ImageIO.read(getClass().getResourceAsStream("/image/newMissile2.png"));
        }
        catch(IOException e){
            System.out.println("Could not load Objects images");
        }
    }
}

class introMusic extends Thread {
    String uri;

    public introMusic(String s) {
        try {
            JFXPanel j = new JFXPanel();
            uri = new File(s).toURI().toString();
            new MediaPlayer( new Media(uri)).play();
        } 
        catch(Exception es){
            System.out.println("Can't load " +s);
        }
    }
    
    public static void play(String s){
        introMusic im = new introMusic(s);
        im.start();
    }
      
    @Override
    public void run(){
        new MediaPlayer( new Media(uri)).play();
    }
}

public class GameProject {
    static MainFrame mf;
    static GameConst gv;
    
    public static void main(String[] args) {
        gv = new GameConst();
        
        introMusic.play("sounds/back.mp3");
        
        mf = new MainFrame(gv);
        mf.setVisible(true);
    }
    
    public static void newFrame(){
        mf.setVisible(false);
        mf = null;
        gv = new GameConst();
        
        mf = new MainFrame(gv);
        mf.setVisible(true);
    }
}