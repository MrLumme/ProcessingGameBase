package lumCode.processingGameBase.time;

import java.util.ArrayList;

import lumCode.processingGameBase.Main;
import lumCode.processingGameBase.Settings;

/**
 * Singleton class that updates the game independently of the draw speed. All
 * game logic should run from here. It is capable of being paused, which
 * effectively pauses the whole game.
 * 
 * @author Lumme
 * @version 0.1
 * @since 20-09-2019
 */

public final class TimeKeeper extends Thread {
	private static TimeKeeper instance = null;

	private static final ArrayList<Alarm> alarms = new ArrayList<>();

	private static boolean paused = false;

	/**
	 * Constructor. Sets the instance to be a daemon thread.
	 */

	private TimeKeeper() {
		setDaemon(true);
		setName("TimeKeeper");
	}

	// ---------
	// FUNCTIONS
	// ---------

	/**
	 * The method that initializes the instance.
	 * 
	 * @return TimeKeeper
	 */

	public static TimeKeeper init() {
		TimeKeeper o = getInstance();
		o.start();
		return o;
	}

	/**
	 * The method running all the logic.
	 */

	@Override
	public void run() {
		while (true) {
			long start = System.currentTimeMillis();
			if (Main.doTick && !paused) {
				checkAlarms();
				checkGame();
			}

			long tick = Settings.TICK_SPEED - (System.currentTimeMillis() - start);
			if (tick > 0) {
				try {
					Thread.sleep(tick);
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		}
	}

	/**
	 * Checks if any alarm have expired.
	 */

	private static synchronized void checkAlarms() {
		for (Alarm a : alarms) {
			a.update();
		}
	}

	private void checkGame() {
		// TODO Add all time dependent stuff, game logic, and calculations.
	}

	// -------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * The method used to get the TimeKeeper.
	 * 
	 * @return TimeKeeper
	 */

	public static TimeKeeper getInstance() {
		if (instance == null) {
			instance = new TimeKeeper();
		}
		return instance;
	}

	/**
	 * Adds a Alarm to the event list.
	 * 
	 * @param event
	 */

	public static synchronized void addAlarm(Alarm event) {
		alarms.add(event);
	}

	/**
	 * Removes a Alarm from the event list
	 * 
	 * @param event
	 */

	public static synchronized void removeAlarm(Alarm event) {
		alarms.remove(event);
	}

	/**
	 * Returns the current system time in milliseconds as a long
	 * 
	 * @return current time
	 */

	public static long getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * @return whether or not the TimeKeeper is currently paused
	 */

	public static boolean isPaused() {
		return paused;
	}

	/**
	 * @param paused
	 */

	public static void setPaused(boolean paused) {
		TimeKeeper.paused = paused;
	}
}
