package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import java_2D_Platformer.Animations;
import java_2D_Platformer.Camera;
import java_2D_Platformer.Game;
import java_2D_Platformer.Handler;




public class Player extends GameObject{
	
	private float width = 48, height = 96;
	
	private float gravity = 0.30f;
	
	private final float MAX_SPEED = 10;
	
	public int Health = 160;
	
	
	
	//1=right
	//-1=left
	
	private Handler handler;
	private Camera cam;
	
	Texture tex = Game.getInstance();
	
	private Animations playerWalk, playerWalkLeft;
	
	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		Health = 160;
		playerWalk = new Animations(10, tex.player[1], tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
		playerWalkLeft = new Animations(10, tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0) facing = -1;
		else if(velX > 0) facing = 1;
		
		if(falling || jumping)
		{
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(object);
		
		playerWalk.runAnimations();
		playerWalkLeft.runAnimations();
		
		
		
	}
	
	public void Collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{	
				//Top
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() +(40);
					velY = 0;
				}
				//bottom
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}else
					falling = true;
				
				//right
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
					velX = 0;
				}
				
				//left
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + (width-10);
					velX = 0;
				}
				
			}else if(tempObject.getId() == ObjectId.Flag){
				//switch level
				if(getBounds().intersects(tempObject.getBounds())){
					handler.switchLevel();
				}
				
			}
			
			
			if(tempObject.getId() == ObjectId.Enemy)
			{	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					if(Health >= 0){
						Health = Health -1;
						}
					}
					//bottom
					if(getBounds().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
					//right
					if(getBoundsRight().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
					//left
					if(getBoundsLeft().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
				}
			

			if(tempObject.getId() == ObjectId.DamageCube)
			{	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					if(Health >= 0){
						Health = Health -1;
						}
					}
					//bottom
					if(getBounds().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
					//right
					if(getBoundsRight().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
					//left
					if(getBoundsLeft().intersects(tempObject.getBounds())){
						if(Health >= 0){
							Health = Health -1;
							}
					}
					
				}
				
			}	
			
		}
		
	
	
	

	
	public void render(Graphics g) {
		
		g.setColor(Color.green);
	if (Health > 0){
		if(jumping){
			if(facing ==1)
				g.drawImage(tex.player_jump[2], (int)x, (int)y, 48, 96, null);
			else if(facing == -1)
				g.drawImage(tex.player_jump[3], (int)x, (int)y, 48, 96, null);
		}else
		if(velX != 0){
			if(facing ==1)
			playerWalk.drawAnimations(g, (int)x, (int)y, 48, 99);
		else
			playerWalkLeft.drawAnimations(g, (int)x, (int)y, 48, 99);
		}else{
			if(facing == 1){
		g.drawImage(tex.player[0], (int)x, (int)y, 48, 99, null);
			}
		else if(facing == -1)
			g.drawImage(tex.player[7], (int)x, (int)y, 48, 99, null);
			}
	}
		g.fillRect((int)x - 400, (int)y - 300, Health, 16);
		
		//bounds
		/*
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
		*/
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y + 25, (int)width/2, (int)height/2 - 20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-10), (int)y+25, (int)5, (int)height-30);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x+5, (int)y+25, (int)5, (int)height-30);
	}
	
	public int  getHealth(){
		
		return Health;
	}
	
	


}
