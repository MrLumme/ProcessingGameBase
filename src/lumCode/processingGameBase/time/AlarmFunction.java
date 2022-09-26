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
			}
		}
	}

	/**
	 * Abstract function which the TimeKeeper triggers when the time has passed.
	 */

	public abstract void function();
}
