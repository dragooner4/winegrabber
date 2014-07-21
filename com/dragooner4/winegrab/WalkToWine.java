package com.dragooner4.winegrab;

import org.powerbot.script.Tile;
import com.dragooner4.framework.*;

public class WalkToWine extends Task {

	private final Tile[] winePath;

	public WalkToWine(MyContext ctx) {
		super(ctx);
		winePath = new Tile[] { new Tile(2946, 3368, 0),
				new Tile(2946, 3375, 0), new Tile(2952, 3379, 0),
				new Tile(2958, 3381, 0), new Tile(2961, 3387, 0),
				new Tile(2964, 3393, 0), new Tile(2959, 3398, 0),
				new Tile(2955, 3404, 0), new Tile(2956, 3410, 0),
				new Tile(2956, 3417, 0), new Tile(2956, 3424, 0),
				new Tile(2953, 3430, 0), new Tile(2950, 3436, 0),
				new Tile(2948, 3442, 0), new Tile(2948, 3449, 0),
				new Tile(2946, 3455, 0), new Tile(2946, 3462, 0),
				new Tile(2946, 3469, 0), new Tile(2946, 3476, 0),
				new Tile(2946, 3483, 0), new Tile(2946, 3490, 0),
				new Tile(2942, 3496, 0), new Tile(2941, 3502, 0),
				new Tile(2941, 3509, 0), new Tile(2942, 3515, 0),
				new Tile(2936, 3517, 0), new Tile(2933, 3515, 0) };

	}

	@Override
	public boolean valid() {

		return ctx.inventory.count() >= 2
				&& !ctx.methods.inArea(altarArea, ctx.players.local().tile());

	}

	@Override
	public void run() {
		ctx.methods.setStatus("Walking to altar to telegrab Wine of Zamorak");
		ctx.methods.traversePath(winePath);
	}

}
