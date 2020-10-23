import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Enemy {

	double x = 460+(int)(Math.random()*300);
	int y = (int)(Math.random()*400);
	int i = 0;
	int state=0;
	double width;
	double height;
	BufferedImage enemyImage[];
	BufferedImage enemyImageShow;
	BufferedImage enemyBoomImage[];
	BufferedImage enemyBoomImageShow;
	
	public Enemy(){
	 	enemyImage= new BufferedImage[8];
    	try {
			enemyImageShow=ImageIO.read(getClass().getResourceAsStream("0.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	for(int i=0;i<7;i++) {
    	try {
    			enemyImage[i]=ImageIO.read(getClass().getResourceAsStream(i+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
	
	public void move() {
		x=x-(double)(Math.random()*4);
    	i++;
    	enemyImageShow=enemyImage[i/16];
    	if(i>=110) {
    		i=0;
    	}
    	
    	}

	public void distory()
	{
		state=1;
		enemyImage = null;
	}
}
