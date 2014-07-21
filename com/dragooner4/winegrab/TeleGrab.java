package com.dragooner4.winegrab;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GroundItem;
import com.dragooner4.framework.*;

public class TeleGrab extends Task {
	private Component magicTab = ctx.widgets.widget(548).component(61);
	private static final int teleGrabCompID = 19;
	private Component telekineticGrab = ctx.widgets.widget(192).component(
			teleGrabCompID);
	private GroundItem Wine;

	public TeleGrab(MyContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean valid() {
		ctx.inventory.select();
		Wine = ctx.groundItems.select().id(245).peek();
		Tile location = ctx.players.local().tile();
		return ctx.methods.inArea(altarArea, location)
				&& ctx.inventory.count() < 28 && Wine.inViewport();
	}

	@Override
	public void run() {

		ctx.methods.setStatus("Telegrabbing Wine of zamorak");

		if (!ctx.methods.selected()) {
			ctx.methods.setSelected(ctx.methods.castSpell(teleGrabCompID));
			Condition.sleep(500);
		}

		else if (ctx.methods.selected())
			ctx.methods.moveToWineCP();

		if (Wine.inViewport() && ctx.methods.selected()) {

			ctx.methods.setSelected(!Wine.interact("Cast"));
			if (GUI.alchSelected)
				ctx.methods.setSelected(!telekineticGrab.interact("Cancel"));
			Condition.sleep(500);
		}
		if (!telekineticGrab.visible()) {
			magicTab.click();
			Condition.sleep();
		}

	}
}
