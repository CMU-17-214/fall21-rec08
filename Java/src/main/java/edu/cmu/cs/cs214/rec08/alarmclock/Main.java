package edu.cmu.cs.cs214.rec08.alarmclock;

import java.time.LocalDateTime;

import javax.swing.SwingUtilities;

/**
 * Creates an alarm that goes off in 10 seconds.
 */
public class Main {

  /**
   * Creates an alarm clock set to go off in three seconds, with three
   * listeners. Each listener has a different behavior.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(Main::createAndShowAlarmClockGUI);
  }

  public static void createAndShowAlarmClockGUI() {
    // Launches an alarm window when the alarm goes off
    AlarmListener alarmWindowListener = new AlarmWindow();

    // Makes a beeping sound when the alarm goes off
    AlarmListener beeperListener = new Beeper();

    // Pretends to check processes when alarm goes off
    AlarmListener watchDogListener = new ReliabilityWatchDog();

    // Calculate the time 10 seconds from now
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime alarmTime = now.plusSeconds(10);
    System.out.println("Time now is " + now);
    System.out.println("Alarm set to go off at " + alarmTime);

    // Create a new alarm clock and set the alarm
    AlarmClock alarmClock = new AlarmClock(alarmTime);

    // Add all the listeners to the alarm clock
    alarmClock.addAlarmListener(alarmWindowListener);
    alarmClock.addAlarmListener(beeperListener);
    alarmClock.addAlarmListener(watchDogListener);

    // Start the alarm clock
    alarmClock.start();
  }
}
