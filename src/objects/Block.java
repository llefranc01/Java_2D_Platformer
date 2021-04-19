package objects;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Game;
import java_2D_Platformer.Handler;
public class Block extends GameObject 
{
	
	Texture tex = Game.getInstance();
	private int type;
	private Handler handler;

	public Block(float x, float y, Handler handler, int type, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		
	}
	
	
	public void render(Graphics g) 
	{
		if(type == 0) //stone block
			g.drawImage(tex.block[0],(int)x, (int)y, null);
		if(type == 1) //grass block
			g.drawImage(tex.block[1],(int)x, (int)y, null);
		if(type == 2) //lava block
			g.drawImage(tex.block[2],(int)x, (int)y, null);
		if(type == 6) //lava block bottom
			g.drawImage(tex.block[6],(int)x, (int)y, null);
		if(type == 3) //dirt block
			g.drawImage(tex.block[3],(int)x, (int)y, null);
	}

	public ObjectId getId() 
	{
		return id;
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	

}
