package org.formix.thevgravel.quest.engine;

public class Event {

	private SceneItem source;
	private String name;
	private Object data;
	
	public Event(SceneItem source, String name, Object data) {
		this.source = source;
		this.name = name;
		this.data = data;
	}

	public SceneItem getSource() {
		return source;
	}

	public String getName() {
		return name;
	}

	public Object getData() {
		return data;
	}
	
}
