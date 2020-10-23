import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Blast {

	
	int x;
	int y;
	int i=0;
	BufferedImage blastImage[];
	BufferedImage blastImageShow;
	
	public Blast(int x,int b)
	{
		this.x=x;
		this.y=y;
		blastImage= new BufferedImage[10];
		
    	try {
			blastImageShow=ImageIO.read(getClass().getResourceAsStream("0_.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	for(int i=0;i<10;i++) {
    	try {
    			blastImage[i]=ImageIO.read(getClass().getResourceAsStream(i+"_"+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	}
	

	public void player()
	{
		if(blastImageShow!=null)
		{
			i++;
			blastImageShow=blastImage[i/5];
		}
		if(i>48)
			blastImageShow=null;
	}
}
