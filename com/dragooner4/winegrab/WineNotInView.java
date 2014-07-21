package com.dragooner4.winegrab;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Item;
import com.dragooner4.framework.*;

public class WineNotInView extends Task {
	private GroundItem Wine;
	private Tile[] altarArea;
	private int alchID;
<<<<<<< HEAD
	private Component TelekineticGrab = ctx.widgets.widget(192).component(19);
	private Component HighAlch = ctx.widgets.widget(192).component(34);
=======
	private Component TelekineticGrab;
	private Component HighAlch;
>>>>>>> 55f4a60a642f700d888a49fa654ac772e93a4258
	private boolean isAlchSelected = false;
	private Item alchItem;

	public WineNotInView(MyContext ctx) {
		super(ctx);
		altarArea = ctx.methods.generateArea(2930, 2936, 3514, 3517);
		if (GUI.alchSelected)
			alchID = Integer.parseInt(GUI.alchItemID);
<<<<<<< HEAD
=======
	  	TelekineticGrab = ctx.widgets.widget(192).component(19);
	  	HighAlch = ctx.widgets.widget(192).component(34);
>>>>>>> 55f4a60a642f700d888a49fa654ac772e93a4258

	}

	@Override
	public boolean valid() {
		Wine = ctx.groundItems.select().id(245).peek();
		Tile location = ctx.players.local().tile();
		return !Wine.inViewport() && ctx.methods.inArea(altarArea, location)
				&& ctx.inventory.count() < 28;
	}

	@Override
	public void run() {
		endAlching();
		ctx.methods.setStatus("Waiting for wine to spawn...");
		if (TelekineticGrab.visible() && !GUI.alchSelected)
			Wine.hover();
		else if (GUI.alchSelected && HighAlch.visible() && !isAlchSelected) {
			alchItem = ctx.inventory.select().id(alchID).poll();
			isAlchSelected = ctx.methods.castSpell(34);
			ctx.methods.setSelected(false);
			if (ctx.inventory.select().id(alchID).count(true) > 0) {
				Condition.sleep();
				isAlchSelected = !alchItem.interact("Cast");
			}
		}

	}

	private void endAlching() {
		if (ctx.methods.itemCount(MyMethods.FIRE_RUNE) == 0
				|| ctx.methods.itemCount(MyMethods.NATURE_RUNE) == 0
				|| ctx.methods.itemCount(alchID) == 0) {
			GUI.alchSelected = false;
		}
	}

}
