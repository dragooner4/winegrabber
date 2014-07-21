package com.dragooner4.winegrab;

import org.powerbot.script.Tile;

import com.dragooner4.framework.MyContext;
import com.dragooner4.framework.Task;

public class RunAway extends Task {
	public RunAway(MyContext ctx) {
		super(ctx);

	}

	public boolean valid() {
		return ctx.players.local().inCombat();
	}

	public void run() {
		ctx.methods.setSelected(false);
		ctx.methods.setStatus("Fleeing the scene");
		ctx.methods.traversePath(runAwayPath);
	}

}
