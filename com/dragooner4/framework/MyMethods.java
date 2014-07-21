package com.dragooner4.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Skills;
import org.powerbot.script.rt4.TileMatrix;
import org.powerbot.script.rt4.TilePath;

public class MyMethods extends ClientAccessor {
	private static final String itemURL = "http://forums.zybez.net/runescape-2007-prices/api/item/";
	private String status = "";
	private final int startXP;
	private int winePrice, lawPrice;
	private final static String wineID = "104";
	private final static String lawID = "282";
	private final static int WINE_ID = 245;
	private boolean isTeleGrabSelected = false;
	public final static int WATER_RUNE = 555;
	public final static int LAW_RUNE = 563;
	public final static int NATURE_RUNE = 561;
	public final static int FIRE_RUNE = 554;
	private int totalWines;
	private int wineStart;

	public MyMethods(ClientContext ctx) {
		super(ctx);
		startXP = ctx.skills.experience(Skills.MAGIC);
		try {
			lawPrice = getPrice(lawID);
			winePrice = getPrice(wineID);

		} catch (IOException e) {
			lawPrice = 250;
			winePrice = 1600;
			e.printStackTrace();
		}
		wineStart = ctx.inventory.id(WINE_ID).count();
	}

	public boolean castSpell(int compID) {
		if (ctx.widgets.widget(192).component(compID).visible()) {
			return ctx.widgets.widget(192).component(compID).interact("Cast");
		} else
			return false;
	}

	public boolean traversePath(Tile[] tiles) {
		TilePath path = ctx.movement.newTilePath(tiles);
		return path.traverse();
	}

	public Tile[] generateArea(int x1, int x2, int y1, int y2) {
		Tile[] area = new Tile[(x2 - x1 + 1) * (y2 - y1 + 1)];
		int c = 0;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				area[c] = new Tile(i, j);
				c++;
			}
		}
		return area;
	}

	public boolean inArea(Tile[] area, Tile loc) {
		return Arrays.binarySearch(area, loc) >= 0;
	}

	public boolean openBank(int bankID) {
		return ctx.objects.select().id(bankID).nearest().poll()
				.interact("Bank");
	}

	public boolean depositItems(int itemID) {
		return ctx.bank.deposit(itemID, ctx.inventory.id(itemID).count(true));
	}

	public int getPrice(final String id) throws IOException {
		String url = itemURL + id;
		URL requestUrl = new URL(url);

		URLConnection con = requestUrl.openConnection();
		con.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = in.read()) != -1)
				sb.append((char) cp);
		} catch (Exception e) {
		}
		String json = sb.toString();
		int i = json.indexOf("average") + 9;
		String price = json.substring(i, i + 5);
		return (int) (Double.parseDouble(price) + .5);
	}

	public String status() {
		return status;
	}

	public void setStatus(String s) {
		status = s;
	}

	public int getMagicExp() {
		return Math.abs(ctx.skills.experience(Skills.MAGIC) - startXP);
	}

	public String clock() {
		long elapsed = ctx.controller.script().getRuntime();
		String H = "";
		String S = "";
		String M = "";
		final long ts = elapsed / 1000;
		final long tm = ts / 60;
		final long th = tm / 60;
		final int s = (int) ts % 60;
		final int m = (int) tm % 60;

		if (s < 10)
			S = "0" + s;
		else
			S = "" + s;

		if (m < 10)
			M = "0" + m;
		else
			M = "" + m;

		if (th < 10)
			H = "0" + th;
		else
			H = "" + th;

		return "" + H + ":" + M + ":" + S;
	}

	public int getWineCollected() {

		return Math.abs(ctx.inventory.select().id(WINE_ID).count() - wineStart)
				+ totalWines;
	}

	public void resetStartWine() {
		wineStart = 0;
	}

	public void setWines(int i) {
		totalWines = i;
	}

	public int gold(boolean b) {
		if (b)
			return getWineCollected() * (winePrice - lawPrice);
		else
			return getWineCollected() * winePrice;
	}

	public boolean moveToWineCP() {
		GroundItem item = ctx.groundItems.select().id(WINE_ID).poll();
		TileMatrix t = item.tile().derive(0, 0, 1).matrix(ctx);
		if (!t.getBounds().contains(ctx.input.getLocation())) {
			System.out.println("moveToCP is being called");
			return ctx.input.move(t.centerPoint());
		} else

			return false;
	}

	public boolean selected() {
		return isTeleGrabSelected;
	}

	public void setSelected(boolean bool) {
		isTeleGrabSelected = bool;
	}

	public int itemCount(int ID) {
		return ctx.inventory.select().id(ID).count(true);
	}
}