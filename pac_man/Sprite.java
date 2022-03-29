
package com.francescol.pac_man;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;




public class Sprite {
    
    public double x,y,height,width;
    public boolean isVisible;
    public Image image;
    

    public Sprite(double x, double y, boolean isVisible, Image img){
        this.x = x;
        this.y = y;
        this.isVisible = isVisible;
        this.image = img;
        
        this.setUpDimensions();
            
        }
        
       
    
    //returns the bounds of the sprite for collison purposes
    public Rectangle2D getBounds(){
       // System.out.println(String.format("made a bounding rectangle: x:%f, y:%f, w:%f, h:%f",this.x,this.y,this.height,this.width));
        return new Rectangle2D.Double(this.x,this.y,this.width,this.height);
    }
    
    //makes the dimensions of the object the actual dimensions of the image.
    public void setUpDimensions(){
        
    this.width = this.image.getWidth(null);
    this.height = this.image.getHeight(null);
        
    }
    
    public void setVisibility(Boolean visible){
        this.isVisible = visible;
    }
    
    public void setXandY(double x, double y){
        this.x = x;
        this.y = y;
    }
}
