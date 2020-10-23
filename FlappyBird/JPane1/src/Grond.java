import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grond {
	int x;
	int y;
	int width;
	int height;
	BufferedImage ground;
	public Grond() {
		x=0;
		y=500;
		try {
			ground=ImageIO.read(getClass().getResourceAsStream("ground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void move() {
		x--;
		if(x<-300)
		{
			x=0;
		}
	}
}
