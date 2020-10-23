import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.text.Bidi;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.BoldAction;

public class MainFly extends JPanel {
Bird bird;//��2������,���棬״̬���÷�
Pillar pillar1;
Pillar pillar2;
Bullet bullet;
Enemy enemy1;
Enemy enemy2;
Enemy enemy3;
Enemy enemy4;
Blast blast;
Grond grond;
static int status;

int score=0;
public static final int BEFOR_GAME=1;
public static final int IN_GAME=2;
public static final int GAME_OVER=3;

BufferedImage background;
BufferedImage ground;
BufferedImage start;
BufferedImage gameover;

int x;
int y;
int width;
int height;

private	URL url ;
private URI uri;
private AudioClip ac;


public MainFly() {
	x=0;
	y=0;
	grond =new Grond();
	pillar1=new Pillar(1);
	pillar2=new Pillar(2);
	bird=new Bird();
	bullet = new Bullet(bird.x+20,bird.y+20);
	
	enemy1 = new Enemy();
	enemy2 = new Enemy();
	enemy3 = new Enemy();
	enemy4 = new Enemy();
	blast = new Blast(x,y);
	try {
		background=ImageIO.read(getClass().getResourceAsStream("bg.png"));
	} catch (IOException e) {

		e.printStackTrace();
	}
	width=background.getWidth();
	height=background.getHeight();
	try {
		start=ImageIO.read(getClass().getResourceAsStream("start.png"));
	} catch (IOException e) {

		e.printStackTrace();
	}
	try {
		gameover=ImageIO.read(getClass().getResourceAsStream("gameover.png"));
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	
	
	
	status=BEFOR_GAME;
}


public void paint(Graphics g) {
	super.paint(g);
	g.drawImage(background, x, y, null);
	g.drawImage(bullet.bullet, bullet.x, bullet.y, null);
	g.drawImage(enemy1.enemyImageShow,(int)enemy1.x,enemy1.y,null);
	g.drawImage(enemy2.enemyImageShow,(int)enemy2.x,enemy2.y,null);
	g.drawImage(enemy3.enemyImageShow,(int)enemy3.x,enemy3.y,null);
	g.drawImage(enemy4.enemyImageShow,(int)enemy4.x,enemy4.y,null);
	g.drawImage(pillar1.pillar, pillar1.x, pillar1.y, null);
	g.drawImage(pillar2.pillar, pillar2.x, pillar2.y, null);
	g.drawImage(grond.ground, grond.x, grond.y, null);
	g.drawImage(bird.birdImageshow, bird.x, bird.y, null);
	g.drawImage(blast.blastImageShow, x, y, null);
	
	Font font=new Font("", Font.BOLD, 20);
	g.setFont(font);
	g.setColor(Color.white);
	g.drawString("������"+score+"", 40, 20);
	g.drawString("level��"+bird.level+"", 280, 20);
	
	switch(status) {
	case BEFOR_GAME:
		g.drawImage(start, x, y, null);
		break;
	case GAME_OVER:
		g.drawImage(gameover, x, y, null);
		Font font1=new Font("", Font.BOLD, 25);
		g.setFont(font1);
		g.setColor(Color.red);
		g.drawString("���÷�����"+score+"", 130, 250);
		break;
	}

	
}


public void BGMPlayer()
{

	File f = new File("bgm.wav");
	try{
		uri=f.toURI();
		url = uri.toURL();
		ac= Applet.newAudioClip(url);
	    ac.loop();
//	    System.out.println("��ʼ��������");
	}catch(MalformedURLException e){
		e.printStackTrace();
	}
}

public void action() {
	
	MouseAdapter mous = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
			switch(status) {
			case BEFOR_GAME:
				
				status=IN_GAME;
				BGMPlayer();
				break;
			case IN_GAME:
				bird.v=30;
				
				break;
			case GAME_OVER:
				grond =new Grond();
		    	pillar1=new Pillar(1);
		    	pillar2=new Pillar(2);
//		    	pillar3=new Pillar(3);
		    	bird=new Bird();
		    	bullet = new Bullet(bird.x+20,bird.y+20);
		    	enemy1 = new Enemy();
		    	enemy2 = new Enemy();
		    	enemy3 = new Enemy();
		    	enemy4 = new Enemy();
		    	blast = new Blast(x,y);
		    	score=0;
				status=BEFOR_GAME;
				break;
			}
		}
		
	};
	addMouseListener(mous);
	
	
	while(true) {
	switch(status) {
	    case BEFOR_GAME:
	    	grond.move();
	    	bird.move();
	    	break;
	    case IN_GAME:
	    		
	    	grond.move();
	    	bird.move();
	    	bird.fly();
	    	
	    	pillar1.move();
	    	pillar2.move();
	    	
	    	bullet.moveRight();
	    	enemy1.move();
	//    	enemy1.enemyBoom();
	    	enemy2.move();
	//    	enemy2.enemyBoom();
	    	enemy3.move();
	//    	enemy3.enemyBoom();
	    	enemy4.move();

//	    	blast.player();
	    	break;
	    case GAME_OVER:
	    	break;
	}
	repaint();
	try {
		Thread.sleep(bird.time);//--------------�ӳ٣�����
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	
	
	if(bird.y==0) {   
		bird.life-=1;
	}
	
	if(bird.y>455) {    
		if(bird.life!=0) {
			bird.life-=1;
		}
		else
		{
			status=GAME_OVER;
		}
	}
	
	if(bird.x>pillar1.x-40 && bird.x<pillar1.x+40 ) {//-----------ײ������
		if(bird.y<pillar1.y+470 || bird.y>pillar1.y+690) {
			if(bird.life!=0) {
				bird.life-=1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	if(bird.x>pillar2.x-40 && bird.x<pillar2.x+40) {
		if(bird.y<pillar2.y+470 || bird.y>pillar2.y+690) {
			if(bird.life!=0) {
				bird.life=bird.life-1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	if(bird.x>enemy1.x-10 && bird.x<enemy1.x+20) {
		if(bird.y>enemy1.y-10 && bird.y<enemy1.y+40) {
			if(bird.life!=0) {
				bird.life=bird.life-1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	if(bird.x>enemy2.x-10 && bird.x<enemy2.x+20) {
		if(bird.y>enemy2.y-10 && bird.y<enemy2.y+40) {
			if(bird.life!=0) {
				bird.life=bird.life-1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	if(bird.x>enemy3.x-10 && bird.x<enemy3.x+20) {
		if(bird.y>enemy3.y-10 && bird.y<enemy3.y+40) {
			if(bird.life!=0) {
				bird.life=bird.life-1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	if(bird.x>enemy4.x-10 && bird.x<enemy4.x+20) {
		if(bird.y>enemy4.y-10 && bird.y<enemy4.y+40) {
			if(bird.life!=0) {
				bird.life=bird.life-1;
			}
			else
			{
				status=GAME_OVER;
			}
		}
	}
	
	
	if(enemy1.x<-20)
	{
		enemy1.distory();
		enemy1 = new Enemy();
	}
	if(enemy2.x<-20)
	{
		enemy2.distory();
		enemy2 = new Enemy();
	}
	if(enemy3.x<-20)
	{
		enemy3.distory();
		enemy3 = new Enemy();
	}
	if(enemy4.x<-20)
	{
		enemy4.distory();
		enemy4 = new Enemy();
	}
	if(bullet.x>440)
	{
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
	}
	if(bullet.x>pillar1.x-30 && bullet.x<pillar1.x+30) {
		if(bullet.y<pillar1.y+470 || bullet.y>pillar1.y+700){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
		}
	}
	if(bullet.x>pillar2.x-30 && bullet.x<pillar2.x+30) {
		if(bullet.y<pillar2.y+470 || bullet.y>pillar2.y+700){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
		}
	}
	if(bullet.x>enemy1.x && bullet.x<enemy1.x+20) {
		if(bullet.y>enemy1.y && bullet.y<enemy1.y+40){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
//			EnemyBoom(enemy1, enemy1.x, enemy1.y);
			
			enemy1.distory();
			enemy1 = new Enemy();
			score++;
		}
	}
	if(bullet.x>enemy2.x && bullet.x<enemy2.x+20) {
		if(bullet.y>enemy2.y && bullet.y<enemy2.y+40){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
//			EnemyBoom(enemy2, enemy2.x, enemy2.y);

			enemy2.distory();
			enemy2 = new Enemy();
			score++;
		}
	}
	if(bullet.x>enemy3.x && bullet.x<enemy3.x+20) {
		if(bullet.y>enemy3.y && bullet.y<enemy3.y+40){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
//			EnemyBoom(enemy3, enemy3.x, enemy3.y);

			enemy3.distory();
			enemy3 = new Enemy();
			score++;
		}
	}
	if(bullet.x>enemy4.x && bullet.x<enemy4.x+20) {
		if(bullet.y>enemy4.y && bullet.y<enemy4.y+40){
			bullet.distroy();
			bullet= new Bullet(bird.x+20,bird.y+20);
//			EnemyBoom(enemy3, enemy3.x, enemy3.y);
			enemy4.distory();
			enemy4 = new Enemy();
			score++;
		}
	}
	
	

	
	if(bird.x==pillar1.x+30 || bird.x==pillar2.x+30) {
		score++;
	}
	
	if(score==40) {
		bird.level=2;
    	bird.t=0.1;
    	bird.g=13;
    	bird.time=10;
	}
	if(score==80) {
		bird.level=3;
		bird.t=0.1;
		bird.g=15;
		bird.time=9;
	}
	}
}
	

public static void main(String[] args) {
	JFrame myFream=new JFrame();
	myFream.setSize(432, 644);
	myFream.setLocationRelativeTo(myFream);
	myFream.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	MainFly mainFly = new MainFly();
	myFream.add(mainFly);
	myFream.setVisible(true);
	mainFly.action();
}
}
	// TODO Auto-generated method stub