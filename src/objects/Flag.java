package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Game;

public class Flag extends GameObject{
	

	Texture tex = Game.getInstance();
	
	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
	
		
		
	}
	

	@Override
	public void render(Graphics g) {
		
		g.drawImage(tex.block[5], (int)x, (int)y - 20, 64, 64, null);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
