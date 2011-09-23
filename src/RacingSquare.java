
import acm.graphics.*;
import acm.util.RandomGenerator;
import java.awt.*;

import java.util.concurrent.Semaphore;

public class RacingSquare extends GRect implements Runnable {

	public RacingSquare(int index, boolean[] finished) {
		super(SIZE,SIZE);
		setFilled(true);
		myIndex = index;
		finishers = finished;
	}

	public void run() {
		// have not finished when we start
		finishers[myIndex] = false;

		// run the race
		for (int i = 0; i < 100; i++) {
			pause(rgen.nextInt(50,50));
			move(STEP,0);
		}

		// watch out for photo finishers! (BUGGY)

		int count = 0;
		for (int i = 0; i < finishers.length; i++) {
			if (finishers[i]) count++;
		}

		// take some time to do a victory dance
		pause(50);

		// mark myself as having finished.
		finishers[myIndex] = true;

		// if no one finished before me, then I win! (turn red)
		if (count == 0) {
			setColor(Color.RED);

		}

	}

	private static final double SIZE = 30;
	private static final double STEP = 5;

	private int myIndex;
	private boolean[] finishers;
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
