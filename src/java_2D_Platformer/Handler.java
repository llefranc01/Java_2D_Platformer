package java_2D_Platformer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;
import objects.DamageCubes;
import objects.Enemy;
import objects.Flag;
import objects.Player;

public class Handler 
{
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	
	public Handler (Camera cam){
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png");
		level3 = loader.loadImage("/level3.png");
		level4 = loader.loadImage("/level4.png");
	}
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
	}
	
	public void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + " " + h);
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				
				if(red == 255 && green == 255 & blue == 255) addObject(new Block(xx*32, yy*32, null, 3, ObjectId.Block)); //dirt
				
				if(red == 255 && green == 0 & blue == 0) addObject(new Block(xx*32, yy*32, null, 2, ObjectId.Block)); //lava
				
				if(red == 255 && green == 0 & blue == 110) addObject(new Block(xx*32, yy*32, null, 6, ObjectId.Block)); //lava bottom
				
				if(red == 128 && green == 128 & blue == 128) addObject(new Block(xx*32, yy*32, null, 1, ObjectId.Block));//grass block
				
				if(red == 64 && green == 64 & blue == 64) addObject(new Block(xx*32, yy*32, null, 0, ObjectId.Block));//stone block
				
				if(red == 255 && green == 0 & blue == 0) addObject(new DamageCubes(xx*32, yy*32-5, 2, ObjectId.DamageCube));//hit box
				
				if(red == 0 && green == 255 & blue == 0) addObject(new Enemy(xx*32, yy*32, 0, this, ObjectId.Enemy));//crawler
				
				if(red == 250 && green == 250 & blue == 0) addObject(new Flag(xx*32, yy*32, ObjectId.Flag));//objective
				
				if(red == 0 && green == 0 & blue == 255) addObject(new Player(xx*32, yy*32, this, cam, ObjectId.Player));//you
			}
		}
	}
	
	public void switchLevel(){
		clearLevel();
		cam.setX(0);
		
		
		switch(Game.LEVEL){
		
		case 1:
			LoadImageLevel(level2);
			break;
			
		case 2:
			LoadImageLevel(level3);
			break;
		case 3:
			LoadImageLevel(level4);
			break;
		}
		
		Game.LEVEL++;
	}
	
	private void clearLevel(){
		object.clear();
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	//place blocks
	public void createLevel()
	{
		
		//for(int xx = 0; xx < Game.WIDTH+32; xx+= 32){
			//addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
			//}
		
		//for(int xx = 100; xx < Game.WIDTH+32; xx+= 32){
			//addObject(new Block(xx, Game.HEIGHT-200, ObjectId.Block));
			//}
		
		//for(int xx = 0; xx < Game.HEIGHT+32; xx+= 32){
			//addObject(new Block(0, xx, ObjectId.Block));
			//
			//}
		
	}

}
