package edu.cmu.cs.cs214.rec08.alarmclock;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A notification window which notifies the user that the alarm has gone off.
 */
public class AlarmWindow implements AlarmListener {

	private final JFrame frame;
	private final JLabel timeLabel;

	public AlarmWindow() {
		frame = new JFrame("Alarm Alert!");

		JPanel alarmPanel = new JPanel();
		alarmPanel.setLayout(new GridLayout(2, 1));

		timeLabel = new JLabel();

		JButton dismissButton = new JButton("Dismiss");
		dismissButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});

		alarmPanel.add(timeLabel);
		alarmPanel.add(dismissButton);

		frame.getContentPane().add(alarmPanel);

		frame.setSize(400, 200);
	}

	@Override
	public void onAlarmEvent(AlarmClock source, LocalDateTime time) {
		timeLabel.setText("ALARM WENT OFF AT " + time);
		frame.setVisible(true);
	}

}
