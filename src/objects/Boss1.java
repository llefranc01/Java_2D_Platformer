package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Animations;
import java_2D_Platformer.Game;
import java_2D_Platformer.Handler;

public class Boss1 extends GameObject{
	
	private float gravity = 0.20f;

	private Handler handler;
	
	private float width = 48, height = 96;
	
	Texture tex = Game.getInstance();
	
	private Animations enemyWalk;
	
	private int type;

	public Boss1(float x, float y, int type, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.type = type;
		
		enemyWalk = new Animations(10, tex.enemy[0], tex.enemy[1]);
	}

	
	public void tick(LinkedList<GameObject> object) 
	{
		
		x += velX;
		y += velY;
		
		
		velY += gravity;	
		
		Collision(object);
		enemyWalk.runAnimations();
	}
	

	
	public void Collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{	
				//bottom
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}else
					falling = true;	
				
			}
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(getDetectionBounds().intersects(tempObject.getBounds())){
					
						y += -tempObject.getVelY();
						x--;
					}
				
				if(getDetectionBoundsRight().intersects(tempObject.getBounds())){
					
					y += -tempObject.getVelY();
					x++;
				}
			}
			
			if(tempObject.getId() == ObjectId.Bullet)
			{
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					handler.removeObject(tempObject);
					}
					
				}
					
			}
				
		}
		
	
	
	public void render(Graphics g) {	
		
		g.drawImage(tex.enemy[0], (int)x, (int)y, 48, 99, null);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		//g2d.draw(getDetectionBounds());
		//g2d.draw(getDetectionBoundsRight());
		enemyWalk.drawAnimations(g, (int)x, (int)y, 48, 99);
	}
	
	public ObjectId getId() 
	{
		return id;
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getDetectionBounds(){
		return new Rectangle((int)x-125, (int)y - 50, 150, 150);
	}
	
	public Rectangle getDetectionBoundsRight(){
		return new Rectangle((int)x+25, (int)y - 50, 150, 150);
	}
	

}
