package edu.uga.m2gi.ar.channels;

public abstract class MessageQueue {
	void send(byte[] bytes, int offset, int length) {
	}
	byte[] receive() {
		return null;
	}
	void close() {
	}
	boolean closed() {
		return false;
	}
}
