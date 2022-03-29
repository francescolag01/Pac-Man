
package com.francescol.pac_man;

import java.awt.Image;


public class Ghost extends GameObject{
    public double speed;
    public Ghost(int posX, int posY, Type type,Handler h, Image i,double speed) {
	super(posX, posY, type,h,i);
        this.speed = speed;
    }
    

    @Override
    public void run() {
       pathing();
    }
    
    
    public void pathing(){
        //first we get the location of the player
        double playerx,playery;
        for (GameObject go: h.objs){
            if (go.type == Type.PLAYER){
                playerx = go.posX;
                playery = go.posY;
            }
        }
        //to be continued once gui is setup
    }
}
