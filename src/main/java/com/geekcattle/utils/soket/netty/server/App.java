package com.geekcattle.utils.soket.netty.server;

import com.geekcattle.netty.server.TCPServer;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		TCPServer.getSingletonInstance().run();
	}
}
