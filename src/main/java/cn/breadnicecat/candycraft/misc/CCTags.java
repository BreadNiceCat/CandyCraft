package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 16:34
 */
public class CCTags {
	public static final TagKey<Block> eatable_block = BlockTags.create(prefix("eatable_block"));

	private static ResourceLocation prefix(String name) {
		return CandyCraft.CORE_INSTANCE.prefix(name);
	}
}
