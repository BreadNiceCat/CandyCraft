package cn.breadnicecat.candycraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 18:41
 */
public class LevelUtils {
	public static void spawnItemEntity(Level level, @NotNull Vec3 pos, ItemStack stack) {
		spawnItemEntity(level, pos.x(), pos.y(), pos.z(), stack);
	}

	public static void spawnItemEntity(Level level, @NotNull BlockPos pos, ItemStack stack) {
		spawnItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack);
	}

	public static void spawnItemEntity(@NotNull Level level, double posX, double posY, double posZ, ItemStack stack) {
		level.addFreshEntity(new ItemEntity(level, posX, posY, posZ, stack));
	}
}
