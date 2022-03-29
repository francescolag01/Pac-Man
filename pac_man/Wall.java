
package com.francescol.pac_man;

import java.awt.Image;

/**
 *
 * @author franc
 */
public class Wall extends GameObject{

    public Wall(int posX, int posY, Type type,Handler h, Image i) {
	super(posX, posY, type,h,i);     
    }
    @Override
    public void run() {
        
    }
    
}
