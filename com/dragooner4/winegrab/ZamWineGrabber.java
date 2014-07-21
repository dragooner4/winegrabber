
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

import org.powerbot.script.Condition;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Script;

import com.dragooner4.framework.*;
import com.dragooner4.winegrab.*;

@Script.Manifest(name = "Zamorak Wine Grabber", description = "Uses telegrab to grab the wine of zamorak and then banks it", properties = "SWAG")
public class ZamWineGrabber extends GraphScript<MyContext> implements
		PaintListener {

	public void start() {
		new GUI(ctx);
		while (!GUI.initiate)
			Condition.sleep();

		chain.addAll(Arrays.asList(new Deposit(ctx), new AdjustCamera(ctx),
				new OpenBank(ctx), new RanOut(ctx), new TeleGrab(ctx),
				new LostAndFound(ctx), new TeleToFally(ctx),
				new WalkToBank(ctx), new WalkToBank(ctx), new WalkToWine(ctx),
				new WineNotInView(ctx), new RunBack(ctx), new RunAway(ctx)));
	}

	public void repaint(Graphics g) {
		Font font = new Font("Verdana", Font.BOLD, 13);
		int x = ctx.input.getLocation().x;
		int y = ctx.input.getLocation().y;
		final int margin = 10;
		final int spacing = 15;
		final int vertMargin = 30;
		long elapsedTime = ctx.controller.script().getTotalRuntime();
		g.setColor(Color.RED);
		g.setFont(font);

		g.drawString("Time elapsed: " + ctx.methods.clock(), margin, vertMargin);
		g.drawString(
				"Law runes left: " + ctx.methods.itemCount(MyMethods.LAW_RUNE),
				margin, vertMargin + spacing);
		g.drawString(
				"Water runes left: "
						+ ctx.methods.itemCount(MyMethods.WATER_RUNE), margin,
				vertMargin + 2 * spacing);
		g.drawString("Wines collected: " + ctx.methods.getWineCollected(),
				margin, vertMargin + 3 * spacing);
		g.drawString("Total experience gained: + " + ctx.methods.getMagicExp(),
				margin, vertMargin + 4 * spacing);
		g.drawString(
				"Experience per hour: "
						+ (int) (ctx.methods.getMagicExp() / (elapsedTime / 3600000.0)),
				margin, vertMargin + 5 * spacing);
		g.drawString("Total gold gained: " + ctx.methods.gold(true), margin,
				vertMargin + 6 * spacing);
		g.drawString("Gold per hour: "
				+ (int) (ctx.methods.gold(true) / (elapsedTime / 3600000.0)),
				margin, vertMargin + 7 * spacing);
		g.drawString("Status: " + ctx.methods.status(), margin, vertMargin + 8
				* spacing);
		g.drawLine(x, 0, x, 500);
		g.drawLine(0, y, 765, y);

	}

}
