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
<<<<<<< HEAD
		return ctx.methods.itemCount(MyMethods.LAW_RUNE) == 0
				|| ctx.methods.itemCount(MyMethods.WATER_RUNE) == 0;
=======
		return ctx.methods.itemCount(MyMethods.LAW_RUNE) == 0 || ctx.methods.itemCount(MyMethods.WATER_RUNE) == 0;
>>>>>>> 55f4a60a642f700d888a49fa654ac772e93a4258
	}

}
