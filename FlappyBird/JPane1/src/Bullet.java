import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Bullet {

	int x,y;		//��ʼ����
	int speed=2;		//�ӵ��ٶ�
	double width;
	double height;
	BufferedImage bullet;
	BufferedImage BulletImageShow;
	
	public Bullet(int a,int b)
	{
		x = a;
		y = b;
		try {
			bullet=ImageIO.read(getClass().getResourceAsStream("bullet.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		width = bullet.getWidth();
		height = bullet.getHeight();
	}
	public void moveRight()
	{
		x = x + 10;
	}
	public void moveLeft()
	{
		x = x - 10;
	}
	
	public void distroy()
	{
		bullet=null;
	}
}
