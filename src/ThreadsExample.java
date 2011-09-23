import acm.program.*;
import acm.graphics.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ThreadsExample extends GraphicsProgram {

	public void run() {
		racers = new RacingSquare[NUM_RACERS];
		finished = new boolean[NUM_RACERS];
		threads = new Thread[NUM_RACERS];
		
		// finish line
		add(new GLine(510,0,510,400));
		
		add(new JButton("Start!"), SOUTH);
		addActionListeners();
		validate();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Start!")) {
			for(int i = 0; i < NUM_RACERS; i++) {
				//remove the racers from a previous race, if previously existed
				if (racers[i] != null) {
					remove(racers[i]);
				}
				racers[i] = new RacingSquare(i, finished);
				add(racers[i], 10, 10 + (40 * i));

				// send the new racer along the page
				// (on a seperate thread)
				threads[i] = new Thread(racers[i]);
				threads[i].start();
			}
		}
	}
	
	private static final int NUM_RACERS = 10;
	
	private RacingSquare[] racers;
	private boolean[] finished;
	private Thread[] threads;
	
}
