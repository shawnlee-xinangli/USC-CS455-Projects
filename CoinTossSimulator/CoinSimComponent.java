import java.awt.*;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
This component draws three Bars.
*/
public class CoinSimComponent extends JComponent {
    //Construct the CoinTossSimulator class nad make it private.
    private static CoinTossSimulator sim = new CoinTossSimulator();
	private int n;

	public CoinSimComponent(int n) {
		this.n = n;
	}
    public void paintComponent(Graphics g){
		int x = getWidth()/4;
		int bw = 100;
		int vb = 50;
		int bottom = getHeight()-vb;
		double scale;

    	int bar1Left = x - bw/2;
      	int bar2Left = 2*x - bw/2;
      	int bar3Left = 3*x - bw/2;
      	
      	sim.run(n);

      	int numOfTrials = sim.getNumTrials();
      	int numOfTwoHeads = sim.getTwoHeads();
      	int numOfOneHeadTails = sim.getHeadTails();
      	int numOfTwoTails = sim.getTwoTails();
      	
      	int ratio1 = (int) (((double) numOfTwoHeads)/numOfTrials * 100);
      	int ratio2 = (int) (((double) numOfOneHeadTails)/numOfTrials * 100);
  		int ratio3 = (int) (((double) numOfTwoTails)/numOfTrials * 100);

      	Graphics2D g2 = (Graphics2D) g;

        String label1 = "Two Heads: " + numOfTwoHeads +" (" + ratio1 + "%)";
        String label2 = "A Head and a Tail: " + numOfOneHeadTails + " (" + ratio2 + "%)";
        String label3 = "Two Tails: " + numOfTwoTails + " (" + ratio3 + "%)";


      	Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(label1, context);
        int heightOfLabel = (int) labelBounds.getHeight();


        scale = ((double) (getHeight() - 2 *vb - heightOfLabel)) / numOfTrials;
        
        int bar1Height = numOfTwoHeads;
        int bar2Height = numOfOneHeadTails;
        int bar3Height = numOfTwoTails;

      	Bar b1 = new Bar(bottom,bar1Left,bw,bar1Height,scale,Color.RED,label1);
      	Bar b2 = new Bar(bottom,bar2Left,bw,bar2Height,scale, Color.GREEN,label2);
      	Bar b3 = new Bar(bottom,bar3Left,bw,bar3Height,scale,Color.BLUE,label3);

      	b1.draw(g2);
      	b2.draw(g2);
      	b3.draw(g2);      
   }
}
