import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main extends Canvas implements Runnable, MouseListener, MouseMotionListener, ColorCallback{
	
	private Thread thread;
	
	private BufferStrategy bs;
	
	private Graphics g;
	
	private boolean running;
	
	private LargeTriangle[][] largeTriangles = new LargeTriangle[30][30];
	
	private MediumTriangle[][] mediumTriangles = new MediumTriangle[50][50];
	
	private SmallTriangle[][] smallTriangles = new SmallTriangle[100][100];
	
	private Point currentPos = new Point(0, 0);
	
	private Color color = null;
	
	private ArrayList<Color> colorRange = null;
	
	private Triangle lastTriangleChanged = null;
	
	private Brush brush = Brush.MEDIUM;
	
	public Main(){
		
		thread = new Thread(this);
		
		running = true;
		
		for(int x = 0; x < largeTriangles.length; x++){
			
			for(int y = 0; y < largeTriangles[0].length; y++){
				
				largeTriangles[x][y] = new LargeTriangle(x, y, Color.white);
				
			}
			
		}
		
		for(int x = 0; x < mediumTriangles.length; x++){
			
			for(int y = 0; y < mediumTriangles[0].length; y++){
				
				mediumTriangles[x][y] = new MediumTriangle(x, y, Color.white);
				
			}
			
		}
		
		for(int x = 0; x < smallTriangles.length; x++){
			
			for(int y = 0; y < smallTriangles[0].length; y++){
				
				smallTriangles[x][y] = new SmallTriangle(x, y, Color.white);
				
			}
			
		}
		
	}
	
	private Color randColor(){
		
		int r = (int)(Math.random() * 255);
		
		int g = (int)(Math.random() * 255);
		
		int b = (int)(Math.random() * 255);
		
		return new Color(r, g, b);
		
	}
	
	private Color between(Color c1, Color c2){
		
		int rdiff = Math.abs(c1.getRed() - c2.getRed());
		
		int gdiff = Math.abs(c1.getGreen() - c2.getGreen());
		
		int bdiff = Math.abs(c1.getBlue() - c2.getBlue());
		
		int r = min(c1.getRed(), c2.getRed()) + (int)(Math.random() * rdiff);
		
		int g = min(c1.getGreen(), c2.getGreen()) + (int)(Math.random() * gdiff);
		
		int b = min(c1.getBlue(), c2.getBlue()) + (int)(Math.random() * bdiff);
		
		return new Color(r, g, b);
		
	}
	
	private int min(int x, int y){
		
		return x < y ? x : y;
		
	}
	
	public void paint(Graphics g){
		
		if(bs == null){
			
			this.createBufferStrategy(2);
			
			this.addMouseListener(this);
			
			this.addMouseMotionListener(this);
			
			bs = this.getBufferStrategy();
			
			this.g = bs.getDrawGraphics();
			
			this.setBackground(Color.white);
			
			thread.start();
			
		}
		
	}
	
	private void tick(){
		
		
		
	}
	
	private void render(){
		
		g.setColor(Color.white);
		
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(int x = 0; x < largeTriangles.length; x++){
			
			for(int y = 0; y < largeTriangles[0].length; y++){
				
				if(largeTriangles[x][y] != null){
					
					largeTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
		for(int x = 0; x < mediumTriangles.length; x++){
			
			for(int y = 0; y < mediumTriangles[0].length; y++){
				
				if(mediumTriangles[x][y] != null){
					
					mediumTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
		for(int x = 0; x < smallTriangles.length; x++){
			
			for(int y = 0; y < smallTriangles[0].length; y++){
				
				if(smallTriangles[x][y] != null){
					
					smallTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
	}
	
	private void renderToScreen(){
		
		bs.show();
		
	}
	
	@Override
	public void run(){
		
		while(running){
			
			tick();
			
			render();
			
			renderToScreen();
			
			try{
				
				Thread.sleep(10);
				
			}
			catch(InterruptedException e){
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(brush == Brush.LARGE){
			
			for(int x = 0; x < largeTriangles.length; x++){
				
				for(int y = 0; y < largeTriangles.length; y++){
					
					if(largeTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(largeTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = largeTriangles[x][y];
							
							if(e.getButton() == MouseEvent.BUTTON1){
								
								if(colorRange == null){
									
									largeTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									largeTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(e.getButton() == MouseEvent.BUTTON3){
								
								largeTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		else if(brush == Brush.MEDIUM){
			
			for(int x = 0; x < mediumTriangles.length; x++){
				
				for(int y = 0; y < mediumTriangles.length; y++){
					
					if(mediumTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(mediumTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = mediumTriangles[x][y];
							
							if(e.getButton() == MouseEvent.BUTTON1){
								
								if(colorRange == null){
									
									mediumTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									mediumTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(e.getButton() == MouseEvent.BUTTON3){
								
								mediumTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		else if(brush == Brush.SMALL){
			
			for(int x = 0; x < smallTriangles.length; x++){
				
				for(int y = 0; y < smallTriangles.length; y++){
					
					if(smallTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(smallTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = smallTriangles[x][y];
							
							if(e.getButton() == MouseEvent.BUTTON1){
								
								if(colorRange == null){
									
									smallTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									smallTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(e.getButton() == MouseEvent.BUTTON3){
								
								smallTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		lastTriangleChanged = null;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setColor(Color color){
		
		this.color = color;
		
		colorRange = null;
		
	}
	
	public void setColorRange(ArrayList<Color> range){
		
		this.colorRange = range;
		
		color = null;
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(brush == Brush.LARGE){
			
			for(int x = 0; x < largeTriangles.length; x++){
				
				for(int y = 0; y < largeTriangles.length; y++){
					
					if(largeTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(largeTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = largeTriangles[x][y];
							
							if(SwingUtilities.isLeftMouseButton(e)){
								
								if(colorRange == null){
									
									largeTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									largeTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(SwingUtilities.isRightMouseButton(e)){
								
								largeTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		else if(brush == Brush.MEDIUM){
			
			for(int x = 0; x < mediumTriangles.length; x++){
				
				for(int y = 0; y < mediumTriangles.length; y++){
					
					if(mediumTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(mediumTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = mediumTriangles[x][y];
							
							if(SwingUtilities.isLeftMouseButton(e)){
								
								if(colorRange == null){
									
									mediumTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									mediumTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(SwingUtilities.isRightMouseButton(e)){
								
								mediumTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		else if(brush == Brush.SMALL){
			
			for(int x = 0; x < smallTriangles.length; x++){
				
				for(int y = 0; y < smallTriangles.length; y++){
					
					if(smallTriangles[x][y].contains(e.getX(), e.getY())){
						
						if(smallTriangles[x][y] != lastTriangleChanged){
							
							lastTriangleChanged = smallTriangles[x][y];
							
							if(SwingUtilities.isLeftMouseButton(e)){
								
								if(colorRange == null){
									
									smallTriangles[x][y].color = this.color == null ? randColor() : this.color;
									
								}
								else{
									
									smallTriangles[x][y].color = between(colorRange.get(0), colorRange.get(1));
									
								}
								
							}
							else if(SwingUtilities.isRightMouseButton(e)){
								
								smallTriangles[x][y].color = Color.white;
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public void setBrush(Brush brush){
		
		this.brush = brush;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getLogoBounds(){
		
		int minx = 999999, miny = 999999;
		
		int maxx = 0, maxy = 0;
		
		for(int x = 0; x < smallTriangles.length; x++){
			
			for(int y = 0; y < smallTriangles[0].length; y++){
				
				if(smallTriangles[x][y].getRealX() < minx && smallTriangles[x][y].color != Color.white){
					
					minx = smallTriangles[x][y].getRealX();
					
				}
				
				if(smallTriangles[x][y].getRealY() < miny && smallTriangles[x][y].color != Color.white){
					
					miny = smallTriangles[x][y].getRealY();
					
				}
				
				if(smallTriangles[x][y].getRealXWidth() > maxx && smallTriangles[x][y].color != Color.white){
					
					maxx = smallTriangles[x][y].getRealXWidth();
					
				}
				
				if(smallTriangles[x][y].getRealYHeight() > maxy && smallTriangles[x][y].color != Color.white){
					
					maxy = smallTriangles[x][y].getRealYHeight();
					
				}
				
			}
			
		}
		
		for(int x = 0; x < mediumTriangles.length; x++){
			
			for(int y = 0; y < mediumTriangles[0].length; y++){
				
				if(mediumTriangles[x][y].getRealX() < minx && mediumTriangles[x][y].color != Color.white){
					
					minx = mediumTriangles[x][y].getRealX();
					
				}
				
				if(mediumTriangles[x][y].getRealY() < miny && mediumTriangles[x][y].color != Color.white){
					
					miny = mediumTriangles[x][y].getRealY();
					
				}
				
				if(mediumTriangles[x][y].getRealXWidth() > maxx && mediumTriangles[x][y].color != Color.white){
					
					maxx = mediumTriangles[x][y].getRealXWidth();
					
				}
				
				if(mediumTriangles[x][y].getRealYHeight() > maxy && mediumTriangles[x][y].color != Color.white){
					
					maxy = mediumTriangles[x][y].getRealYHeight();
					
				}
				
			}
			
		}
		
		for(int x = 0; x < largeTriangles.length; x++){
			
			for(int y = 0; y < largeTriangles[0].length; y++){
				
				if(largeTriangles[x][y].getRealX() < minx && largeTriangles[x][y].color != Color.white){
					
					minx = largeTriangles[x][y].getRealX();
					
				}
				
				if(largeTriangles[x][y].getRealY() < miny && largeTriangles[x][y].color != Color.white){
					
					miny = largeTriangles[x][y].getRealY();
					
				}
				
				if(largeTriangles[x][y].getRealXWidth() > maxx && largeTriangles[x][y].color != Color.white){
					
					maxx = largeTriangles[x][y].getRealXWidth();
					
				}
				
				if(largeTriangles[x][y].getRealYHeight() > maxy && largeTriangles[x][y].color != Color.white){
					
					maxy = largeTriangles[x][y].getRealYHeight();
					
				}
				
			}
			
		}
		
		return new Rectangle(minx, miny, maxx - minx, maxy - miny);
		
	}
	
	public BufferedImage getLogo(){
		
		int xoffs = 50;
		
		int yoffs = 50;
		
		Rectangle bounds = getLogoBounds();
		
		BufferedImage logo = new BufferedImage(bounds.width + xoffs * 2, bounds.height + yoffs * 2, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D)logo.getGraphics();
		
		g.translate(-bounds.x + xoffs, -bounds.y + yoffs);
		
		for(int x = 0; x < largeTriangles.length; x++){
			
			for(int y = 0; y < largeTriangles[0].length; y++){
				
				if(largeTriangles[x][y] != null){
					
					largeTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
		for(int x = 0; x < mediumTriangles.length; x++){
			
			for(int y = 0; y < mediumTriangles[0].length; y++){
				
				if(mediumTriangles[x][y] != null){
					
					mediumTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
		for(int x = 0; x < smallTriangles.length; x++){
			
			for(int y = 0; y < smallTriangles[0].length; y++){
				
				if(smallTriangles[x][y] != null){
					
					smallTriangles[x][y].render(g);
					
				}
				
			}
			
		}
		
		return logo;
		
	}
	
}
