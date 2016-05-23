import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Projectile extends Actor {
	
	public static final double BULLET_SPEED = 1;
	private double velX;
	private double velY;
	private int damage;
	
	/**
	 * Creates new projectile at coordinates x,y and size width,height
	 * @param x x coordinate of projectile
	 * @param y y coordinate of projectile
	 * @param width width of projectile
	 * @param height height of projectile
	 */
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	/**
	 * Sets damage of the projectile
	 * @param damage damage of the projectile
	 */
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	/**
	 * 
	 * @return Damage of the projectile
	 */
	public int getDamage(){
		return damage;
	}

	/**
	 * Creates new projectile at coordinates x,y and size width,height and image filename sprite
	 * @param sprite filename of the image
	 * @param x x coordinate of projectile
	 * @param y y coordinate of projectile
	 * @param width width of projectile
	 * @param height height of projectile
	 */
	public Projectile(String sprite, int x, int y, int width, int height) {
		super(sprite, x, y, width, height);
	}
	
	/**
	 * 
	 * @param newVelX new x velocity of projectile
	 */
	public void setVelX(double newVelX) {
		velX = newVelX;
	}
	
	/**
	 * 
	 * @param newVelY new y velocity of projectile
	 */
	public void setVelY(double newVelY) {
		velY = newVelY;
	}
	
	/**
	 * 
	 * @return x velocity
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * 
	 * @return y velocity
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Moves projectile by velocity, updates projectile
	 */
	@Override
	public void act() {
		double tempX = velX, tempY = velY;
		
		if(tempX > 0)
			tempX += 0.5;
		else
			tempX -= 0.5;
		
		if(tempY > 0)
			tempY += 0.5;
		else
			tempY -= 0.5;
		
		moveBy((int)(tempX), (int)(tempY));
		}
	
	/**
	 * Looks through and finds the actor that is being collided with the actor, player loses health if projectile collides with player
	 * @param actors ArrayList of actors to test for
	 * @param angle current actor's angle of movement
	 * @return Actor that is being collided with
	 */
	public Actor willCollide(ArrayList<Actor> actors, double angle){
		Rectangle window = new Rectangle(0,0,Main.WINDOW_WIDTH-8,Main.WINDOW_HEIGHT-32);
		if(this instanceof BuilderBullet || this instanceof DamagerBullet || this instanceof TankBullet || this instanceof TankBulletSpecial || this instanceof DamagerFreezeBullet){
			for(int i = 0; i<actors.size();i++){
				Actor a = actors.get(i);
				if(this.getHitBox().intersects(a.getHitBox())){
					if(a instanceof TankForcefield && !(this instanceof DamagerFreezeBullet)){
						if(!(this instanceof TankBullet)){
							this.velX = -this.velX;
							this.velY = -this.velY;
						}
					}
					if(a instanceof Player && !(this instanceof DamagerFreezeBullet)){
						((Player) a).changeCurrentHealth(-damage);
						return a;
					}else if(a instanceof Player && this instanceof DamagerFreezeBullet){
						((Player)a).setFreezeTime(150);
						((Player)a).changeCurrentHealth(-damage);
						return a;
					}else if(a instanceof BreakableBarrier){
						if(this instanceof BuilderBullet || this instanceof DamagerFreezeBullet){
							((BreakableBarrier) a).changeCurrentHealth(-2);
							return null;
						}
						((BreakableBarrier) a).changeCurrentHealth(-damage);
						return a;
					}else if (a instanceof Turret && !(this instanceof TankBulletSpecial)){
						((Turret) a).changeCurrentHealth(-damage);
						if(!(this instanceof DamagerFreezeBullet))
							return a;
						return null;
					}else if(a instanceof Turret  && this instanceof TankBulletSpecial){
						((Turret) a).changeCurrentHealth(-2);
						return null;
					}else if (a instanceof Barrier && !(this instanceof TankBulletSpecial) && !(this instanceof DamagerFreezeBullet)){
						for(int j = 0; j<actors.size();j++){
							Actor b = actors.get(j);
							if(b instanceof Turret){
								if(b.getHitBox().intersects(this.getHitBox())){
									((Turret) b).changeCurrentHealth(-damage);
									return b;
								}
							
							}
						}
						return a;
					}
				}
			}
			if(!window.contains(this.getHitBox().getRectangle())){
				return this;
			}
		}else{
			for(int i = 0; i<actors.size();i++){
				Actor a = actors.get(i);
				if(this.getHitBox().intersects(a.getHitBox())){
					if(a instanceof TankForcefield){
							setVelX(-getVelX());
							setVelY(-getVelY());
							moveBy((int)(getVelX()*5), (int)(getVelY()*5));
							
					}
					else if(a instanceof Player){
						((Player) a).changeCurrentHealth(-10);
						return a;
					}
				}
				
			}
			if(!window.contains(this.getHitBox().getRectangle())){
				return this;
			}
		}
		
		
		return null;
		
	}

	/**
	 * Draws projectile at given coordinates
	 */
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
}
