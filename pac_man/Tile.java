
package com.francescol.pac_man;

import java.awt.Color;

public class Tile {
    public int x,y;
    public Type t;
    
    //testing purposes
    public Color c;
    
    public Tile(Type t){
         
        this.t = t;
    }
    
    public Tile(int x, int y){
        if (x > Main.width){
            this.x = 0;
            this.y += 15;
        } else{
            this.x = x;
            this.y = y;
        }
        this.y = y;
               
    }
    
     public Tile(int x, int y,Color c){
        if (x > Main.width){
            this.x = 0;
            this.y += 15;
        } else{
            this.x = x;
            this.y = y;
        }
        
        this.c = c;       
    }
    @Override
    public String toString(){
        
        return String.format("x = %s y = %s",this.x,this.y);
    }
    
    public void setType(Type t){
         this.t = t;
    }
}

