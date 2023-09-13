package com.catmiih.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.catmiih.main.Game;
import com.catmiih.world.Camera;
import com.catmiih.world.World;

public class Player extends Entity{
	
	public BufferedImage sprite_right;
	public BufferedImage sprite_left;
	public BufferedImage sprite_up;
	public BufferedImage sprite_down;
	
	public boolean right,up,left,down;

	public int lastDir = 0;

	public Player(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);	
		sprite_right = Game.spritesheet.getSprite(16*2, 0, 16, 16);
		sprite_left = Game.spritesheet.getSprite(16*3, 0, 16, 16);
		sprite_up = Game.spritesheet.getSprite(16*5, 0, 16, 16);
		sprite_down = Game.spritesheet.getSprite(16*4, 0, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 0;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = 1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			lastDir = 2;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			lastDir = 3;
		}
		
		verificarPegaMoeda();
		
		if(Game.moedas_contagem == Game.moeda_atual) {
			//Tela de vitória
			World.restartGame();
		}
	}
	
	public void verificarPegaMoeda() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Moeda) {
				if(Entity.isColidding(this, current)) {
					Game.moeda_atual++;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}

	public void render(Graphics g) {
		if(lastDir == 0) {
			g.drawImage(sprite_right,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 1){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 2){
			g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == 3){
			g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
	}	
}
