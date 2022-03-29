
package com.francescol.pac_man;
import java.util.ArrayList;

import java.awt.*;
import java.util.Random;
import java.io.File;
import java.util.Scanner;




public class Game extends Canvas{
    public static int state = 0;//-2 = help, -1 = scoreboard, 0 = menu, 1+ is the current level. 
    public Handler h;
    public static final ArrayList<Tile> tiles = new ArrayList<>();
    private final SpriteList sl;
    private Player p;
    private Random r;
    public boolean paused = false;
    
    
    private int playerSpawnX,playerSpawnY; 
   
    
    
   
    public Game(){
        r = new Random();
        h = new Handler();
        sl = new SpriteList();
       generateTileMap();
       this.setBackground(Color.black);
       
       playerSpawnX = 290;
       playerSpawnY = 434;
       
       
     
   
    }
 
   //this function updates every gameobjects actions and drawing to the screen
        
    
    
    public void restart(){
        
        for (GameObject g: h.objs){
            
            if(g.type == Type.GHOST || g.type == Type.FRUIT){
            h.remove(g);   
            }
        }
        setUpLevel(state);
    }
    
    public void setUpLevel(int state){
        setTiles();
        h.add(new Player(playerSpawnX,playerSpawnY,Type.PLAYER,h,sl.images.get(1)));
       // spawnGhosts(state);
       
    }
    
    
    public void spawnGhosts(int state){
        int spawnx = 0;
        int spawny = 0;
        //have to play around with where they spawn once I impliment gui
        for (int i = 0; i < 4; i++){
            double speed = 1 + state * .2; // can be adjusted, supposed to make ghosts faster as levels go on
           h.add(new Ghost(spawnx,spawny,Type.GHOST,h,sl.images.get(3),speed));
        }
    }
    
    public void spawnFruit(){
        //1/2000 chance to spawn a fruit at where the player spawns. Can be changed after testing.
        if(r.nextInt(0,2000) == 27){
        h.add(new Fruit(playerSpawnX,playerSpawnY,Type.FRUIT,h,sl.images.get(6)));
        }
    }
    
    
   
    public void generateTileMap(){
       
       int currentHeight = 0;
       int currentWidth = 0;
       System.out.println(Main.height);
        while(currentHeight < Main.height){
            
            
            if(currentWidth >= Main.width){
                currentWidth = 0;
                currentHeight += 15;
               // tiles.add(new Tile(0,currentHeight));
               tiles.add(new Tile(0,currentHeight));
             //  System.out.println(String.format("Created tile at x = %s y = %s",currentWidth,currentHeight));
            }else{
            tiles.add(new Tile(currentWidth,currentHeight));
            //System.out.println(String.format("Created tile at x = %s y = %s",currentWidth,currentHeight));
            } 
           
            currentWidth += 10;
          
        }
        //removes the last tile that is added erroneosly. absolutely horrible solution but what can you do?
        tiles.remove(3600);
        }
    public void setTiles(){
        //reads the tilemap and stores the tiles' type in a huge string
        String tilesAsString = "";
      try{
       File tileMap = new File("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\TileMap.txt");
       Scanner scanner = new Scanner(tileMap);
       
       while(scanner.hasNextLine()){
           tilesAsString += scanner.nextLine();
       }
          }catch(Exception e){
         
      }
      char[] tilesAsChar = tilesAsString.toCharArray();
     // System.out.println(tiles.size());
    //  System.out.println(tilesAsChar.length);
     for(int i = 0; i < tiles.size();i++){
    /*for(Tile t:tiles){
          //makes walls around the border of the screen
          if(t.x == 0 || t.x == 590 || t.y == 0 || t.y == 885){
              t.setType(Type.WALL);
              h.add(new Wall(t.x,t.y,t.t,h,sl.images.get(2))); 
             // System.out.println(String.format("Made a wall at %d,%d",t.x,t.y));
          }else if(t.x == 10 & t.y == 15 || t.x == 10 & t.y == 870 || t.x == 580 & t.y == 15 || t.x == 580 & t.y == 870){
               //places powerpellets on the corners
               t.setType(Type.POWERPELLET);
              
          }else {
             //puts pellets everywhere else
             t.setType(Type.PELLET);
              h.add(new Pellet(t.x,t.y,t.t,h,sl.images.get(5)));   
          } */
         
       //  System.out.println(i);
        //  System.out.println(tilesAsChar[i]);
          switch(tilesAsChar[i]){
            
              case '1':
              //makes a wall
             // System.out.println(String.format("Made a wall at %d,%d",tiles.get(i).x,tiles.get(i).y));
              h.add(new Wall(tiles.get(i).x,tiles.get(i).y,Type.WALL,h,sl.images.get(2)));    
              break;
              
              case '2':
              //makes a pellet    
              h.add(new Pellet(tiles.get(i).x+4,tiles.get(i).y+7,Type.PELLET,h,sl.images.get(5)));
              break;
              
              case '3':
              //makes a powerPellet
                  h.add(new Wall(tiles.get(i).x,tiles.get(i).y,Type.POWERPELLET,h,sl.images.get(4)));
              break;
              
              case '4':
              //makes a     
              break;
          }
         
          
          
          
          //will implement the drawing of the other walls once GUI is set up
          
      }
      
    }
    @Override
     public void paint(Graphics g){
       
     for(GameObject go: h.objs){
         go.render(g);
         g.setColor(Color.red);
        //used for testing g.drawRect((int)go.sprite.x,(int) go.sprite.y, (int)(go.sprite.width*1.3), (int) (1.3* go.sprite.height));
     }
              
    }
      
   
     
    
    
}
    
    

