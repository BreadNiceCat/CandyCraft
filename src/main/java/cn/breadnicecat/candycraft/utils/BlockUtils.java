package cn.breadnicecat.candycraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/10 9:07
 */
public class BlockUtils {
	public static BlockPos move(BlockPos pos, Direction direction, int distance) {
		return switch (direction) {
			case DOWN -> pos.below(distance);
			case UP -> pos.above(distance);
			case NORTH -> pos.north(distance);
			case SOUTH -> pos.south(distance);
			case WEST -> pos.west(distance);
			case EAST -> pos.east(distance);
		};
	}
}
