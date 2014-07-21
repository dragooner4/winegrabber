package com.dragooner4.winegrab;

import com.dragooner4.framework.*;

public class RanOut extends Task {

	public RanOut(MyContext ctx) {
		super(ctx);
	}

	public void run() {
		ctx.controller.stop();
	}

	public boolean valid() {
		return ctx.methods.itemCount(MyMethods.LAW_RUNE) == 0 || ctx.methods.itemCount(MyMethods.WATER_RUNE) == 0;
	}

}
