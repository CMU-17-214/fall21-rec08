package edu.cmu.cs.cs214.rec08.alarmclock;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An alarm clock that supports listeners that are triggered when the alarm goes
 * off.
 * 
 * The alarm clock has an alarm time and a current time. The alarm time is when
 * the alarm should go off. The current time is the time now that the system
 * tells it.
 */
public class AlarmClock {
  private final List<AlarmListener> alarmListeners; // The set of listeners
  private final LocalDateTime alarmTime; // The time the alarm should go off

  /** The thread that is run to update the time. */
  private final Thread updateTimeThread;

  public AlarmClock(LocalDateTime alarmTime) {
    this.alarmListeners = new ArrayList<>();
    this.alarmTime = alarmTime;

    updateTimeThread = new Thread(() -> {
      LocalDateTime currentTime = LocalDateTime.now();
      while (currentTime.isBefore(alarmTime)) {
        try {
          Thread.sleep(Duration.between(currentTime, alarmTime).toMillis());
        } catch (InterruptedException e) {
          System.err.println(("The alarm clock was interrupted."));
        }
        currentTime = LocalDateTime.now();
      }

      // Alarm has gone off, notify our listeners.
      for (AlarmListener al : alarmListeners) {
        al.onAlarmEvent(AlarmClock.this, alarmTime);
      }
    });
  }

  /**
   * Starts updating the AlarmClock's time in a new thread.
   */
  public void start() {
    updateTimeThread.start();
  }

  /**
   * Adds a listener which triggers some action when the alarm goes off.
   * 
   * @param lis
   *          The listener to add.
   */
  public void addAlarmListener(AlarmListener lis) {
    alarmListeners.add(lis);
  }

  /**
   * Notify all listeners that the alarm has gone off.
   * 
   * @param time
   *          The current time.
   */
  private void publishAlarmEvent(LocalDateTime time) {
    for (AlarmListener al : alarmListeners) {
      al.onAlarmEvent(this, time);
    }
  }
}
