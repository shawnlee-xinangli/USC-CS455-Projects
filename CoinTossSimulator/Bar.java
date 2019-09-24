// Name: 
// USC NetID:
// CS 455 PA1
// Fall 2019

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
   private int barXPos;
   private int barYPos;
   private int width;
   private int heightInPixels;
   private int left;
   private double scale;
   private int bottom;
   private String label;
   private int labelXPos;
   private int labelYPos;
   private Color color;
   private int barHeight;


   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {

         this.label = label;
         this.color = color;
         this.width = width;
         this.barHeight = barHeight;
         this.bottom = bottom;
         this.left = left;
         this.scale = scale;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
       Font font = g2.getFont();
       FontRenderContext context = g2.getFontRenderContext();
       Rectangle2D labelBounds = font.getStringBounds(this.label, context);
       int widthOfLabel = (int) labelBounds.getWidth();
       int heightOfLabel = (int) labelBounds.getHeight();

       heightInPixels = (int) Math.round(barHeight * scale); // calculate out the height of the bar in pixels
       labelXPos =  left + width/2 - widthOfLabel/2;// calculate out the x-coordinate of the basepoint of the label
       labelYPos = bottom;
       barXPos = left;
       barYPos = bottom - heightOfLabel - heightInPixels;// calculate out the y-coordinate of start point of the bar

       //First we draw the label.
       g2.drawString(label,labelXPos,labelYPos);
       //Then we set the color for the bar's body.
       g2.setColor(color);
       //Then we draw the body;
       Rectangle body = new Rectangle(barXPos,barYPos,width, heightInPixels);
       g2.fill(body);

       // Finally we change the color back to the black.
       g2.setColor(Color.BLACK);
      
   }
}
