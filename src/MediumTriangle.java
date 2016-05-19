import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class MediumTriangle extends Triangle{
	
	public static final int WIDTH = LargeTriangle.WIDTH / 2;
	
	public static final int HEIGHT = LargeTriangle.WIDTH / 2;
	
	public int x, y;
	
	private Polygon triangle;
	
	public MediumTriangle(int x, int y, Color color){
		
		super(x, y, WIDTH, HEIGHT);
		
		this.x = x;
		
		this.y = y;
		
		this.color = color;
		
		init();
		
	}
	
	private void init(){
		
		int xoffs = (y % 2 == 0 ? WIDTH / 2 : 0) + SmallTriangle.WIDTH / 2;
		
		int yoffs = SmallTriangle.HEIGHT * 2;
		
		if(x % 2 == 0){
			
			int[] xcoords = new int[3];
			
			int[] ycoords = new int[3];
			
			xcoords[0] = (int)((x / 2.0) * WIDTH) + xoffs;
			ycoords[0] = y * HEIGHT + yoffs;
			
			xcoords[1] = (int)((x / 2.0) * WIDTH + WIDTH) + xoffs;
			ycoords[1] = y * HEIGHT + yoffs;
			
			xcoords[2] = (int)((x / 2.0) * WIDTH + WIDTH / 2) + xoffs;
			ycoords[2] = y * HEIGHT + HEIGHT + yoffs;
			
			this.triangle = new Polygon(xcoords, ycoords, 3);
			
		}
		else{
			
			int[] xcoords = new int[3];
			
			int[] ycoords = new int[3];
			
			xcoords[0] = (int)((x / 2.0) * WIDTH) + xoffs;
			ycoords[0] = y * HEIGHT + HEIGHT + yoffs;
			
			xcoords[1] = (int)((x / 2.0) * WIDTH + WIDTH) + xoffs;
			ycoords[1] = y * HEIGHT + HEIGHT + yoffs;
			
			xcoords[2] = (int)((x / 2.0) * WIDTH + WIDTH / 2) + xoffs;
			ycoords[2] = y * HEIGHT + yoffs;
			
			this.triangle = new Polygon(xcoords, ycoords, 3);
			
		}
		
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
