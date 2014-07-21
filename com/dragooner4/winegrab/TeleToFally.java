package com.dragooner4.winegrab;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Component;

import com.dragooner4.framework.MyContext;
import com.dragooner4.framework.*;

public class TeleToFally extends Task {
	private Tile[] altarArea;
	private static final int fallyCompID = 21;
	private Component magicTab = ctx.widgets.widget(548).component(61);

	public TeleToFally(MyContext ctx) {
		super(ctx);
		altarArea = ctx.methods.generateArea(2930, 2936, 3514, 3517);
	}

	@Override
	public boolean valid() {
		ctx.inventory.select();
		Tile location = ctx.players.local().tile();
		return ctx.methods.inArea(altarArea, location)
				&& ctx.inventory.count() == 28;
	}

	@Override
	public void run() {

		ctx.methods.setStatus("Inventory full , teleporting back to Falador");
		ctx.methods.setSelected(false);

		if (!ctx.methods.castSpell(fallyCompID)) {
			magicTab.click();
			Condition.sleep();
		} else
			Condition.sleep(1500);

	}

}
