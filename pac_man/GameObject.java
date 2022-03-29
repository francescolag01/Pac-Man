
package com.francescol.pac_man;


import java.awt.Graphics;
import java.awt.Image;


public abstract class GameObject {
	public double posX, posY;
        public double velX,velY;
        public Type type;
        public Sprite sprite;
        public Handler h;


	public GameObject(double posX,double posY, Type type,Handler h, Image i) {

		this.posX = posX;
		this.posY = posY;
		this.velX = 0;
		this.velX = 0;
                this.type = type;
                this.h = h;
                this.sprite = new Sprite(this.posX,this.posY,true,i);
                
	}

	public void move() {

		this.posX += velX;
		this.posY += velY;
               // System.out.println(this.posY);

	}
        
        public void setVelX(double v){
            
            this.velX = v;
        }
        
           public void setVelY(double v){
            
            this.velY = v;
        }
           
        public void clampInWall(GameObject go, Wall w){
         //useful method that constrains a value to a certain range
        if(go.posX > w.posX){
            
            
        }  
          
      }
        public void render(Graphics g){
            
            if(this.sprite.isVisible == true){
                g.drawImage(this.sprite.image,(int)this.posX,(int)this.posY,null);
                
               // System.out.println("Drew a "+ this.type + " at X: " +this.posX + " Y: " + this.posY );
            }    
        }
        //this method will handle any actions any GameObjects do
        public abstract void run();
        
    

}

