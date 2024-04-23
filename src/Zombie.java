import java.awt.*;

public class Zombie {


        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public String name;                //holds the name of the hero
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public boolean isAlive;
        public Rectangle rec;
        public Zombie (int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        dx =10;
        dy =0;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos,ypos,width,height);
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);
    }
    public void Bounce() {
        //System.out.println(xpos+", "+ ypos);

        if (xpos < 0) {
            dx = -dx;


        }

        if (xpos > 1000-width) {
            dx = -dx;

            // width = width + 40;


        }

        if (ypos < 0) {
            dy = -dy;

        }
        if (ypos > 700-height) {
            dy = -dy;

        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);
    }
}

