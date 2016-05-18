import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener{
	
	public static final int WIDTH = 1600;
	
	public static final int HEIGHT = 1200;
	
	private Main main;
	
	private JButton chooseColor;
	
	private JButton colorRange;
	
	private JButton smallBrush;
	
	private JButton mediumBrush;
	
	private JButton largeBrush;
	
	private JButton export;
	
	public Window(){
		
		super("Logo Creator");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(WIDTH, HEIGHT);
		
		this.setResizable(false);
		
		Container contentPane = this.getContentPane();
		
		Main main = new Main();
		
		this.main = main;
		
		contentPane.add(main);
		
		chooseColor = new JButton("Change Color");
		
		chooseColor.addActionListener(this);
		
		colorRange = new JButton("Specify Color Range");
		
		colorRange.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(0, 3));
		
		buttonPanel.add(chooseColor);
		
		buttonPanel.add(colorRange);
		
		// Small brush button
		
		smallBrush = new JButton();
		
		ImageIcon smallBrushIcon = new ImageIcon();
		
		Image smallBrushImage = null;
		
		try{
			
			smallBrushImage = ImageIO.read(new File("res/smallbrush.png"));
			
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		smallBrushIcon.setImage(smallBrushImage);
		
		smallBrush.setIcon(smallBrushIcon);
		
		smallBrush.addActionListener(this);
		
		// Medium brush button
		
		mediumBrush = new JButton();
		
		ImageIcon mediumBrushIcon = new ImageIcon();
		
		Image mediumBrushImage = null;
		
		try{
			
			mediumBrushImage = ImageIO.read(new File("res/mediumbrush.png"));
			
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		mediumBrushIcon.setImage(mediumBrushImage);
		
		mediumBrush.setIcon(mediumBrushIcon);
		
		mediumBrush.addActionListener(this);
		
		// Large brush button
		
		largeBrush = new JButton();
		
		ImageIcon largeBrushIcon = new ImageIcon();
		
		Image largeBrushImage = null;
		
		try{
			
			largeBrushImage = ImageIO.read(new File("res/largebrush.png"));
			
		}
		catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		largeBrushIcon.setImage(largeBrushImage);
		
		largeBrush.setIcon(largeBrushIcon);
		
		largeBrush.addActionListener(this);
		
		// Brush button panel
		
		JPanel brushPanel = new JPanel();
		
		brushPanel.setLayout(new GridLayout(3, 0));
		
		brushPanel.add(smallBrush);
		
		brushPanel.add(mediumBrush);
		
		brushPanel.add(largeBrush);
		
		// Export button
		
		export = new JButton("Export Logo");
		
		export.addActionListener(this);
		
		buttonPanel.add(export);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		contentPane.add(brushPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		
		new Window();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chooseColor){
			
			new ColorChooser(main).showDialog();
			
		}
		else if(e.getSource() == colorRange){
			
			ArrayList<Color> colors = new ArrayList<Color>();
			
			ColorCallback callback = new ColorCallback(){
				
				public void setColor(Color color){
					
					colors.add(color);
					
					if(colors.size() == 2){
						
						main.setColorRange(colors);
						
					}
					
				}
				
			};
			
			new ColorChooser(callback).showDialog();
			
			new ColorChooser(callback).showDialog();
			
		}
		else if(e.getSource() == smallBrush){
			
			main.setBrush(Brush.SMALL);
			
		}
		else if(e.getSource() == mediumBrush){
			
			main.setBrush(Brush.MEDIUM);
			
		}
		else if(e.getSource() == largeBrush){
			
			main.setBrush(Brush.LARGE);
			
		}
		else if(e.getSource() == export){
			
			JFileChooser chooser = new JFileChooser();
			
			int result = chooser.showSaveDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION){
				
				BufferedImage logo = main.getLogo();
				
				try {
					
					ImageIO.write(logo, "PNG", chooser.getSelectedFile());
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		}
		
	}
	
}
