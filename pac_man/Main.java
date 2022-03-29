
package com.francescol.pac_man;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent.KeyBinding;


public class Main implements ActionListener{
    public static JPanel menu,help,score,playArea;
    public static ActionListener l;
    public static  Player player = null;
    public static AbstractAction leftAction,rightAction,upAction,downAction;
    public static Graphics g;
    
    public final static int width = 600;
    public final static int height = 900;
    
    
    public static void main(String[] args){
       
       CardLayout cl = new CardLayout();
  
        help = new JPanel();
        //help.add(new );
        help.setLayout(new BoxLayout(help,BoxLayout.Y_AXIS));
        //this code makes the buttons appear in the middle of the screen
        help.setBorder(new EmptyBorder(height/2,width/2,height/2,width/2));
        help.setSize(width,height);
        help.setBackground(Color.black);
        cl.addLayoutComponent(help,"help");
        score = new JPanel();
        score.setLayout(new BoxLayout(score,BoxLayout.Y_AXIS));
        score.setBorder(new EmptyBorder(height/2,width/2,height/2,width/2));
        score.setSize(width,height);
        score.setBackground(Color.black);
        cl.addLayoutComponent(score,"score");
        menu = new JPanel();
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        menu.setBorder(new EmptyBorder(height/2,width/2,height/2,width/2));
        menu.setSize(width,height);
        menu.setBackground(Color.black);
        cl.addLayoutComponent(menu,"menu");
        playArea = new JPanel();
        //playArea.setLayout(new BoxLayout(playArea,BoxLayout.Y_AXIS));
        playArea.setSize(width,height);
        playArea.setBackground(Color.black);
        playArea.setFocusable(true);
        
        setKeyBindings();
        
        cl.addLayoutComponent(playArea,"playArea");
       drawMainMenu();
       drawHelp();
       drawScoreBoard();
       
        //setting up Jframes and whatnot
        Game game = new Game();
        game.setSize(width, height);
        JFrame j = new JFrame("Pac-Man");
        JPanel p = new JPanel(cl);
        p.setSize(width,height);
        playArea.add(game);
        //j.setLayout(cl);
        j.setSize(width,height);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocation(600,0);
        j.setResizable(false);
        j.add(p);
        p.add(menu); 
        p.add(help);
        p.add(score);
        p.add(playArea);
        j.pack();
        playArea.requestFocusInWindow();
        //used to draw graphics 
        
        
        
        //game loop
        game.setUpLevel(2);
       
        while(true){
           
            
            //chooses which menu to render
            if(Game.state == -2){
            cl.show(p,"help");
            }else if (Game.state == -1){
            cl.show(p,"score");    
            }else if (Game.state == 0){
            //System.out.println("in main menu");
            cl.show(p,"menu");    
            }else if (Game.state > 0){
           // System.out.println("in game");
            cl.show(p, "playArea");
            
            //latches on to the current player to later check if it died at any point
            if(player == null){
                for(GameObject go: game.h.objs){
                    if (go.type == Type.PLAYER){
                        player = (Player)go;
                        System.out.println("Found player"+ player);
                        
                       
                    }
                }
            }
                
            //gameobject will perform its action, then sprite is drawn at new position
            if(game.paused == false){
                g = game.getGraphics();
                
                for(GameObject go: game.h.objs){
                go.run();
                
                }
              game.h.remove(game.h.removeables);
              game.paint(g);
              game.update(g);
              g.dispose();
                
              
            }
            
            
                
                
                
           //checks to see if the player died. if true, restarts the level.
            if(player == null){
                    game.restart();
                }    
                
                
            }
            }     
        
            
        }
    /*these methods draw the buttons at the start of the program,
     but they will only be visible under certain conditions*/
        public static void drawMainMenu(){
            JLabel title = new JLabel("PacMan");
            JButton play = new JButton("Play");
            JButton helpBtn = new JButton("How to Play");
            JButton scoreBtn = new JButton("High Scores");
            play.addActionListener((e ->{
            Game.state = 1;
            System.out.println(Game.state);
            }));
            helpBtn.addActionListener((e ->{
            Game.state = -2;
            System.out.println(Game.state);
            }));
            scoreBtn.addActionListener((e ->{
            Game.state = -1;
            System.out.println(Game.state);
            }));
            
           menu.add(title);
           menu.add(play);
           menu.add(scoreBtn);
           menu.add(helpBtn);

        }
            
        public static void drawScoreBoard(){
            //have to add way to display scores
            JButton back = new JButton("Back");
            back.addActionListener(e ->{
            Game.state = 0;
            System.out.println(Game.state);
            });
            score.add(back);
        }    
            
        public static void drawHelp(){
            JLabel text = new JLabel("Insert help text here");
            JButton back2 = new JButton("Back");
            back2.addActionListener((e ->{
            Game.state = 0;
            System.out.println(Game.state);
            }));
            help.add(text);
            help.add(back2);
        }
        
        public static void drawPlayArea(){
            JLabel lives, score, level;
            lives = new JLabel("Lives: " + Player.lives);
            score = new JLabel("Score: " + Player.score);
            level = new JLabel("Level: " + Game.state);
            playArea.add(lives);
            lives.setLocation(0,800);
            lives.setForeground(Color.blue);
            
            playArea.add(score);
            score.setLocation(0,0);
            score.setForeground(Color.blue);
            
            playArea.add(level);
            level.setLocation(0,0);
            level.setForeground(Color.blue);
        }
        
       
        
       
        

    @Override
    public void actionPerformed(ActionEvent e) {
       
     
    }

    public static void setKeyBindings(){
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        
        playArea.getInputMap().put(KeyStroke.getKeyStroke("W"), "upAction");
        playArea.getActionMap().put("upAction",upAction);
        
         playArea.getInputMap().put(KeyStroke.getKeyStroke("A"), "leftAction");
        playArea.getActionMap().put("leftAction",leftAction);
        
         playArea.getInputMap().put(KeyStroke.getKeyStroke("S"), "downAction");
        playArea.getActionMap().put("downAction",downAction);
        
         playArea.getInputMap().put(KeyStroke.getKeyStroke("D"), "rightAction");
        playArea.getActionMap().put("rightAction",rightAction);
        
        
    }

   

}
    
class UpAction extends AbstractAction{
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Main.player.setMoving(true);
        Main.player.setVelX(0);
        Main.player.setVelY(-Main.player.speed);
        
    }
}

class DownAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        
         Main.player.setVelX(0);
         Main.player.setVelY(Main.player.speed);
    }
    
    
}

class LeftAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.player.setVelX(-Main.player.speed);
        Main.player.setVelY(0);
    }
    
    
}

class RightAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
      Main.player.setVelX(Main.player.speed);
      Main.player.setVelY(0);  
    }
    
    
}

