package lumCode.processingGameBase;

/**
 * A Keeper is single instance object running a set of tasks as a subroutine.
 * It runs forever in a while(true) loop, calling the abstract tasks() method regularly.
 * It is set as a daemon thread to ensure it closes down when the program asks it to.
 * It provides utilities for suspending it temporarily, and for aborting it entirely.
 *
 * Before making a new subclass Keeper for a part of the program, the following questions must all be true:
 * - Does this need to run on a separate schedule?
 * - Does this
 *
 * @author Lumme
 * @version 0.1
 * @since 12-02-2026
 */

public abstract class Keeper extends Thread {
	private boolean suspended = false;
	private boolean abort = false;
	private long tickTime;

	/**
	 * Constructor. Sets the instance to be a daemon thread. Tick time is the time in milliseconds between each call to the tasks() method. After the method
	 * is done the Keeper will wait for the remaining amount of milliseconds before calling the tasks() method again. If left out it defaults to
	 * Settings.TICK_SPEED. If set to zero or negative, waiting is skipped entirely and the method will be called repeatedly as fast as possible.
	 */

	protected Keeper(String typeName, long tickTime) {
		setDaemon(true);
		setName(typeName + "Keeper");
		this.tickTime = tickTime;
	}

	protected Keeper(String typeName) {
		this(typeName, Settings.TICK_SPEED);
	}

	// ---------
	// FUNCTIONS
	// ---------

	/**
	 * The method running all the logic.
	 */

	@Override
	public void run() {
		initialSetup();
		if (abort) {
			return;
		}
		if (tickTime > 0) {
			while (true) {
				long start = System.currentTimeMillis();
				doTasks();
				if (abort) {
					return;
				}
				long tick = tickTime - (System.currentTimeMillis() - start);
				if (tick > 0) {
					try {
						Thread.sleep(tick);
					} catch (InterruptedException e) {
						// Do nothing
					}
				}
			}
		} else {
			while (true) {
				doTasks();
				if (abort) {
					return;
				}
			}
		}
	}

	private void doTasks() {
		if (Main.doTick && !suspended) {
			tasks();
		}
	}

	protected void initialSetup() {
		// Do nothing by default
	};

	protected abstract void tasks();

	// ---------
	// UTILITIES
	// ---------

	public void suspend() {
		suspended = true;
	}

	public void unsuspend() {
		suspended = false;
	}

	public boolean isSuspended() {
		return suspended;
	}

	protected void abort() {
		abort = true;
	}
}
