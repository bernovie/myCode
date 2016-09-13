import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
/**
 * This class demonstrates how to load an Image from an external file
 */
public class LoadImageApp extends Component {
    public static final long serialVersionUID = 1234; 
    BufferedImage img;
    
    public void setAlpha(byte alpha) {       
        alpha %= 0xff; 
        for (int cx=0;cx<img.getWidth();cx++) {          
            for (int cy=0;cy<img.getHeight();cy++) {
                int color = img.getRGB(cx, cy);

                int mc = (alpha << 24) | 0x00ffffff;
                int newcolor = color & mc;
                img.setRGB(cx, cy, newcolor);            

            }

        }
    }
    
    public static void modAlpha(BufferedImage modMe, double modAmount) {
        //
    for (int x = 0; x < modMe.getWidth(); x++) {          
        for (int y = 0; y < modMe.getHeight(); y++) {
                //
            int argb = modMe.getRGB(x, y); //always returns TYPE_INT_ARGB
            int alpha = (argb >> 24) & 0xff;  //isolate alpha

            alpha *= modAmount; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
            alpha &= 0xff;      //keeps alpha in 0-255 range

            argb &= 0x00ffffff; //remove old alpha info
            argb |= (alpha << 24);  //add new alpha info
            modMe.setRGB(x, y, argb);            
        }
    }
}
    
    public void paint(Graphics g) {
    	for(int row = 0; row < img.getHeight(); row++){
    		for(int col = 0; col < img.getWidth(); col++){
    			Color customColor = new Color(img.getRGB(col, row));
    			int red = customColor.getRed();
    			int blue = customColor.getBlue();
    			int green = customColor.getGreen();
    			int alpha = customColor.getAlpha();
    			int argb = img.getRGB(col, row);
    			red *= 1;
    			red &= 0xff;
    			green *= 0.5;
    			green &= 0xff;
    			blue *= 0.5;
    			blue &= 0xff;
    			
    			alpha = alpha;
    			//alpha = (argb >> 24) & 0xff;
    			//alpha *= 1;
    			//alpha &= 0xff;
    			argb &= 0xff000000;
    			argb |= (red << 16) | (green << 8) | blue;
    			Color fcolor = new Color(red,green,blue,alpha);
    			int finalColor = (alpha << 24) | (red << 16) | (green << 8) | blue;
    			img.setRGB(col, row, argb);
    			
    		}
    	}
        g.drawImage(img, 0, 0, null);
    }
 
    public LoadImageApp(String file) {
       try {
           img = ImageIO.read(new File(file));
       } catch (IOException e) {
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    public static void main(String[] args) {
    	
        JFrame f = new JFrame("Load Image Sample");
             
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        LoadImageApp dog = new LoadImageApp("/Users/berny/Downloads/Scottish-Fold-Kater.png");
        f.add(dog);
        f.pack();
        f.setVisible(true);
    	//System.out.println(4 << 1);
    }
}

