package lumCode.processingGameBase.time;

import java.util.ArrayList;
import java.util.Iterator;

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
	private static final ArrayList<Trigger> triggers = new ArrayList<>();

	private static boolean paused = false;
	private static boolean autoClearAlarms = false;

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
				checkTriggers();
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
		if (autoClearAlarms) {
			clearDoneAlarms();
		}
	}

	/**
	 * Checks if any trigger condition has been met.
	 */

	private static synchronized void checkTriggers() {
		Iterator<Trigger> it = triggers.iterator();
		while (it.hasNext()) {
			Trigger act = it.next();
			if (act.condition()) {
				act.action();
				it.remove();
			}
		}
	}

	private static void checkGame() {
		// TODO Add all time dependent stuff, game logic, and calculations.
	}

	public static synchronized void clearDoneAlarms() {
		Iterator<Alarm> it = alarms.iterator();
		while (it.hasNext()) {
			Alarm a = it.next();
			if (a.isDone() && (!(a instanceof AlarmFunction) || !((AlarmFunction) a).isRepeat())) {
				it.remove();
			}
		}
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
	 * Adds an Alarm to the event list.
	 * 
	 * @param event
	 */

	public static synchronized void addAlarm(Alarm event) {
		alarms.add(event);
	}

	/**
	 * Removes an Alarm from the event list
	 * 
	 * @param event
	 */

	public static synchronized void removeAlarm(Alarm event) {
		alarms.remove(event);
	}

	/**
	 * Adds a Trigger to the triggers list.
	 * 
	 * @param event
	 */

	public static synchronized void addTrigger(Trigger trigger) {
		triggers.add(trigger);
	}

	/**
	 * Removes a Trigger from the triggers list
	 * 
	 * @param event
	 */

	public static synchronized void removeTrigger(Trigger trigger) {
		triggers.remove(trigger);
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

	/**
	 * Returns whether or not TimeKeeper will clear finished alarms automatically
	 * 
	 * @return
	 */

	public static boolean isAutoClearAlarms() {
		return autoClearAlarms;
	}

	/**
	 * Sets whether or not TimeKeeper will clear finished alarms automatically
	 * 
	 * @param autoClearAlarms
	 */

	public static void setAutoClearAlarms(boolean autoClearAlarms) {
		TimeKeeper.autoClearAlarms = autoClearAlarms;
	}
}
