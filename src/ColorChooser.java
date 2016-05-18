import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChooser implements ActionListener{
	
	private JFrame chooserWindow;
	
	private JColorChooser chooser;
	
	private JButton done, cancel;
	
	private Color color;
	
	private ColorCallback callback;
	
	public ColorChooser(ColorCallback callback){
		
		this.callback = callback;
		
	}
	
	public void showDialog(){
		
		chooserWindow = new JFrame("Pick a Color");
		
		chooserWindow.setSize(800, 600);
		
		chooserWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container contentPane = chooserWindow.getContentPane();
		
		done = new JButton("OK");
		
		done.addActionListener(this);
		
		cancel = new JButton("Cancel");
		
		cancel.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(0, 2));
		
		buttonPanel.add(done);
		
		buttonPanel.add(cancel);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		chooser = new JColorChooser();
		
		contentPane.add(chooser);
		
		chooserWindow.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancel){
			
			chooserWindow.dispatchEvent(new WindowEvent(chooserWindow, WindowEvent.WINDOW_CLOSING));
			
		}
		else if(e.getSource() == done){
			
			color = chooser.getColor();
			
			callback.setColor(color);
			
			chooserWindow.dispatchEvent(new WindowEvent(chooserWindow, WindowEvent.WINDOW_CLOSING));
			
		}
		
	}
	
}
