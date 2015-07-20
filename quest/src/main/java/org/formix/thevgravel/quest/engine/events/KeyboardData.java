package org.formix.thevgravel.quest.engine.events;

import java.awt.event.KeyEvent;

public class KeyboardData extends KeyEvent {
	private static final long serialVersionUID = 4881608618782257740L;

	private KeyboardDataType type;

	public KeyboardData(KeyEvent sourceEvent, KeyboardDataType type) {
		super(sourceEvent.getComponent(), sourceEvent.getID(), sourceEvent.getWhen(), sourceEvent.getModifiers(),
				sourceEvent.getKeyCode(), sourceEvent.getKeyChar(), sourceEvent.getKeyLocation());
		this.type = type;
	}

	public KeyboardDataType getType() {
		return this.type;
	}
}
