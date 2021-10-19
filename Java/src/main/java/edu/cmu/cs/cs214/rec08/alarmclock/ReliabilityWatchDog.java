package edu.cmu.cs.cs214.rec08.alarmclock;

import java.time.LocalDateTime;

/**
 * Pretends to check system processes when the alarm goes off. All it does is
 * simply print a message to the console.
 */
public class ReliabilityWatchDog implements AlarmListener {

	@Override
	public void onAlarmEvent(AlarmClock source, LocalDateTime time) {
		String msg = String.format(
				"Alarm went off at %s. Checking processes... Done.",
				time);
		System.out.println(msg);
	}

}
