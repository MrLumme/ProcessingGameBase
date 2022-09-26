package lumCode.processingGameBase.keys;

import java.util.HashMap;

public final class InputTracker {
	private static final HashMap<Input, Boolean> inputs = new HashMap<Input, Boolean>();

	public static void state(Input input, boolean state) {
		inputs.put(input, state);
	}

	public static boolean getState(Input input) {
		return inputs.getOrDefault(input, false);
	}
}
