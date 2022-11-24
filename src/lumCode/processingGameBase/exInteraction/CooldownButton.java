package lumCode.processingGameBase.exInteraction;

import lumCode.interactables.entities.Button;
import lumCode.processingGameBase.time.AlarmFunction;
import lumCode.processingGameBase.time.TimeKeeper;
import processing.core.PApplet;
import processing.core.PFont;

public abstract class CooldownButton extends Button {
	private final AlarmFunction cooldown;
	private boolean cooldownDisabled;

	public CooldownButton(int x, int y, int w, int h, PApplet p, long coolDown) {
		super(x, y, w, h, p);
		cooldown = new AlarmFunction(coolDown) {

			@Override
			public void function() {
				setDisabled(false);
			}
		};
		TimeKeeper.addAlarm(cooldown);
	}

	public CooldownButton(int x, int y, int w, int h, PApplet p, String label, PFont font, long coolDown) {
		super(x, y, w, h, p, label, font);
		cooldown = new AlarmFunction(coolDown) {

			@Override
			public void function() {
				setDisabled(false);
			}
		};
		TimeKeeper.addAlarm(cooldown);
	}

	@Override
	public boolean mouseClicked() {
		boolean res = super.mouseClicked();
		if (res && !cooldown.isActive() && !cooldownDisabled) {
			setDisabled(true);
			cooldown.start();
		}
		return res;
	}

	public void stopCooldown() {
		cooldown.stop();
	}

	public boolean isCooldownActive() {
		return cooldown.isActive();
	}

	public boolean isCooldownDisabled() {
		return cooldownDisabled;
	}

	public void setCooldownDisabled(boolean cooldownDisabled) {
		this.cooldownDisabled = cooldownDisabled;
	}
}
