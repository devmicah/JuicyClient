package dev.micah.juicyclient.event.impl;

import dev.micah.juicyclient.event.Event;

public class EventKey extends Event {

	private int key;
	public EventKey(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
}
