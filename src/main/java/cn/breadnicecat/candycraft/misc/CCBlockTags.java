package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static cn.breadnicecat.candycraft.CandyCraft.prefix;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 16:34
 */
public class CCBlockTags {
	public static final TagKey<Block> SUGARY_BLOCK = BlockTags.create(prefix("sugary_block"));
	public static final TagKey<Block> CANDYLAND_PORTAL_FRAME = BlockTags.create(prefix("candyland_portal_frame"));
	public static final TagKey<Block> CANDY_SAPLING_CAN_PLACE_ON = BlockTags.create(prefix("candy_sapling_can_place_on"));

	public static void init() {
		CandyCraft.clockIn();
	}
}
