import javax.swing.JFrame;
import java.util.Scanner;
public class CoinSimViewer {

	public static void main(String[] args) {

            System.out.print("Enter number of trials: ");
            Scanner in = new Scanner(System.in);
            int n =  in.nextInt();

            // Error check for n less than or equal to 0.
            while(n<=0) {
            System.out.println("ERROR: Number entered must be greater than 0.");
            System.out.print("Enter number of trials: ");
            in = new Scanner(System.in);
             n =  in.nextInt();
            }
            
            // If n is positive, run(n).
            JFrame frame = new JFrame();

            frame.setSize(800, 500);
            frame.setTitle("CoinSim");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CoinSimComponent component = new CoinSimComponent(n);
            frame.add(component);

            frame.setVisible(true);
      
      }

	
}