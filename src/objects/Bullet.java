package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.xml.ws.handler.Handler;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Game;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	public Bullet(float x, float y, ObjectId id, int velX, Handler handler) {
		super(x, y, id);
		this.velX = velX;
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		x += velX;
		
	}
	
	
	
	@Override
	public void render(Graphics g) {
		//g.setColor(Color.RED);
		//g.fillRect((int)x, (int)y, 16, 16);
		
		g.drawImage(tex.block[4], (int)x, (int)y - 20, 64, 64, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
