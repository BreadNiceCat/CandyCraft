package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 22:32
 */
public class CandylandPortalBlock extends NetherPortalBlock {
	public CandylandPortalBlock() {
		super(BlockBehaviour.Properties.of(Material.GLASS, DyeColor.ORANGE).noCollission().strength(-1).lightLevel((state) -> 11));
	}

	@Override
	public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
		//无需randomTick
	}
}
