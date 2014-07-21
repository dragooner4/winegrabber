package com.dragooner4.winegrab;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.TilePath;

import com.dragooner4.framework.*;

public class RunBack extends Task {
	private Tile[] safeArea;
	private TilePath path;

	public RunBack(MyContext ctx) {
		super(ctx);
		safeArea = ctx.methods.generateArea(2937, 2943, 3488, 3513);
		path = ctx.movement.newTilePath(runAwayPath).reverse();
	}

	public boolean valid() {
		return ctx.methods.inArea(safeArea, ctx.players.local().tile())
				&& !ctx.players.local().inCombat();
	}

	public void run() {
		ctx.methods.setStatus("Running back to TeleGrab");
		path.traverse();
	}

}
