package org.formix.thevgravel.quest.engine;

import java.awt.Rectangle;

public interface Collisionnable {

	void collided(Collisionnable other);

	Rectangle getRectangle();
}
