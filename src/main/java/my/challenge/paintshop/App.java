package my.challenge.paintshop;

import my.challenge.paintshop.exceptions.InvalidParameterException;

import java.io.File;
import java.io.IOException;

/**
 * Represents the App which will run the Paint Shop
 *
 * Created by weverthonmedeiros on 09/03/2019.
 */
public class App 
{
    public static void main( String[] args ) {
        if (args.length != 1) {
            System.err.println("Arguments invalid. Please provide the path for the file.");
            System.exit(1);
        } else {
            File file = new File(args[0]);
            try {
                PaintShop paintShop = new PaintShop(file);
                System.out.println(paintShop.mixColors());
            } catch (IOException | InvalidParameterException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
