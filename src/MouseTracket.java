import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 * A Java class that tracks the mouse cursor's position on the screen
 * and outputs the RGB value of the pixel it is pointing to every second.
 * This class uses the java.awt.Robot and java.awt.MouseInfo APIs.
 */
public class MouseTracket {

    /**
     * The main method to execute the MouseTracker program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Create a Robot instance. This is required to capture screen pixels.
            Robot robot = new Robot();
            System.out.println("Starting mouse tracker. Press Ctrl+C to stop.");

            // Loop indefinitely to continuously track the mouse.
            while (true) {
                // Get the current location of the mouse cursor on the screen.
                Point mousePosition = MouseInfo.getPointerInfo().getLocation();

                // Get the Color of the pixel at the mouse's current coordinates.
                Color pixelColor = robot.getPixelColor(mousePosition.x, mousePosition.y);

                // Extract the individual red, green, and blue components.
                int red = pixelColor.getRed();
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();

                // Print the mouse coordinates and the RGB values to the console.
                System.out.println("Mouse Position: (" + mousePosition.x + ", " + mousePosition.y + ") | " +
                        "RGB: (" + red + ", " + green + ", " + blue + ")");

                // Pause the program for 1000 milliseconds (1 second) before the next check.
                Thread.sleep(1000);
            }
        } catch (AWTException e) {
            // This exception occurs if the platform configuration does not allow
            // low-level input control or screen capture.
            System.err.println("AWTException: Robot class could not be instantiated. " +
                    "Make sure you have a graphical environment and appropriate permissions.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            // This exception is thrown when a thread is waiting, sleeping, or
            // otherwise occupied, and the thread is interrupted.
            System.err.println("InterruptedException: The program was interrupted.");
            e.printStackTrace();
        }
    }
}
