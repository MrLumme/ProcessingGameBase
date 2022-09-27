package lumCode.processingGameBase.time;

/**
 * Class to define a timed event. It contains a method which will be run by a
 * another object when the designated time has passed. It extends the Alarm
 * class.
 * 
 * @author Lumme
 * @version 0.1
 * @since 02-08-2019
 */

public abstract class AlarmFunction extends Alarm {
	private boolean repeat;

	/**
	 * Constructor. Length is in milliseconds.
	 * 
	 * @param length
	 */

	public AlarmFunction(long length) {
		super(length);
	}

	/**
	 * Updates the status of the alarm
	 */

	@Override
	public void update() {
		if (isActive()) {
			boolean t = isDone();
			super.update();
			if (!t && isDone()) {
				function();
				if (repeat) {
					start();
				}
			}
		}
	}

	/**
	 * Abstract function which the TimeKeeper triggers when the time has passed.
	 */

	public abstract void function();

	/**
	 * Returns whether or not the AlarmFunction repeats when it is finished.
	 * 
	 * @return
	 */

	public boolean isRepeat() {
		return repeat;
	}

	/**
	 * Sets whether or not the AlarmFunction repeats when it is finished.
	 * 
	 * @param repeat
	 */

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
}
