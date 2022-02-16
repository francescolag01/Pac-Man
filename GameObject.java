
public abstract class GameObject {
	private float posX, posY, velX,velY;
	
	
	public GameObject(float posX,float posY,float velX, float velY) {
		
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velX = velY;
	}
	
	public void move() {
		
		this.posX += velX;
		this.posY += velY;
		
		
	}
	
	
}

