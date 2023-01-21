package cn.breadnicecat.candycraft.misc;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 9:37
 */
public final class CCSoundTypes {
	// volumeIn,  pitchIn, breakSoundIn,stepSoundIn, placeSoundIn,hitSoundIn,fallSoundIn
	public static final SoundType JELLY = new ForgeSoundType(1F, 1F, CCSoundEvents.jelly_block, CCSoundEvents.jelly_step, CCSoundEvents.jelly_block, CCSoundEvents.jelly_block, CCSoundEvents.jelly_step);
	public static final SoundType PUDDING = new ForgeSoundType(1F, 1F, CCSoundEvents.jelly_block, () -> SoundEvents.METAL_STEP, CCSoundEvents.jelly_block, CCSoundEvents.jelly_block, CCSoundEvents.jelly_step);
}
