import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class SmallTriangle extends Triangle{
	
	public static final int WIDTH = MediumTriangle.WIDTH / 2;
	
	public static final int HEIGHT = MediumTriangle.HEIGHT / 2;
	
	public int x, y;
	
	private Polygon triangle;
	
	public SmallTriangle(int x, int y, Color color){
		
		super(x, y, WIDTH, HEIGHT);
		
		this.x = x;
		
		this.y = y;
		
		this.color = color;
		
		init();
		
	}
	
	private void init(){
		
		int xoffs = y % 2 == 0 ? WIDTH / 2 : 0;
		
		int[] xcoords = new int[3];
		
		int[] ycoords = new int[3];
		
		if(x % 2 == 0){
			
			xcoords[0] = (int)((x / 2.0) * WIDTH) + xoffs;
			ycoords[0] = y * HEIGHT;
			
			xcoords[1] = (int)((x / 2.0) * WIDTH + WIDTH) + xoffs;
			ycoords[1] = y * HEIGHT;
			
			xcoords[2] = (int)((x / 2.0) * WIDTH + WIDTH / 2) + xoffs;
			ycoords[2] = y * HEIGHT + HEIGHT;
			
		}
		else{
			
			xcoords[0] = (int)((x / 2.0) * WIDTH) + xoffs;
			ycoords[0] = y * HEIGHT + HEIGHT;
			
			xcoords[1] = (int)((x / 2.0) * WIDTH + WIDTH) + xoffs;
			ycoords[1] = y * HEIGHT + HEIGHT;
			
			xcoords[2] = (int)((x / 2.0) * WIDTH + WIDTH / 2) + xoffs;
			ycoords[2] = y * HEIGHT;
			
		}
		
		this.triangle = new Polygon(xcoords, ycoords, 3);
		
	}
	
	public void render(Graphics g){
		
		if(color != Color.white){
			
			g.setColor(color);
			
			g.fillPolygon(triangle);
			
		}
		
	}
	
	public boolean contains(int x, int y){
		
		return triangle.contains(x, y);
		
	}
	
}
