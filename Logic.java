/* Armand Thierry Djappi,09.2019 */
package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;
import java.lang.*;
import mooc.vandy.java4android.diamonds.ui.OutputInterface;

import static java.lang.Math.abs;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        int matrix_height = size * 2 + 1; //height of the matrix (vertical)
        int matrix_width  = size * 2 + 2; // width of the matrix (horizontal)
        int pile          = -(size+1);

        //1- Drawing the rectangle which contains the diamond
        // looping from the vertical
        for(int i=1;i<=matrix_height;i++){
            pile++;
            // looping from horizontal
            for (int j=1;j<=matrix_width;j++) {
                // Displaying the "+" sign in four corners
                if((i == 1 || i == matrix_height) && (j == 1 || j == matrix_width))
                    mOut.print("+");
                // Displaying the "-" sign in horizontal (top and bottom) of the matrix except the corners
                else if((i == 1 || i == matrix_height) && !(j == 1 || j == matrix_width))
                    mOut.print("-");
                // Displaying the "|" sign in both vertical except the corners.
                else if(!(i == 1 || i == matrix_height) && (j == 1 || j == matrix_width))
                    mOut.print("|");
                //2- Drawing the diamond
                else {
                    shapeDiamond(size, i, j, pile);
                }
            }
            mOut.print("\n");
        }
    }

    //2- Drawing the diamond : passing the size, i,j,accumulator as variables
    public void shapeDiamond(int size, int i, int j, int pile){
        int diameter_length;  // diameter of the diamond
        if (pile <= 0){
            diameter_length    = i*2-2;
        } else {
            diameter_length    = (i-pile*2)*2-2;
        }
        // mathematical orientation
        int midpoint_diamond   = size + 1; // diamonf midle point
        int diamondleft_side   = midpoint_diamond - (diameter_length/2-1);
        int diamondright_side  = midpoint_diamond + (diameter_length/2);
        int top_side           = 1;
        int bottom_side        = size * 2 + 1;

        if (j >= diamondleft_side && j <= diamondright_side) {
            if (j == diamondleft_side || j == diamondright_side) {
                if (i < midpoint_diamond && i > top_side) {
                    if (j == diamondleft_side) {
                        mOut.print("/");
                    } else {
                        mOut.print("\\");
                    }
                } else if (i == midpoint_diamond) {
                    if (j == diamondleft_side) {
                        mOut.print("<");
                    } else {
                        mOut.print(">");
                    }
                } else if (i > midpoint_diamond && i < bottom_side) {
                    if (j == diamondleft_side) {
                        mOut.print("\\");
                    } else {
                        mOut.print("/");
                    }
                }
            } else {
                if (i % 2 == 0) {
                    mOut.print("=");
                } else {
                    mOut.print("-");
                }
            }
        } else {
            mOut.print(" ");   // print space
        }
    }
}
