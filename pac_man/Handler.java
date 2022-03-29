
package com.francescol.pac_man;

import java.util.ArrayList;

//this class holds all of the game objects in an arraylist
public class Handler {
        public ArrayList<GameObject> objs = new ArrayList<>();
        public ArrayList<GameObject> removeables = new ArrayList<>();
        
        
    public void add(GameObject go){
         
      objs.add(go);
        
    }
    
    public void remove(GameObject go){
        
        objs.remove(go);
        
    }

   public void remove(ArrayList<GameObject> removeables){
        for(GameObject go: removeables){
        this.objs.remove(go);    
        }
        
        
    }
}
