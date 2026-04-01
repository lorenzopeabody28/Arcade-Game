import java.awt.*;

public class Monkey {
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isCrashing;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Monkey(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        int randw = (int) (Math.random() * 10) + 1;
        int randv = (int) (Math.random() * 10) + 1;
        int rand1 = (int) (Math.random() * 2) + 1;
        if (rand1 == 1) {
            dx = -randw;
        }
        else  {
            dx = randw;
        }
        int rand2 = (int) (Math.random() * 2) + 1;
        if (rand2 == 1) {
            dy = -randv;
        }
        else {
            dy = randv;
        }
        width = 50;
        height = 50;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        isCrashing = false;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if (ypos > 700){ // wrap when hit the bootom wall
            ypos = 0;
        }
        if (ypos < 0) { //wrap when hit the top wall
            ypos = 700;
        }
        if (xpos > 1000) { //wrap when hit the right wall
            xpos = 0;
        }
        if (xpos < 0) {  // wrap when hit left wall
            xpos = 1000;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);

        //todo: bounce off all walls


    }

}
