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
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

// step 1:implement KeyListener
// step 1: imprement MouseListener

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

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
    public Image BearGPic;
    public Image backgroundPic;
    public Image BoulderPic;
    public Image MonkeyPic;


    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private BearG Bear1;
    public Rectangle startHitbox;
    public Boulder boulder;
    public boolean startGame;
    //make a new object of Astronaut called astro2
    public Boulder[] boulders;
    public Monkey[] monkeys;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    //todo: make astro and astro 2 go in different directions

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        startGame = false;
        setUpGraphics();

        //randomness
        //(int)(Math.random() *range) +offset
        //range 0-9
        //int randx = (int) (Math.random() * 10);
        //0.0001-0.9999
        //0.001 - 9.999
        //0-9

        //range to 1-10
        //randx = (int) (Math.random() * 10) + 1;
        // 0.001 - .999
        //0.01 - 9.99
        //0 - 9
        //1 - 10

        //range 1-1000
        //randx = (int) (Math.random() * 1000) + 1;

        //todo make a variable called randy and make the random range 1-700

        //int randy = (int) (Math.random() * 700) + 1;


        //variable and objects
        //create (construct) the objects needed for the game and load up
        BearGPic = Toolkit.getDefaultToolkit().getImage("BearG.png");//load the picture
        BoulderPic = Toolkit.getDefaultToolkit().getImage("Boulder.png");
        MonkeyPic = Toolkit.getDefaultToolkit().getImage("Monkey.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("background.png");

        Bear1 = new BearG(900, 600);
        Bear1.dx = 7;


        startHitbox = new Rectangle(100, 100, 100, 100);
        startGame = false;

        boulders = new Boulder[5];
        for (int w = 0; w < boulders.length; w++) {
            int randw = (int) (Math.random() * 200) + 50;
            int randv = (int) (Math.random() * 300) + 50;
            boulders[w] = new Boulder(randv, randw);
        }
        monkeys = new Monkey[7];
        for (int w = 0; w < monkeys.length; w++) {
            int randw = (int) (Math.random() * 650) + 1;
            int randv = (int) (Math.random() * 950) + 1;
            monkeys[w] = new Monkey(randv, randw);
        }
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


    public void moveThings() {
        //calls the move( ) code in the objects
        if (startGame == true) {
            Bear1.move();
            crashing();
            //todo: make the asteroids in asteroids move
            for (int c = 0; c < boulders.length; c++) {
                boulders[c].move();
            }
            for (int c = 0; c < monkeys.length; c++) {
                monkeys[c].move();
            }
        }
    }

    public void crashing() {
        // if the astros crash into eachother
      //  for (int b = 0; b < boulders.length; b++){
       //if (boulders[b].hitbox.intersects(Bear1.hitbox)) {
         //  System.out.println("Dead...");
         //  Bear1.isAlive = false;
        //}
       //}


        //if (asteroid1.hitbox.intersects(asteroid2.hitbox) && asteroid2.isCrashing == false) {
        // System.out.println("Asteroid Collision");
        //  asteroid2.height = asteroid2.height + 10;
        // asteroid2.isCrashing = true;


        //if (!asteroid1.hitbox.intersects(asteroid2.hitbox)) {
        //   asteroid2.isCrashing = false;

        //todo: print asteroid crash when ever one of the asteroids in the asteroids array hits eighter astro or astro2

    }



    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
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

        //step 2: add KeyListener to canvas
        canvas.addKeyListener(this);

        //step 2: add MouseListener to canvas
        canvas.addMouseListener(this);

        canvas.setBounds(0, 0, WIDTH, HEIGHT);
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
        if (startGame == true) {
            g.clearRect(0, 0, WIDTH, HEIGHT);
            //start of drawing things
            g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

            //draw the image of the astronaut
            if (Bear1.isAlive == true) {
                g.drawImage(BearGPic, Bear1.xpos, Bear1.ypos, Bear1.width, Bear1.height, null);
            }

            for (int b = 0; b < boulders.length; b++) {
                if (boulders[b].isAlive == true) {
                    g.drawImage(BoulderPic, boulders[b].xpos, boulders[b].ypos, boulders[b].width, boulders[b].height, null);
                }
            }
            for (int b = 0; b < monkeys.length; b++) {
                if (monkeys[b].isAlive == true) {
                    g.drawImage(MonkeyPic, monkeys[b].xpos, monkeys[b].ypos, monkeys[b].width, monkeys[b].height, null);
                }
            }


            //g.drawRect(astro.hitbox`````.x, astro.hitbox.y, astro.hitbox.width, astro.hitbox.height);
            //end of drawing things



        }
        if (startGame == false) {
            g.setColor(Color.GREEN);
            g.fillRect(100, 100, 100, 100);
        }
            bufferStrategy.show();


        g.dispose();
    }

    //step 3: add methods



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == 38) { //up arrow
            System.out.println("Going Up");
            //astro.dy = -Math.abs(astro.dy);
            Bear1.isNorth = true;
        }
        if (e.getKeyCode() == 37) { //left arrow
            System.out.println("Going Left");
            //astro.dx = -Math.abs(astro.dy);
            Bear1.isWest = true;
        }
        if (e.getKeyCode() == 39) { //right arrow
            System.out.println("Going Right");
            //astro.dx = Math.abs(astro.dy);
            Bear1.isEast= true;
        }
        if (e.getKeyCode() == 40) { //down arrow
            System.out.println("Going Down");
            //astro.dy = Math.abs(astro.dy);
            Bear1.isSouth = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("I stopped touching " + e.getKeyCode());
        if (e.getKeyCode() == 38) { // 38 is up arrow
            System.out.println("Not going Up");
            Bear1.isNorth = false;
        }
        if (e.getKeyCode() == 37) { // 37 is left arrow
            System.out.println("Not going Left");
            Bear1.isEast = false;
        }
        if (e.getKeyCode() == 39) { // 39 is right arrow
            System.out.println("Not going Right");
            Bear1.isWest = false;
        }
        if (e.getKeyCode() == 40) { // 40 is down arrow
            System.out.println("Not going Down");
            Bear1.isSouth = false;

        }


    }
    //step 3: add methods

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint());
        Rectangle pointHitbox = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (pointHitbox.intersects(startHitbox)) {
            startGame = true;
            System.out.println("Start Game");
        }
        for (int b = 0; b < boulders.length; b++) {
            if (pointHitbox.intersects(boulders[b].hitbox)) {
                boulders[b].isAlive = false;
                System.out.println("Asteroid has been destroyed");
            }

        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered the Screen");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited the Screen");
    }

    }