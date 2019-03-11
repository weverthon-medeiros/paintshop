package my.challenge.paintshop;

import my.challenge.paintshop.exceptions.InvalidParameterException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static my.challenge.paintshop.util.Constants.*;

/**
 * Created by weverthonmedeiros on 09/03/2019.
 */
public class PaintShop {

    private int numColors;
    private List<Customer> customers;


    /**
     * Reads and parses the file to Customers' objects
     * @param file {code File} to be read
     * @throws IOException
     * @throws InvalidParameterException
     */
    public PaintShop(File file) throws IOException, InvalidParameterException {
        customers = new ArrayList<>();
        parseFile(file);
    }

    /**
     * Parses the file to a {code PaintShop}. It will generate the customers and fill them with the colors.
     * @param file {code File} to be read
     * @throws IOException
     * @throws InvalidParameterException
     */
    private void parseFile(File file) throws IOException, InvalidParameterException {
        boolean isFirstLine = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    numColors = Integer.parseInt(line.trim());
                    isFirstLine = false;
                } else {
                    customers.add(parseCustomer(line));
                }
            }
        }
    }

    /**
     * Reads the line of the file;
     * Generates the customer;
     * Fills the customer's colors.
     *
     * @param line to be read
     * @return a {@link Customer} filled with the colors
     * @throws InvalidParameterException When the line is {@code null} or its length is less than three (3).
     */
    private Customer parseCustomer(String line) throws InvalidParameterException {
        if(line == null || line.length() < 3) {
            throw new InvalidParameterException("The lines should contain at least one color");
        }

        String[] colors = line.split(SEPARATOR);
        /* as the array colors has the color number and the finish, the number of colors for the customer is half the
         * length of the array.
         */
        Customer customer = new Customer(colors.length/2);
        for(int i = 0; i < colors.length; i+=2) {
            //Will parse the color number to Integer and the finish to char
            customer.addColor(Integer.valueOf(colors[i]), colors[i+1].charAt(0));
        }
        return customer;
    }

    /**
     * Mixes the colors. The process
     * <ol>
     *     <li>Starts with all colors as GLOSS</li>
     *     <li>Iterates over customer and check if they are happy</li>
     *     <li>When a customer is not happy: <br />
     *         <ol>
     *             <li>If the customer has MATTE option, changes that color to MATTE and restart the process</li>
     *             <li>If the customer does NOT have MATTE option it means other customers have requested MATTE to
     *             all their options, and there is no solution</li>
     *         </ol>
     *     </li>
     * </ol>
     * @return a {@code String} with the result of the mix
     */
    public String mixColors() {
        char[] finalColors = getInitialMixColors(numColors);
        boolean isNoSolution = false;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if(!customer.isHappy(finalColors)) {
                if(customer.getMatteOption() != null) {
                    finalColors[customer.getMatteOption()] = MATTE;
                    //Restart the customer to analise if the change affected another customer
                    i = -1;
                } else {
                    isNoSolution = true;
                    break;
                }
            }
        }

        return printResult(finalColors, isNoSolution);
    }

    /**
     * Instantiates the initial {@code array} of colors, all colors will have GLOSS for now.
     *
     * @param numColors defines the length of the {@code array}
     * @return the initial {@code array}
     */
    private static char[] getInitialMixColors(int numColors) {
        char[] finalColors = new char[numColors];
        Arrays.fill(finalColors, GLOSS);
        return finalColors;
    }

    /**
     * Prints the result of the color mix.
     *
     * When a mix is possible: converts the final colors {@code array} in a {@code String} separated by space (" ").
     * Otherwise will return "No solution exists".
     *
     * @param finalColors
     * @return the mix result
     */
    private String printResult(char[] finalColors, boolean isNoSolution) {
        if(isNoSolution) {
            return NO_SOLUTION;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean firstTime = true;
            for (char finish: finalColors) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    sb.append(SEPARATOR);
                }
                sb.append(finish);
            }
            return sb.toString();
        }
    }
}
