package org.formix.thevgravel.quest.engine.events;

import java.util.EventObject;

public class SceneEvent<D> extends EventObject {

	private static final long serialVersionUID = -2015074766230586206L;
	
	private String name;
	private D data;
	
	public SceneEvent(Object source, String name, D data) {
		super(source);
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public D getData() {
		return data;
	}
	
}
