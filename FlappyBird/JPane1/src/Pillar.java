import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pillar {
    int x;
    int y;
    int width;
    int height;
    BufferedImage pillar;
    int Xdistence;//¼ä¾à
    int Ydistence;
    
    public Pillar(int n) {
    	try {
			pillar=ImageIO.read(getClass().getResourceAsStream("column.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	width=pillar.getWidth();
    	height=pillar.getHeight();
    	Xdistence=250;
    	Ydistence=143;
    	y=(int)(Math.random()*200-400);
    	x=(n-1)*Xdistence+150;
    	
    }
    
    public void move() {
    	x--;
    	if(x+80<0) {
    		x=432;
    		y=(int)(Math.random()*200-400);

    	}
    }

}
