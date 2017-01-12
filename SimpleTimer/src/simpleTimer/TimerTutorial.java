package simpleTimer;


import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TimerTutorial extends JFrame {
	JLabel promptLabel, timerLabel;
	JTextField tf;
	JButton button;
	Timer timer;
	
	int counter;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void showWindow() {
		setVisible(true);
	}
	public void hideWindow() {
		setVisible(false);
	}
	
	public TimerTutorial() {
		setLayout(new GridLayout(8, 8, 5, 5));
		
		promptLabel = new JLabel("Enter minutes: ");
		add(promptLabel);
		
		tf = new JTextField();
		tf.setText("1");
		add(tf);
		
		button = new JButton("Start timing");
		add(button);
		
		timerLabel = new JLabel("Waiting....", SwingConstants.CENTER);
		add(timerLabel);
		
		event e = new event();
		button.addActionListener( e);
	}
	
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int count = (int) (Double.parseDouble(tf.getText()));
			count = count * 60;
			timerLabel.setText("Time left (minutes):" + (int)( count/60) 
					 + " seconds:" + count % 60 );
			
			TimeClass tc = new TimeClass(count);
			timer = new Timer(1000, tc);
			timer.start();
			hideWindow();
		}
	}

	
	public class TimeClass implements ActionListener {
		int counter;
		
		public TimeClass(int counter) {
			this.counter = counter;
		}
		public void actionPerformed(ActionEvent tc) {
			counter--;
			if (counter >= 1) {
				timerLabel.setText("Time left (minutes):" + (int)( counter/60) 
						 + " seconds:" + counter % 60 );
			} else {
				timer.stop();
				showWindow();
				timerLabel.setText("Done!");
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
	
}
