package my.challenge.paintshop;

import my.challenge.paintshop.exceptions.InvalidParameterException;

import static my.challenge.paintshop.util.Constants.GLOSSY;
import static my.challenge.paintshop.util.Constants.MATTE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weverthonmedeiros on 09/03/2019.
 */
public class Customer {

    private Integer matteOption;
    private List<Integer> glossyOptions;

    /**
     * Instantiate the {@code glassOptions} with the numOfColors (the worst case)
     * @param numColors number of colors for the customer
     */
    public Customer(int numColors) {
        glossyOptions = new ArrayList<>(numColors);
    }

    /**
     * Adds a color to the customer. Rules:
     * If the color is MATTE it will be stored in a different field (as the customer can have one only MATTE option)
     * Otherwise will be stored in a {code List}
     *
     * @param color the number of the color
     * @param finish the type of finish: MATTE or GLOSSY
     * @throws InvalidParameterException
     */
    public void addColor(Integer color, char finish) throws InvalidParameterException {
        if(MATTE == finish) {
            matteOption = getColorIndex(color);
        } else {
            glossyOptions.add(getColorIndex(color));
        }
    }

    /**
     * Returns the index of the color, so, color "1" has "0" as index.
     *
     * @param color to get the index
     * @return the index of the {@code color}
     * @throws InvalidParameterException when the color is invalid: {@code null} or zero.
     */
    private Integer getColorIndex(Integer color) throws InvalidParameterException {
        if(color == null || color == 0) {
            throw new InvalidParameterException("Invalid color, it should be not null and greater than 0");
        }
        return color - 1;
    }

    /**
     * Checks if the customer is Happy. The process:
     * <ol>
     *     <ul>If the customer has MATTE option, check it first (to avoid an iteration)</ul>
     *     <ul>Otherwise iterate over the GLOSS options and check them</ul>
     * </ol>
     * The index of the color is its position in the array.
     *
     * @param colors the colors to be checked
     * @return {@code true} when the customer is happy | {@code false} otherwise
     */
    public boolean isHappy(char[] colors) {
        if(matteOption != null && MATTE == colors[matteOption]) {
            return true;
        } else {
            return glossyOptions.stream().anyMatch(color -> GLOSSY == colors[color]);
        }
    }

    /**
     * Getter for the {@code MatteOption}
     * @return the index of the Matte option
     */
    public Integer getMatteOption() {
        return matteOption;
    }

}
