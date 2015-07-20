package org.formix.thevgravel.quest.engine.events;

public interface SceneEventListener<D> {

	void handle(SceneEvent<D> event);

}
