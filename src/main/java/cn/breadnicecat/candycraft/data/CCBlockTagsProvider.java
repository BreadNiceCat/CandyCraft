package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 19:34
 */
public class CCBlockTagsProvider extends BlockTagsProvider {


	public CCBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, CandyCraft.MODID, existingFileHelper);

	}

	@Override
	protected void addTags() {
		CCDatagenManager.blockTags.forEach((tag, blocks) -> {
			TagAppender<Block> tagAppender = tag(tag);
			blocks.forEach(block -> tagAppender.add(block.get()));
		});
	}
}
