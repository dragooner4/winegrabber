package com.dragooner4.winegrab;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Component;
import com.dragooner4.framework.*;

public class Deposit extends Task {
	private static final int WINE = 245;
	private final Component BANK_WINDOW;

	public Deposit(MyContext ctx) {
		super(ctx);
		BANK_WINDOW = ctx.widgets.widget(12).component(0);
	}

	@Override
	public boolean valid() {
		ctx.inventory.select();
		return ctx.inventory.id(WINE).count() > 1 && BANK_WINDOW.visible();
	}

	@Override
	public void run() {
		ctx.methods.setStatus("Depositing wine of Zamorak");
		ctx.methods.setWines(ctx.methods.getWineCollected());
		ctx.methods.depositItems(WINE);
		ctx.methods.resetStartWine();
		Condition.sleep();
	}

}
