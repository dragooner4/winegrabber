package com.dragooner4.winegrab;

import java.util.Arrays;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.GameObject;

import com.dragooner4.framework.MyContext;
import com.dragooner4.framework.Task;

public class LostAndFound extends Task {
	public LostAndFound(MyContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	private static final String APPENDAGE = "Appendage";

	@Override
	public boolean valid() {
		ctx.objects.select();
		GameObject appendage = ctx.objects.select().name(APPENDAGE).within(1)
				.poll();
		return appendage.valid();
	}

	private GameObject oddOneOut() {
		int[] ids = new int[4];
		int i = 0;

		for (GameObject appendage : ctx.objects.select().name(APPENDAGE)) {
			ids[i] = appendage.id();
			i++;
		}
		Arrays.sort(ids);

		if (ids[3] == ids[2]) {
			return ctx.objects.select().id(ids[0]).poll();
		} else {
			return ctx.objects.select().id(ids[3]).poll();
		}
	}

	@Override
	public void run() {
		ctx.methods.setStatus("In Lost and Found random event");
		Condition.sleep(2500);
		ctx.methods.setStatus("Applying multi line algorithm to determine odd appendage...");
		Condition.sleep(1000);
		ctx.methods.setStatus("Odd Appendage found! GREAT SUCESS");
		final GameObject appendage = oddOneOut();
		if (appendage.valid()) {

			appendage.interact("Operate");
			Condition.sleep(3000);
			System.out.println("Success");
		}
	}
}
