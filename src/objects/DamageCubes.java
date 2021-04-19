package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Game;

public class DamageCubes extends GameObject 
{
	
	Texture tex = Game.getInstance();
	private int type;

	public DamageCubes(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		
		
	}

	
	public void render(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		//g2d.draw(getBounds());
	}

	public ObjectId getId() 
	{
		return id;
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	

}
