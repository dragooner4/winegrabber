package com.dragooner4.winegrab;

import com.dragooner4.framework.*;

public class AdjustCamera extends Task {

	public AdjustCamera(MyContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean valid() {
		// TODO Auto-generated method stub
		return ctx.camera.yaw() < 280 || ctx.camera.yaw() > 290
				|| ctx.camera.pitch() != 99;
	}

	@Override
	public void run() {
<<<<<<< HEAD
		ctx.methods.setStatus("Adjusting camera...");
=======
	   	ctx.methods.setStatus("Adjusting camera...");
>>>>>>> 55f4a60a642f700d888a49fa654ac772e93a4258
		ctx.camera.pitch(99);
		ctx.camera.angle(285);

	}

}
