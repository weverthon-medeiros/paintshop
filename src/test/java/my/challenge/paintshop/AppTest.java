package my.challenge.paintshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import my.challenge.paintshop.exceptions.InvalidParameterException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * PaintShop tests
 */
public class AppTest 
{
    ClassLoader classLoader = getClass().getClassLoader();
    String filesFolder = "files" + File.separator;

    /**
     * Tests file not found
     */
    @Test
    public void testFileNotFound() {
        String result = null;
        try {
            File file = new File("FileDoesNotExist.txt");
            PaintShop paintShop = new PaintShop(file);
            fail();
        } catch (FileNotFoundException e) {
            //Expecting this
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests invalid file (PaintshopInvalid01.txt). A line is empty.
     * Should returns: The lines should contain at least one color
     */
    @Test
    public void testInvalidFile01() {
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopInvalid01.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            paintShop.mixColors();
            fail();
        } catch (InvalidParameterException e) {
            //Expecting this
            assertEquals("The lines should contain at least one color", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests invalid file (PaintshopInvalid02.txt). A color is "0".
     * Should returns: Invalid color, it should be not null and greater than 0
     */
    @Test
    public void testInvalidFile02() {
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopInvalid02.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            paintShop.mixColors();
            fail();
        } catch (InvalidParameterException e) {
            //Expecting this
            assertEquals("Invalid color, it should be not null and greater than 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the app using PaintshopNoSolution01.txt file
     */
    @Test
    public void testNoSolution01()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopNoSolution01.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("No solution exists", result);
    }

    /**
     * Tests the app using PaintshopNoSolution02.txt file
     */
    @Test
    public void testNoSolution02()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopNoSolution02.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("No solution exists", result);
    }

    /**
     * Tests the app using PaintshopValid01.txt file
     */
    @Test
    public void testValid01()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopValid01.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("G G G G M", result);
    }

    /**
     * Tests the app using PaintshopValid02.txt file
     */
    @Test
    public void testValid02()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopValid02.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("G M G M G", result);
    }

    /**
     * Tests the app using PaintshopValid03.txt file
     */
    @Test
    public void testValid03()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopValid03.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("M M", result);
    }

    /**
     * Tests the app using PaintshopValid04.txt file
     */
    @Test
    public void testValid04()
    {
        String result = null;
        try {
            File file = new File(classLoader.getResource(filesFolder + "PaintshopValid04.txt").toURI());
            PaintShop paintShop = new PaintShop(file);
            result = paintShop.mixColors();
        } catch (Exception e) {
            fail();
        }

        assertEquals("M M M", result);
    }
}
