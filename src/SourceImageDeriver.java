import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class SourceImageDeriver {
	
	public static void fromSourceImage(BufferedImage image, Triangle[][] triangles){
		
		float xResize = image.getWidth() / (float)(triangles.length * (triangles[0][0].getRealXWidth() - triangles[0][0].getRealX()));
		
		float yResize = (image.getHeight() * 2) / (float)(triangles.length * (triangles[0][0].getRealYHeight() - triangles[0][0].getRealY()));
		
		for(int x = 0; x < triangles.length; x++){
			
			for(int y = 0; y < triangles[0].length; y++){
				
				try{
					
					Triangle current = triangles[x][y];
					
					Point center = current.getCenter();
					
					int clr = image.getRGB((int)(center.x * xResize), (int)(center.y * yResize));
					
					int red = (clr & 0x00ff0000) >> 16;
				  	int green = (clr & 0x0000ff00) >> 8;
				  	int blue = clr & 0x000000ff;
				  	
				  	triangles[x][y].color = new Color(red, green, blue);
				  	
				}
				catch(ArrayIndexOutOfBoundsException e){
					
					
					
				}
				
			}
			
		}
		
	}
	
}
