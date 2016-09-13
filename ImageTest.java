import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.sun.prism.Image;

class Canvas extends JComponent{
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		String file = "/Users/berny/Downloads/too-cute-doggone-it-video-playlist.jpg";
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2d.drawImage(img, 0, 0, this);
		
		g2d.finalize();
	}
}
public class ImageTest {
	public void main(String[] args){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0,0,720,720);
		window.getContentPane().add(new Canvas());
		window.setVisible(true);
	}
}
