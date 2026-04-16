import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class BearG {

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
    public boolean isControlable;
    public boolean isNorth;
    public boolean isEast;
    public boolean isWest;
    public boolean isSouth;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public BearG(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 7;
        dy = 7;
        width = 80;
        height = 80;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        isControlable = false;
        isNorth = false;
        isEast = false;
        isWest = false;
        isSouth = false;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {


        if(isNorth == true && isSouth == false) {
            dy = -4;
        }
        if (isNorth == true && isSouth == true){
            dy = 0;
        }
        if (isEast == true && isWest == false){
            dx = 4;
        }
        if (isEast == true && isWest == true){
            dx = 0;
        }
        if (isWest == true && isEast == false){
            dx = -4;
        }
        if (isWest == false && isEast == false){
            dx = 0;
        }
        if (isSouth == true && isNorth == false){
            dy = 4;
        }
        if (isSouth == false && isNorth == false){
            dy = 0;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos < 0) { //bounce off the left wall
            xpos = 0;
            dx = 0;
        }
        if (xpos > 1000-width) { //bounce off right wall
            xpos = 1000 - width;
            dx = 0;
        }
        if (ypos < 0) {    //bounce off top
            ypos = 0;
            dy = 0;
        }
        if (ypos > 700-height) { //bounce off bottom
            ypos = 700 - height;
            dy = 0;
        }


        hitbox = new Rectangle(xpos, ypos, width, height);



        //todo: bounce off all walls

    }

}

