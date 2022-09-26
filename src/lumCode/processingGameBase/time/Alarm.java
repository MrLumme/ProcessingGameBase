package lumCode.processingGameBase.time;

/**
 * Class to define the timing of an event. It is essentially a stop watch.
 * 
 * @author Lumme
 * @version 0.1
 * @since 02-08-2019
 */

public class Alarm {
	private long start;
	private long duration;
	private boolean done;
	private boolean active;
	private boolean paused;
	private long pauseTime;

	/**
	 * Constructor. sets done to be false and start to be 0. Duration is in
	 * milliseconds.
	 * 
	 * @param length
	 */

	public Alarm(long duration) {
		this.duration = duration;
		done = false;
		active = false;
		paused = false;
		pauseTime = 0;
	}

	/**
	 * Starts the alarm. Can also be used to restart the alarm.
	 */

	public void start() {
		start = TimeKeeper.getTime();
		done = false;
		active = true;
	}

	/**
	 * Stops the alarm. State of value 'done' is unchanged by this.
	 */

	public void stop() {
		start = 0;
		active = false;
	}

	/**
	 * Updates the status of the alarm.
	 */

	public void update() {
		if (active && TimeKeeper.getTime() >= (start + duration)) {
			done = true;
			stop();
		}
	}

	/**
	 * returns the progress of the alarm as a percentage. Returns 0.00 if alarm is
	 * not active.
	 * 
	 * @return
	 */

	public double getProgress() {
		return (active ? (double) (TimeKeeper.getTime() - start) / duration : 0.00);
	}

	/**
	 * Return the amount of time left i milliseconds.
	 * 
	 * @return
	 */

	public long getTimeLeft() {
		return TimeKeeper.getTime() - (start + duration);
	}

	/**
	 * Returns the state of the alarm. Should not be used in drawing and looping
	 * methods that does not reset the alarm.
	 * 
	 * @return state
	 */

	public boolean isActive() {
		return active;
	}

	/**
	 * Set the duration of the alarm in milliseconds.
	 * 
	 * @param duration
	 */

	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Returns the duration of the alarm in milliseconds.
	 * 
	 * @return long
	 */

	public long getDuration() {
		return duration;
	}

	/**
	 * Returns whether the indicated time has passed. Should not be used in methods
	 * that reset the alarm. Use instead isActive().
	 * 
	 * @return boolean
	 */

	public boolean isDone() {
		return done;
	}

	/**
	 * Sets the pause state of the alarm. Does nothing if the alarm is not active.
	 * 
	 * @param state
	 */

	public void setPaused(boolean state) {
		if (active) {
			paused = state;
			if (state) {
				pauseTime = TimeKeeper.getTime();
			} else {
				start += TimeKeeper.getTime() - pauseTime;
			}
		}
	}

	/**
	 * Returns whether the alarm is paused.
	 * 
	 * @return boolean
	 */

	public boolean isPaused() {
		return paused;
	}
}
