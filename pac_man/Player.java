
package com.francescol.pac_man;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



public class Player extends GameObject {
    
    double speed = .2;
    static int score = 0;
    static int lives = 3;
    boolean powerPellet = false;
    boolean moving = false;


    public Player(double posX, double posY, Type type,Handler h, Image i) {
	super(posX, posY, type,h,i);   
        
       
    }
    public void collide(){
        Rectangle2D r1 = this.sprite.getBounds();
       
        /* 
        so the methodolgy here is this: if i remove the gameobject the second it collides
        a noConcurrentModification exception is thrown for good reason. So, my solution to that is
        when the object collides, the collided object is teleported out of bounds and made invisible
        to make it look like it was removed, but it is removed when the objs arraylist is done looping.
        */
        for (GameObject g: this.h.objs){
           
            Rectangle2D r2 = g.sprite.getBounds();
           //System.out.println(g.type);
           //System.out.println(String.format("Rect 1 x: %f y:%f Rect 2 x:%f y:%f",r1.getX(),r1.getY(),r2.getX(),r2.getY()));  
            //collides with ghost
            if(g.type == Type.GHOST & r1.intersects(r2) & this.powerPellet == false){
                
            this.die();
                
            } else if(this.powerPellet == true & g.type == Type.GHOST & r1.intersects(r2)) {
                System.out.println("Collided with " + g.type);
                score += 100;
                g.sprite.isVisible = false;
                g.posX = 2000;
                g.posY = 2000;
                h.removeables.add(g);
                
            }
            //collides with pellet
            if(g.type == Type.PELLET & r1.intersects(r2)){
                System.out.println("Collided with " + g.type);
                this.score += 10;
                g.posX = 2000;
                g.posY = 2000;
                h.removeables.add(g);
            }
            //collides with power pellet
            if(g.type == Type.POWERPELLET & r1.intersects(r2)){
                System.out.println("Collided with " + g.type);
                this.score += 50;
                this.powerPellet = true;
                g.posX = 2000;
                g.posY = 2000;
                h.removeables.add(g);
            }
            //collides with fruit
            if(g.type == Type.FRUIT & r1.intersects(r2)){
                
            score += 50 * Game.state;
            g.posX = 2000;
                g.posY = 2000;
                h.removeables.add(g);
            }
            //collides with wall
            if(g.type == Type.WALL & r1.intersects(r2)){
                System.out.println("Collided with " + g.type);
            this.velX = 0;
            this.velY = 0;
          
            if(this.posX <= g.posX ){
                System.out.println("moving left");
                this.posX -= 1;
            } else if(this.posX > g.posX){
                System.out.println("moving right");
                this.posX += 1;
            } 
            
            if(this.posY >= g.posY){
                System.out.println("moving down");
                this.posY += 1;
            } else if(this.posY - g.posY < 0 ){
                System.out.println("moving up");
                this.posY -= 1;
            } 
        
        
      }
        
    }
    }
    

    @Override
    public void run() {
       // System.out.println(String.format("x = %f, y = %f",this.posX,this.posY));
       move();
       this.sprite.setXandY(this.posX, this.posY);
       collide();
        if(powerPellet == true){
            powerPelletState(Game.state);
        }
        
        
      //  System.out.println(String.format("X vel = %d, Y vel = %d",this.velX,this.velY));
    }
    
    public void die(){
        
        //playDeathSound() if i add in sounds
        lives -= 1;
        this.sprite.setVisibility(false);
        Main.player = null;
        h.remove(this);
       // Game.restart(); have to implement 
    }

    

    public void powerPelletState(int state){
        double timePowered = 100 * (state *.9); // makes it so the powerpellet state gets shorter as game goes on
        while(timePowered > 0){
            timePowered -= 1;
        }
        this.powerPellet = false;
    }
   
    public void setMoving(Boolean moving){
        this.moving = moving;
    }
}