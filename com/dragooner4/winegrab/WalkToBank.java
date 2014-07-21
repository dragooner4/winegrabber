package com.dragooner4.winegrab;

import org.powerbot.script.Tile;

import com.dragooner4.framework.*;

public class WalkToBank extends Task {
	private final Tile[] bankPath;
	private final Tile[] bankArea;

	public WalkToBank(MyContext ctx) {
		super(ctx);
		bankPath = new Tile[] { new Tile(2964, 3380, 0),
				new Tile(2957, 3380, 0), new Tile(2951, 3376, 0),
				new Tile(2946, 3372, 0), new Tile(2945, 3368, 0) };

		bankArea = ctx.methods.generateArea(2945, 2949, 3368, 3368);

	}

	public boolean valid() {
		Tile location = ctx.players.local().tile();
		return ctx.inventory.count() == 28
				&& !ctx.methods.inArea(bankArea, location);
	}

	@Override
	public void run() {
		ctx.methods.setStatus("Walking to Falador bank");
		ctx.methods.traversePath(bankPath);
	}

}
