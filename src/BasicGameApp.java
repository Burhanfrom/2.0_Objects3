//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
	public Image zombPic;
	public Image Brainpic;
   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
	public Zombie zomb;
	//public Zombie[] azomb;
	public Brain[] abrain;
	public Image Background;

   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
		zombPic = Toolkit.getDefaultToolkit().getImage("zombie.png");
		Brainpic = Toolkit.getDefaultToolkit().getImage("brain.png");
		zomb = new Zombie (50,550);
		abrain = new Brain[][100];
		for (int i = 0; i < abrain.length; i++) {
			abrain[i] = new Brain ((int) (Math.random() * 1001), (int) (Math.random() * 700));
		}
		astro = new Astronaut(10,100);
		brain = new Brain(50,0);
		Background = Toolkit.getDefaultToolkit().getImage("arcade.png");
	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		astro.move();
		Brain.Bounce();
		for (int i = 0; i < abrain.length; i++) {
			abrain[i].Bounce();
			if(abrain[i].rec.intersects(brain.rec)){
				System.out.println("Crash");
			}
		}
		Brain.move();

	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
	  canvas.addKeyListener(this);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(Background,0, 0 ,1000, 700,  null);
		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
		//g.drawImage(zombPic, zomb.xpos, zomb.ypos, zomb.width, zomb.height, null);
	//	for (int i = 0; i < azomb.length; i++) {
		//g.drawImage(zombPic, azomb[i].xpos, azomb[i].ypos, azomb[i].width, azomb[i].height, null);
		}

		g.drawImage(Brainpic, brain.xpos, brain.ypos, brain.width, brain.height, null);
		for (int i = 0; i < abrain.length; i++) {
			g.drawImage(zombPic, abrain[i].xpos, abrain[i].ypos, abrain[i].width, abrain[i].height, null);

			g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==37){
			zomb.dx=-5;
			zomb.dy=0;
		}
		if(e.getKeyCode()==38) {
			zomb.dx = 0;
			zomb.dy = -5;
		}
		if(e.getKeyCode()==39){
			zomb.dx=5;
			zomb.dy=0;
		}
		if(e.getKeyCode()==40){
			zomb.dx=0;
			zomb.dy=5;
		}
		System.out.println("pressed key" + e.getKeyChar() + "with key code" + e.getKeyCode());
		if (e.getKeyCode() == 32) { //spacebar
			System.out.println("Space bar");
		}

	}


	@Override
	public void keyReleased(KeyEvent e) {

	}
}