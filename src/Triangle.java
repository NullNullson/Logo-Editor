
public abstract class Triangle {
	
	private int x, y;
	
	private int w, h;
	
	protected Triangle(int x, int y, int w, int h){
		
		this.x = x;
		
		this.y = y;
		
		this.w = w;
		
		this.h = h;
		
	}
	
	public int getRealX(){
		
		return (x * w) / 2 + w / 2;
		
	}
	
	public int getRealY(){
		
		return y * h + h;
		
	}
	
	public int getRealXWidth(){
		
		return (x * w) / 2 + w;
		
	}
	
	public int getRealYHeight(){
		
		return y * h + h * 2;
		
	}
	
}
