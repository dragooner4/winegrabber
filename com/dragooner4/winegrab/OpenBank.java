package com.dragooner4.winegrab;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Component;

import com.dragooner4.framework.*;

public class OpenBank extends Task {
	private static final int BANK_BOOTH = 24101;
	private Tile[] BANK_AREA;
	private final Component BANK_WINDOW;

	public OpenBank(MyContext ctx) {
		super(ctx);
		BANK_AREA = ctx.methods.generateArea(2943, 2950, 3368, 3371);
		BANK_WINDOW = ctx.widgets.widget(12).component(0);
	}

	@Override
	public boolean valid() {

		Tile location = ctx.players.local().tile();
		return ctx.methods.inArea(BANK_AREA, location)
				&& ctx.inventory.select().count() == 28
				&& !BANK_WINDOW.visible();

	}

	@Override
	public void run() {
		ctx.methods.setStatus("Opening bank booth");
		ctx.methods.openBank(BANK_BOOTH);
		Condition.sleep();
	}

}
