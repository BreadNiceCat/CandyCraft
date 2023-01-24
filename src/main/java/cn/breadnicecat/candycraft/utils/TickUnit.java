package cn.breadnicecat.candycraft.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/19 21:02
 */
public class TickUnit {
	public static long toTick(TimeUnit unit, long duration) {
		return unit.toMillis(duration) / 50;
	}
	
	public static long fromTick(TimeUnit unit, long tick) {
		return TimeUnit.MILLISECONDS.convert(tick * 50, unit);
	}
	
}
