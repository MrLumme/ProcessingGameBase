package lumCode.processingGameBase.resource;

public abstract class Resource {
	private static int idCounter = 0;
	public final int id;

	// -----------
	// CONSTRUCTOR
	// -----------

	protected Resource() {
		id = idCounter;
		idCounter++;
	}

	// --------
	// INTERNAL
	// --------

	protected abstract boolean isReady();
}
