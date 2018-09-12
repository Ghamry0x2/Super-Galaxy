package gameproj;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import javax.swing.*;

class Winner implements Comparable<Winner>, Serializable{
    int score;
    String name;
    
    public Winner(String name, int HighScore){
        this.name = name;
        score = HighScore;
    }

    @Override
    public int compareTo(Winner w) {
        if(this.score > w.score)
            return -1;
        else if(this.score < w.score)
            return 1;
        else
            return 0;
    }
}

class HighScore extends JPanel {
    private GameConst gv;
    private HomePanel home;
    
    private Font font = GameConst.font;
    private JTextField txtName = new JTextField("Enter Your Name");

    private JLabel first, overLap;
    private JLabel  meteor, meteor1, meteor2;
    
    private int score;
    
    public HighScore(HomePanel h, GameConst g){
        gv = g;
        score = GameConst.score;
        home = h;
        init();
    }
    
    private void init(){
        setSize(800,500);
        setLayout(null);
        
        meteor = new JLabel(gv.met);
        meteor1 = new JLabel(gv.met1);
        meteor2 = new JLabel(gv.met2);
        first = new JLabel(gv.fullBackground);
        overLap = new JLabel(gv.fullBackground);
        
        meteor.setBounds(800, 00, 180,90);
        meteor1.setBounds(800, 00, 180,90);
        meteor2.setBounds(800, 00, 180,90);
        first.setBounds(gv.xo,0,800,500);
        overLap.setBounds(gv.xf,0,800,500);
        
        Timer tm = new javax.swing.Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(gv.xo == -800)
                    gv.xo = 800;
                if(gv.xf == -800)
                    gv.xf = 800;
                first.setLocation(gv.xo,0);
                overLap.setLocation(gv.xf,0);
                paintm();
            }
        });
        tm.start();
        
        if(score > HallofFame.winners[4].score)
            newScore();
        else
            showScore();
        
        add(meteor);
        add(meteor1);
        add(meteor2);
        add(first);
        add(overLap);
    }
    
    private void newScore(){
        JLabel showScore = new JLabel("Score "+score);
        showScore.setBounds(300,150,200,50);
        showScore.setFont(font);
        showScore.setForeground(Color.white);
        showScore.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(showScore);
        
        txtName.setBounds(200,250,400,50);
        txtName.setFont(font);
        txtName.setForeground(Color.white);
        txtName.setBackground(new Color(3,12,24));
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtName);
        
        txtName.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( txtName.getText().equals("Enter Your Name") )
                    txtName.setText("");
            }
        });
        
        txtName.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    HallofFame.winners[4] = (new Winner(txtName.getText(),score));
                    Arrays.sort(HallofFame.winners);
                    try {
                        FileWriter fw = new FileWriter("Winners.hsh", false);
                        PrintWriter pw = new PrintWriter(fw, true);
                        for(int i=0; i<5; i++){
                            pw.println(HallofFame.winners[i].name+"!"+HallofFame.winners[i].score);
                        }
                        pw.close();
                        fw.close();
                    }
                    catch(IOException io){
                        System.out.println("Could not load file");
                    }
                    HighScore.this.setVisible(false);
                    GameProject.newFrame();
                }
            }
        });
    }
    
    private void showScore(){
        JLabel showScore = new JLabel("Score "+score);
        showScore.setBounds(300,100,200,50);
        showScore.setFont(font);
        showScore.setForeground(Color.white);
        showScore.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(showScore);
        
        JLabel back = new JLabel(gv.bk);
        JLabel backLow = new JLabel(gv.bkg);
        
        back.setBounds(350, 400, 100, 50);
        backLow.setBounds(350, 400, 100, 50);
        
        add(back);
        add(backLow);
        
        backLow.setVisible(false);
        
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                backLow.setVisible(true);
                repaint();       
            }
            @Override
             public void mouseExited(MouseEvent e){
                backLow.setVisible(false);
                repaint();       
            }
            @Override
            public void mousePressed(MouseEvent e){
                HighScore.this.setVisible(false);
                GameProject.newFrame();
            }
       
        });
        
    }
    
    public void paintm(){
        meteor.setLocation(gv.xm,gv.ym);
        meteor1.setLocation(gv.xm+130,gv.ym+150);
        meteor2.setLocation(gv.xm-100,gv.ym-130);
        
        if(gv.xm <= -400)
        {
            gv.xm = 790;
            gv.ym = 00;
        }
    }
}

public class HallofFame extends JPanel{
    private HomePanel home;
    private GameConst gv;
    
    private JLabel back;
    private JLabel backLow;
    
    private JLabel first, overLap;
    private JLabel newGame, meteor, meteor1, meteor2;
    
    private JLabel[] lbls = new JLabel[12];
    static Winner[] winners = new Winner[5];
    private static int count;
    
    private Font font = GameConst.font;
    
    public HallofFame(HomePanel home, GameConst g){
        gv = g;
        this.home = home;
        
        refresh();
        init();
    }
    
    private void init(){
        this.setLayout(null);
        
        back = new JLabel(gv.bk);
        backLow = new JLabel(gv.bkg);
        meteor = new JLabel(gv.met);
        meteor1 = new JLabel(gv.met1);
        meteor2 = new JLabel(gv.met2);
        first = new JLabel(gv.fullBackground);
        overLap = new JLabel(gv.fullBackground);
        
        back.setBounds(300, 400, 180,90);
        backLow.setBounds(300, 400, 180,90);
        meteor.setBounds(800, 00, 180,90);
        meteor1.setBounds(800, 00, 180,90);
        meteor2.setBounds(800, 00, 180,90);
        first.setBounds(gv.xo,0,800,500);
        overLap.setBounds(gv.xf,0,800,500);
        
        backLow.setVisible(false);
        
        add(back);
        add(backLow);
        add(meteor);
        add(meteor1);
        add(meteor2);
        add(first);
        add(overLap);
        
        Timer tm = new javax.swing.Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(gv.xo == -800)
                    gv.xo = 800;
                if(gv.xf == -800)
                    gv.xf = 800;
                first.setLocation(gv.xo,0);
                overLap.setLocation(gv.xf,0);
                paintm();
            }
        });
        tm.start();
        
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
                HallofFame.this.setVisible(false);
                home.setVisible(true);
            }
        });
    }
    
    public void paintm(){
        meteor.setLocation(gv.xm,gv.ym);
        meteor1.setLocation(gv.xm+130,gv.ym+150);
        meteor2.setLocation(gv.xm-100,gv.ym-130);
        
        if(gv.xm <= -400)
        {
            gv.xm = 790;
            gv.ym = 00;
        }
    }
    
    public void refresh(){

        for(int i=0; i<winners.length; i++)
            winners[i] = new Winner("Name",0);

        try {
            FileReader fr = new FileReader("Winners.hsh");
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            for(int i=0; i<5 && s!=null; i++){
                int j = s.indexOf("!");
                winners[i] = new Winner(s.substring(0,j),Integer.parseInt(s.substring(j+1)));
                s = br.readLine();
            }
        }
        catch(IOException e){
            System.out.println("could not load file");
        }
        
        for(int i=0; i<6; i++){
            lbls[i] = new JLabel();
            lbls[i].setBounds(200,50+(i*60),250,50);
            lbls[i].setFont(font);
            lbls[i].setForeground(Color.white);
            add(lbls[i]);
            if(i>=1)
                lbls[i].setText(winners[i-1].name);
        }
        lbls[0].setText("Name");
        
        for(int i=6; i<12; i++){
            lbls[i] = new JLabel();
            lbls[i].setBounds(500,50+((i-6)*60),100,50);
            lbls[i].setFont(font);
            lbls[i].setForeground(Color.white);
            add(lbls[i]);
            if(i>=7)
                lbls[i].setText(winners[i-7].score+"");
        }
        lbls[6].setText("Score");
    }
}
