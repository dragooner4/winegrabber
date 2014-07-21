package com.dragooner4.framework;

import org.powerbot.script.Tile;

public abstract class Task extends GraphScript.Action<MyContext> {
	public Tile[] runAwayPath;
	public Tile[] altarArea;

	public Task(MyContext ctx) {
		super(ctx);
		runAwayPath = new Tile[] { new Tile(2932, 3515),
				new Tile(2935, 3515, 0), new Tile(2941, 3517, 0),
				new Tile(2941, 3510, 0), new Tile(2941, 3503, 0),
				new Tile(2942, 3497, 0), new Tile(2940, 3494, 0) };
		altarArea = ctx.methods.generateArea(2930, 2936, 3514, 3517);
	}

}
