/** 
After constructor:
Number of trials [exp:0]: 0
Two-head tosses: 0
Two-tail tosses: 0
One-head one-tail tosses: 0
Tosses add up correctly? true

After run(1):
Number of trials [exp:1]: 1
Two-head tosses: 0
Two-tail tosses: 1
One-head one-tail tosses: 0
Tosses add up correctly? true

After run(10):
Number of trials [exp:11]: 11
Number of trials: 
Two-head tosses: 2
Two-tail tosses: 3
One-head one-tail tosses: 6
Tosses add up correctly? true

After run(100):
Number of trials [exp:111]: 111
Two-head tosses: 28
Two-tail tosses: 30
One-head one-tail tosses: 53
Tosses add up correctly? true

After reset:
Number of trials [exp:0]: 0
Two-head tosses: 0
Two-tail tosses: 0
One-head one-tail tosses: 0
Tosses add up correctly? true

After run(1000):
Number of trials [exp:1000]: 1000
Two-head tosses: 265
Two-tail tosses: 229
One-head one-tail tosses: 506
Tosses add up correctly? true
*/

public class CoinTossSimulatorTester {
	
	private static boolean correctness;
	private static CoinTossSimulator sim = new CoinTossSimulator();
	private static int testTimes = 0;
	
	public static void main(String[] args) {
		showResult();
		System.out.println();
		run(2);
		System.out.println();
		run(20);
		System.out.println();
		run(200);
		System.out.println();
		reset();
		System.out.println();
		run(2000);

	}
	
	public static void  run(int n) {
		sim.run(n);
		testTimes += n;
		showResult();
	}
	
	public static void showResult() {
		correctness = sim.getNumTrials() == sim.getTwoHeads()+sim.getHeadTails()+sim.getTwoTails();
		
		System.out.println("Number of trials [exp:"+ testTimes + "]: " + sim.getNumTrials());
		System.out.println("Two-head tosses: " + sim.getTwoHeads());
		System.out.println("Two-tail tosses: " + sim.getTwoTails());
		System.out.println("One-head one-tail tosses: " + sim.getHeadTails());
		System.out.println("Tosses add up correctly? " + correctness);

	}

	public static void reset() {
		sim.reset();
		correctness = false;
		testTimes = 0;
		showResult();
	}

	
}