package com.dragooner4.framework;

import org.powerbot.script.rt4.ClientContext;

public class MyContext extends ClientContext {
	public MyMethods methods;

	public MyContext(ClientContext ctx) {
		super(ctx);
		methods = new MyMethods(this);
	}
}
