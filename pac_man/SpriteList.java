
package com.francescol.pac_man;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

//this class generates all of the sprites from their file location, and stores the images that the program can use.
public class SpriteList {
    
   public ArrayList<Image> images = new ArrayList<>();
   private ArrayList<String> fileLocs = new ArrayList<>();
   //image.get(i) returns in increasing i: default,pacman,wall,ghost,powerpellet,pellet,fruit 
   
   
   public SpriteList(){
       setUpImageLoc();
       
       //generates default texture first
       try{
           System.out.println("Attempting to read file at "+fileLocs.get(0));
           this.images.add(ImageIO.read(new File(fileLocs.get(0))));
       }catch(Exception e){
           
        }
        //generates the sprites from string, stores in image arraylist
       for (int i = 1;i < 7;i++){ 
        try{
           System.out.println("Attempting to read file at "+fileLocs.get(i));
           this.images.add(ImageIO.read(new File(fileLocs.get(i))));
           System.out.println("Successfully read "+ fileLocs.get(i));
        }catch(Exception e){
           System.out.println("File not found, setting to default sprite");
            this.images.add(images.get(0));
        }    
       }
      System.out.println("Generated "+images.size() +" images" );
    }
   
   public void setUpImageLoc(){
       //default texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\missingTexture.jpg");
       //pacman texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\pacman.png");
       //wall texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\wall.png");
       //ghost texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\ghost.png");
       //powerPellet texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\powerPellet.png");
       //pellet texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\pellet.png");
       //fruit texture
       fileLocs.add("C:\\Users\\franc\\Documents\\NetBeansProjects\\Pac-Man\\Pac_Man\\src\\main\\java\\com\\francescol\\pac_man\\textures\\fruit.png");
       
       
       
   }
}
