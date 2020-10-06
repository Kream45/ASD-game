package com.gametest;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameTest extends ApplicationAdapter {
	
	class MySprite extends Sprite{
		
		
		Rectangle hitbox;
		
		protected int vectorX;
		protected int vectorY;
		
		protected float lastX;
		protected float lastY;
		
		int check;
		char thisOne;
		MySprite(Texture t, char c){
			
			super(t);
			
			check = 0;
			vectorX = 0;
			vectorY = 0;
			
			this.thisOne = 'c';
			
			this.hitbox = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
			
		}
		
		void randomizeVectors(){
			Random r = new Random();
			int ifAtAll = r.nextInt(2);
			if (difficulty > 0)
				ifAtAll = 1;
			
			if(ifAtAll == 1){
				this.vectorX = r.nextInt(3) - 1;
				this.vectorY = r.nextInt(3) - 1;
				
				if (difficulty > 0){
					while (this.vectorX == 0 && this.vectorY == 0){
						this.vectorX = r.nextInt(3) - 1;
						this.vectorY = r.nextInt(3) - 1;
					}

				}
				
			}

		}
		
		void move(){
			this.setPosition(this.getX()+((1+difficulty)*this.vectorX), this.getY()+((1+difficulty)*this.vectorY));
		}
		
		void swap(){
			if (check == 0)
				check = 1;
			else check = 0;
		}
		
		void collisionCheck(){
			
			if (this.check == 0){
					
				if (this.getX() < 0){
					this.setX(0);
					this.vectorX *= -1;
					boopSound.play(0.5f);
				}
				
				if (this.getX() > width - 32){
					this.setX(width - 32);
					this.vectorX *= -1;
					boopSound.play(0.5f);
				}
				
				if (this.getY() < 0){
					this.setY(0);
					this.vectorY *= -1;
					boopSound.play(0.5f);
				}
				
				if (this.getY() > height - 32){
					this.setY(height - 32);
					this.vectorY *= -1;
					boopSound.play(0.5f);
				}
			}
		}
		
		
		void stop(){
			this.vectorX = 0;
			this.vectorY = 0;
		}
		
		void reverseX() {
			this.vectorX *= -1;
		}
		void reverseY() {
			this.vectorY *= -1;
		}
		
		void collision() {
			
			
			Vector2 centerThis = new Vector2();
			Vector2 centerOther = new Vector2();
			boolean didBounce = false;
			
			if (this.thisOne != 'a'){

				this.hitbox.getCenter(centerThis);
				spriteA.hitbox.getCenter(centerOther);
				
				boolean isOverlapping = this.hitbox.overlaps(spriteA.hitbox);
				
				if (isOverlapping){
					
					if (centerThis.x < centerOther.x && didBounce == false){
						
						if (this.getX() + this.getWidth() <= spriteA.getX()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
						
					}
					
					if (centerThis.x > centerOther.x && didBounce == false){
						
						if (this.getX() >= spriteA.getX() + spriteA.getWidth()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					
					if (centerThis.y < centerOther.y && didBounce == false){
						

						if (this.getY() + this.getHeight() <= spriteA.getY()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					if (centerThis.y > centerOther.y && didBounce == false){
						
						if (this.getY() >= spriteA.getY() + spriteA.getHeight()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
				}
			}
			
			
			
			
			
			if (this.thisOne != 's'){
				this.hitbox.getCenter(centerThis);
				spriteS.hitbox.getCenter(centerOther);
				
				boolean isOverlapping = this.hitbox.overlaps(spriteS.hitbox);
				
				if (isOverlapping){

					
					if (centerThis.x < centerOther.x && didBounce == false){
						
						if (this.getX() + this.getWidth() <= spriteS.getX()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
						
					}
					
					if (centerThis.x > centerOther.x && didBounce == false){
						
						if (this.getX() >= spriteS.getX() + spriteS.getWidth()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					
					if (centerThis.y < centerOther.y && didBounce == false){
						

						if (this.getY() + this.getHeight() <= spriteS.getY()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					if (centerThis.y > centerOther.y && didBounce == false){
						
						if (this.getY() >= spriteS.getY() + spriteS.getHeight()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
				}
			}
			
			
			if (this.thisOne != 'd'){
				this.hitbox.getCenter(centerThis);
				spriteD.hitbox.getCenter(centerOther);
				
				
				boolean isOverlapping = this.hitbox.overlaps(spriteD.hitbox);
				
				if (isOverlapping){
					
					if (centerThis.x < centerOther.x && didBounce == false){
						
						if (this.getX() + this.getWidth() <= spriteD.getX()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
						
					}
					
					if (centerThis.x > centerOther.x && didBounce == false){
						
						if (this.getX() >= spriteD.getX() + spriteD.getWidth()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					
					if (centerThis.y < centerOther.y && didBounce == false){
						

						if (this.getY() + this.getHeight() <= spriteD.getY()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					if (centerThis.y > centerOther.y && didBounce == false){
						
						if (this.getY() >= spriteD.getY() + spriteD.getHeight()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					

				}
			}
			
			if (this.thisOne != 'e'){
				this.hitbox.getCenter(centerThis);
				spriteEmoji.hitbox.getCenter(centerOther);
				
				
				boolean isOverlapping = this.hitbox.overlaps(spriteEmoji.hitbox);
				
				if (isOverlapping){

					if (centerThis.x < centerOther.x && didBounce == false){
						
						if (this.getX() + this.getWidth() <= spriteEmoji.getX()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					if (centerThis.x > centerOther.x && didBounce == false){
						
						if (this.getX() >= spriteEmoji.getX() + spriteEmoji.getWidth()){
							this.vectorX *= -1;
						}
						
						else this.vectorY *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);						
					}
					
					
					if (centerThis.y < centerOther.y && didBounce == false){
						

						if (this.getY() + this.getHeight() <= spriteEmoji.getY()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
					
					if (centerThis.y > centerOther.y && didBounce == false){
						
						if (this.getY() >= spriteEmoji.getY() + spriteEmoji.getHeight()){
							this.vectorY *= -1;
						}
						
						else this.vectorX *= -1;
						
						didBounce = true;
						
						boopSound.play(0.5f);
					}
				}
			}
		}
		
		
		void match(){
			this.hitbox.setPosition(this.getX(), this.getY());
		}
		
		
		void lastPos(){
			this.lastX = this.getX();
			this.lastY = this.getY();
			
		}
		
		float getLastX(){
			return this.lastX;
		}
		
		float getLastY(){
			return this.lastY;
		}
		
		
		
	}
	
	class Emoji extends MySprite{

		Emoji(Texture t, char c) {
			super(t, c);
		}
		
		void randomizeEmoji(){
			Random r = new Random();
			vectorX = r.nextInt(7) - 3;
			vectorY = r.nextInt(7) - 3;
			
			while (vectorX == 0 && vectorY == 0){
				vectorX = r.nextInt(7) - 3;
				vectorY = r.nextInt(7) - 3;
			}
		
		}
		
	}
	
	

	MySprite spriteA;
	MySprite spriteS;
	MySprite spriteD;
	Emoji spriteEmoji;
	
	SpriteBatch batch;
	
	BitmapFont text;
	
	Texture textureA;
	Texture textureS;
	Texture textureD;
	Texture textureEmoji;
	Texture wood;
	
	int points = 0;
	
	int startCheck = 0;
	int vectorLock = 0;
	int difficulty = 0;

	double time = 3000.0;	
	int lose = 0;
	
	int oof = 0;
	int wow = 0;
	
	Sound oofSound;
	Sound popSound;
	Sound wowSound;
	Sound hornSound;
	Sound boopSound;
	
	int diffCheck = 0;
	
	int width = 240;
	int height = 240;
	
	int menuCheck = 0;
	
	FreeTypeFontGenerator fontGenerator;
	FreeTypeFontParameter fontParameter;
	
	TextureAtlas atlas;
	Animation<TextureRegion> animation;
	
	float explosionATime = 0;
	float explosionSTime = 0;
	float explosionDTime = 0;
	float explosionETime = 0;
	
	boolean expA = false;
	boolean expS = false;
	boolean expD = false;
	boolean expE = false;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		text = new BitmapFont();
		text.setColor(Color.BLACK);
		
		
		textureA = new Texture(Gdx.files.internal("A.png"));
		textureS = new Texture(Gdx.files.internal("S.png"));	
		textureD = new Texture(Gdx.files.internal("D.png"));
		textureEmoji = new Texture(Gdx.files.internal("emoji.png"));
		wood = new Texture(Gdx.files.internal("wood.jpg"));
		
		spriteA = new MySprite(textureA, 'a');
		spriteS = new MySprite(textureS, 's');
		spriteD = new MySprite(textureD, 'd');
		spriteEmoji = new Emoji(textureEmoji, 'e');
		
		
		atlas = new TextureAtlas(Gdx.files.internal("explosion.pack"));
		animation = new Animation<TextureRegion>(1/45f, atlas.getRegions());

		animation.setPlayMode(Animation.PlayMode.NORMAL);
		
		
		oofSound = Gdx.audio.newSound(Gdx.files.internal("oof.wav"));
		popSound = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
		wowSound = Gdx.audio.newSound(Gdx.files.internal("wow.wav"));
		hornSound = Gdx.audio.newSound(Gdx.files.internal("horn.wav"));
		boopSound = Gdx.audio.newSound(Gdx.files.internal("boop.wav"));
		
	}
	
	public void menu () {
		text.setColor(Color.CYAN);
		text.draw(batch, "GRA   A S D", 10, height - 10);
		text.setColor(Color.CYAN);
		text.draw(batch, "Najezdzaj myszka na kwadraciki", 10, height - 30);
		text.draw(batch, "i klikaj A, S lub D na klawiaturze.", 10, height - 50);
		text.draw(batch, "Traf spacja w emotke zeby", 10, height - 70);
		text.draw(batch, "dostac ekstra punkty.", 10, height - 90);	
		text.draw(batch, "Wygrywasz kiedy", 10, height - 110);
		text.draw(batch, "zdobedziesz 50 punktow.", 10, height - 130);
		text.setColor(Color.GOLD);
		text.draw(batch, "ENTER - zacznij gre.", 10, height - 160);
	}

	@Override
	public void render () {
		
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		if (menuCheck == 0){
			
			batch.begin();
			batch.draw(wood, 0, 0, width, height);
			menu();
			batch.end();
			
			
			
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if (menuCheck == 0)
				menuCheck = 1;
		}
		
		
		if (menuCheck == 1){

			
			if (lose != 2)
				time--;
			
			if (time > 180)
				text.setColor(Color.WHITE);
			if (time <= 180)
				text.setColor(Color.YELLOW);
			if (time <= 90)
				text.setColor(Color.RED);
			
			if (startCheck == 0){
				
				if (points >= 5)
					diffCheck = 1;
				
				if (points < 5)
					diffCheck = 0;
				
				int x;
				int y;
				
				Random r = new Random();
				
				x = r.nextInt(width - 31);
				y = r.nextInt(height - 31);
				
				spriteA.setPosition(x, y);
				
				x = r.nextInt(width - 31);
				y = r.nextInt(height - 31);
				
				spriteS.setPosition(x, y);
				
				
				x = r.nextInt(width - 31);
				y = r.nextInt(height - 31);
				
				spriteD.setPosition(x, y);
				
				
				startCheck = 1;
				
				
				
				if(points >= 5){
					int i = r.nextInt(5);
					if(i == 1){
						x = r.nextInt(width - 31);
						y = r.nextInt(height - 31);
						spriteEmoji.setPosition(x,y);
						spriteEmoji.check = 0;
					}
					else {
						spriteEmoji.setPosition(700,700);
						spriteEmoji.stop();
						spriteEmoji.check = 1;
					}
				}
				else {
					spriteEmoji.setPosition(700,700);
					spriteEmoji.stop();
					spriteEmoji.check = 1;
				}
			}
			
			
			
			
			
			if (diffCheck == 1){
				
			////////	
				
				if (vectorLock == 0){
					spriteEmoji.randomizeEmoji();
					spriteA.randomizeVectors();
					spriteS.randomizeVectors();
					spriteD.randomizeVectors();
					vectorLock = 1;
				}
				
				spriteA.move();
				spriteS.move();
				spriteD.move();
				
				spriteA.match();
				spriteS.match();
				spriteD.match();
				spriteEmoji.match();
				
				
				
				
				if (spriteEmoji.check == 0)
					spriteEmoji.move();
					
			}
			
			spriteA.collisionCheck();
			spriteS.collisionCheck();
			spriteD.collisionCheck();
			spriteEmoji.collisionCheck();
			
			
			spriteA.collision();
			spriteS.collision();
			spriteD.collision();
			spriteEmoji.collision();
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
		        if( Gdx.input.getX() >= spriteA.getX() 
		        		&& Gdx.input.getX() <= (spriteA.getX() + spriteA.getWidth() ) 
		        		&& Gdx.input.getY() <= (Gdx.graphics.getHeight() - spriteA.getY() ) 
		        		&& Gdx.input.getY() >= (Gdx.graphics.getHeight() - spriteA.getY() - spriteA.getHeight() ) )  {
		        	
		        	spriteA.lastPos();
		        	spriteA.setPosition(700,700);
		        	spriteA.swap();
		        	spriteA.stop();
		        	popSound.play(0.5f);
		        	expA = true;
		        }
		        else if (lose == 0) {
		        	points--;
		        	oofSound.play(0.5f);
		        }
			}
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
		        if( Gdx.input.getX() >= spriteS.getX() 
		        		&& Gdx.input.getX() <= (spriteS.getX() + spriteS.getWidth() ) 
		        		&& Gdx.input.getY() <= (Gdx.graphics.getHeight() - spriteS.getY() )
		        		&& Gdx.input.getY() >= (Gdx.graphics.getHeight() - spriteS.getY() - spriteS.getHeight() ) )  {
		        	
		        	spriteS.lastPos();
		        	spriteS.setPosition(700,700);
		        	spriteS.swap();
		        	spriteS.stop();
		        	popSound.play(0.5f);
		        	expS = true;
		        }
		        else if (lose == 0) {
		        	points--;
		        	oofSound.play(0.5f);
		        }
			}
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
		        if( Gdx.input.getX() >= spriteD.getX() 
		        		&& Gdx.input.getX() <= (spriteD.getX() + spriteD.getWidth() ) 
		        		&& Gdx.input.getY() <= (Gdx.graphics.getHeight() - spriteD.getY() ) 
		        		&& Gdx.input.getY() >= (Gdx.graphics.getHeight() - spriteD.getY() - spriteD.getHeight() ) )  {
		        	
		        	spriteD.lastPos();
		        	spriteD.setPosition(700,700);
		        	spriteD.swap();
		        	spriteD.stop();
		        	popSound.play(0.5f);
		        	expD = true;

		        }
		        else if (lose == 0) {
		        	points--;
		        	oofSound.play(0.5f);
		        }
			}
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
		        if( Gdx.input.getX() >= spriteEmoji.getX() 
		        		&& Gdx.input.getX() <= (spriteEmoji.getX() + spriteEmoji.getWidth() ) 
		        		&& Gdx.input.getY() <= (Gdx.graphics.getHeight() - spriteEmoji.getY() ) 
		        		&& Gdx.input.getY() >= (Gdx.graphics.getHeight() - spriteEmoji.getY() - spriteEmoji.getHeight() ) )  {
		        	
		        	
		        	spriteEmoji.lastPos();
		        	spriteEmoji.setPosition(700,700);
		        	spriteEmoji.check = 1;
		        	points += 5;
		        	spriteEmoji.stop();
		        	wowSound.play(0.5f);
		        	expE = true;
		        }
		        else if (lose == 0) {
		        	points--;
		        	oofSound.play(0.5f);
		        }
			}
			
			
			if (spriteA.check == 1 && spriteS.check == 1 && spriteD.check == 1 && lose == 0){
				
				
				startCheck = 0;
				spriteA.swap();
				spriteS.swap();
				spriteD.swap();
				
				points += 3;
				vectorLock = 0;
				
				
				if (points < 5){
					difficulty = 0;
					time = 4800;
				}
				
				if (points >= 5){
					difficulty = 0;
					time = 4200;
				}
				
				if (points >= 10){
					difficulty = 0;
					time = 3600;
				}
					
				
				if (points >= 15){
					difficulty = 1;
					time = 3000;
				}
					
					
				if (points >= 30){
					difficulty = 2;
					time = 2400;
				}
					
				if (points >= 45){
					difficulty = 3;
					time = 1800;
				}
				
				
			}
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
		        points = 0;
		        lose = 0;
		        difficulty = 0;
		        time = 480;
		        startCheck = 0;
		        oof = 0;
		        wow = 0;
		        
				spriteA.check = 0;
				spriteS.check = 0;
				spriteD.check = 0;
				spriteEmoji.check = 0;
			}
			
			
			batch.begin();
			batch.draw(wood, 0, 0, width, height);
			
			spriteA.draw(batch);
			spriteS.draw(batch);
			spriteD.draw(batch);
			spriteEmoji.draw(batch);
			
			
			if (expA) {
				batch.draw(animation.getKeyFrame(explosionATime, false), spriteA.getLastX(), spriteA.getLastY());
				explosionATime += .01f;
			}
			if (expS) {
				batch.draw(animation.getKeyFrame(explosionSTime, false), spriteS.getLastX(), spriteS.getLastY());
				explosionSTime += .01f;
			}
			if (expD) {
				batch.draw(animation.getKeyFrame(explosionDTime, false), spriteD.getLastX(), spriteD.getLastY());
				explosionDTime += .01f;
			}
			if (expE) {
				batch.draw(animation.getKeyFrame(explosionETime, false), spriteEmoji.getLastX(), spriteEmoji.getLastY());
				explosionETime += .01f;
			}
			
			
				if(animation.isAnimationFinished(explosionATime)){
					explosionATime = 0;
					expA = false;
				}
			
				if(animation.isAnimationFinished(explosionSTime)){
					explosionSTime = 0;
					expS = false;
				}
			
				if(animation.isAnimationFinished(explosionDTime)){
					explosionDTime = 0;
					expD = false;
				}
				
				
					
				if(animation.isAnimationFinished(explosionETime)){
					explosionETime = 0;
					expE = false;
				}
			
				
			
			

			
			if (time <= 0 || points <= -5 && lose != 2){
				if (oof == 0){
					hornSound.play(0.4f);
					oof = 1;
				}
				
				startCheck = 1;
				lose = 1;
				time = 0;
				text.draw(batch, "PORAZKA", width / 4, height / 2);
				text.draw(batch, "RESTART - Wcisnij R", width / 4, height / 2 - 20);
				
				spriteA.setPosition(700,700);
				spriteS.setPosition(700,700);
				spriteD.setPosition(700,700);
				spriteEmoji.setPosition(700,700);
				
				spriteA.check = 1;
				spriteS.check = 1;
				spriteD.check = 1;
				spriteEmoji.check = 1;
				
				
				spriteA.stop();
				spriteS.stop();
				spriteD.stop();
				spriteEmoji.stop();
				
			}
			
			if (points >= 50){
				
				if (wow == 0){
					wowSound.play(1.0f);
					wow = 1;
				}
				
				text.setColor(Color.GREEN);
				
				points = 50;
				lose = 2;
				text.draw(batch, "ZWYCIESTWO!", width / 2, height / 2);
				text.draw(batch, "RESTART - Wcisnij R", width / 2, width / 2 - 20);
				
				spriteA.setPosition(700,700);
				spriteS.setPosition(700,700);
				spriteD.setPosition(700,700);
				spriteEmoji.setPosition(700,700);
				
				spriteA.check = 1;
				spriteS.check = 1;
				spriteD.check = 1;
				spriteEmoji.check = 1;
				
				
				spriteA.stop();
				spriteS.stop();
				spriteD.stop();
				spriteEmoji.stop();
			}
			
			text.draw(batch, "Punkty: " + points, 5, height + 35);
			text.draw(batch, "Pozostaly czas: " + (Math.round(time*10/60)/10.0), 5, height + 15);
			batch.end();
		}
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		textureA.dispose();
		textureS.dispose();
		textureD.dispose();
		textureEmoji.dispose();
		wood.dispose();
		text.dispose();
		oofSound.dispose();
		popSound.dispose();
		wowSound.dispose();
		hornSound.dispose();
		boopSound.dispose();
		atlas.dispose();
	}
}
