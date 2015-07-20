package org.formix.thevgravel.quest.engine;

public interface SceneEventListener<D> {

	void notify(Object source, D data);
	
}
