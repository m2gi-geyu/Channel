package edu.uga.m2gi.ar.channels;

public class Broker {
	String m_name;
	int port;
	public Broker(String name) {
		m_name = name;
	}

	public String name() {
		return m_name;
	}

	public Channel accept(int port) {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Channel c=TableChannel.getClient(port);
		return c;
	}

	public Channel connect(String name, int port) throws InterruptedException {
		Channel c=TableChannel.connect(port,m_name);
		notify();
		return c;
	}
}
