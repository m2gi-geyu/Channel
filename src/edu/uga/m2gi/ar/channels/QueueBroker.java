package edu.uga.m2gi.ar.channels;

public abstract class QueueBroker {
	QueueBroker(String name){
		
	}
	MessageQueue accept(int port) {
		return null;
		
	}
	MessageQueue connect(String name, int port) {
		return null;
		
	}
}
