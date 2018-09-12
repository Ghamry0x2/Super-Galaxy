package gameproj;

import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;

public class HomePanel extends JPanel{
    private GameConst gv;
    
    private javax.swing.Timer tm;
    private int gameCount;
    
    private JLabel first, overLap;
    
    private JLabel newGame, meteor, meteor1, meteor2, newGameLow, howToPlay, howToPayLow, exit, exitLow,
                   back, backLow, hallOfFame, hallOfFameglow, instructions, credits, creditsglow, trio;

    private MainFrame p;
    
    public HomePanel(MainFrame parent, GameConst g){
        gv = g;
        p = parent;
        init();
    }
    
    public void init(){
        setLayout(null);
        //*********************************
        newGame = new JLabel(gv.ng);
        newGameLow = new JLabel(gv.ngg);
        howToPlay = new JLabel(gv.htp);
        howToPayLow = new JLabel(gv.htpg);
        exit = new JLabel(gv.ex);
        exitLow = new JLabel(gv.exg);
        back = new JLabel(gv.bk);
        backLow = new JLabel(gv.bkg);
        hallOfFame = new JLabel(gv.hof);
        hallOfFameglow = new JLabel(gv.hofg);
        instructions = new JLabel(gv.inst);
        credits = new JLabel(gv.crd);
        creditsglow = new JLabel(gv.crdg);
        trio = new JLabel(gv.tri);
        meteor = new JLabel(gv.met);
        meteor1 = new JLabel(gv.met1);
        meteor2 = new JLabel(gv.met2);
        
        first = new JLabel(gv.fullBackground);
        overLap = new JLabel(gv.fullBackground);
        
        first.setBounds(gv.xo, 0, 800,500);
        overLap.setBounds(gv.xf, 0, 800,500);
        
        newGame.setBounds(300, 30, 180,90);
        newGameLow.setBounds(300, 30, 180,90);
        
        howToPlay.setBounds(300, 130, 180,90);
        howToPayLow.setBounds(300, 130, 180,90);
        
        exit.setBounds(300, 330, 180,90);
        exitLow.setBounds(300, 330, 180,90);
    
        hallOfFame.setBounds(300, 230, 180,90);
        hallOfFameglow.setBounds(300, 230, 180,90);
        
        back.setBounds(300, 320, 180,90);
        backLow.setBounds(300, 320, 180,90);
      
        instructions.setBounds(0, -20, 800,400);
        
        credits.setBounds(130, 400, 180,90);
        creditsglow.setBounds(130, 400, 180,90);
        trio.setBounds(0, -20, 800,400);
        
        meteor.setBounds(800, 00, 180,90);
        meteor1.setBounds(800, 00, 180,90);
        meteor2.setBounds(800, 00, 180,90);
        
        //*********************************
        add(newGame);
        add(newGameLow);
        add(howToPlay);
        add(howToPayLow);
        add(exit);
        add(exitLow);
        add(back);
        add(backLow);
        add(hallOfFame);
        add(hallOfFameglow);
        add(instructions);
        add(credits);
        add(creditsglow);
        add(meteor);
        add(meteor1);
        add(meteor2);
        add(trio);
        add(first);
        add(overLap);
        
        //*********************************
        newGameLow.setVisible(false);
        howToPayLow.setVisible(false);
        exitLow.setVisible(false);
        back.setVisible(false);
        backLow.setVisible(false);
        hallOfFameglow.setVisible(false);
        instructions.setVisible(false);
        creditsglow.setVisible(false);
        trio.setVisible(false);
        meteor.setVisible(true);
         
        //*********************************
        tm = new javax.swing.Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                gv.xo -= 1;
                gv.xf -= 1;
                if(gv.xo == -800)
                    gv.xo = 800;
                if(gv.xf == -800)
                    gv.xf = 800;
                first.setLocation(gv.xo,0);
                overLap.setLocation(gv.xf,0);
                paintm();
                repaint();
            }
        });
        tm.start();
         
        //*********************************
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                newGameLow.setVisible(true);
            }
            @Override
             public void mouseExited(MouseEvent e){
                newGameLow.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e){
                p.newGame();
            }
        });
        
        howToPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                howToPayLow.setVisible(true);
            }
            @Override
             public void mouseExited(MouseEvent e){
                howToPayLow.setVisible(false);
            }
            @Override
             public void mousePressed(MouseEvent e){
                newGame.setVisible(false);
                howToPlay.setVisible(false);
                howToPayLow.setVisible(false);
                hallOfFame.setVisible(false);
                hallOfFameglow.setVisible(false);
                exit.setVisible(false);
                back.setVisible(true);
                instructions.setVisible(true);
                credits.setVisible(false);
                creditsglow.setVisible(false);
            }
       
        });
        
        hallOfFame.addMouseListener(new MouseAdapter (){
                 @Override
            public void mouseEntered(MouseEvent e){
                hallOfFameglow.setVisible(true);
            }
            @Override
             public void mouseExited(MouseEvent e){
                hallOfFameglow.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e){
                p.openHallofFame();
            }
       
        });
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                exitLow.setVisible(true);
            }
            @Override
             public void mouseExited(MouseEvent e){
                exitLow.setVisible(false);
            }
            @Override
             public void mousePressed(MouseEvent e){
                System.exit(0);
            }
       
        });
        
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                creditsglow.setVisible(true);
                repaint();       
            }
            @Override
             public void mouseExited(MouseEvent e){
                creditsglow.setVisible(false);
                repaint();       
            }
            @Override
            public void mousePressed(MouseEvent e){
                newGame.setVisible(false);
                howToPlay.setVisible(false);
                hallOfFame.setVisible(false);
                creditsglow.setVisible(false);
                credits.setVisible(false);
                exit.setVisible(false);
                trio.setVisible(true);
                back.setVisible(true);
                repaint();
            }
        });
               
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                backLow.setVisible(true);
            }
            @Override
             public void mouseExited(MouseEvent e){
                backLow.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e){
                backLow.setVisible(false);
                newGame.setVisible(true);
                howToPlay.setVisible(true);
                hallOfFame.setVisible(true);
                exit.setVisible(true);
                back.setVisible(false); 
                instructions.setVisible(false);
                credits.setVisible(true);
                creditsglow.setVisible(false); 
                trio.setVisible(false);
            }
       
        });
    }
    
    public void paintm(){
        meteor.setLocation(gv.xm,gv.ym);
        meteor1.setLocation(gv.xm+130,gv.ym+150);
        meteor2.setLocation(gv.xm-100,gv.ym-130);
        
        gv.xm = gv.xm - 20;
        gv.ym+= 10;
        
        if(gv.xm <= -400)
        {
            gv.xm = 790;
            gv.ym = 00;
        }
    }
}