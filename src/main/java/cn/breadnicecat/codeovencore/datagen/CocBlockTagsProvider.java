package cn.breadnicecat.codeovencore.datagen;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 19:34
 */
public class CocBlockTagsProvider extends BlockTagsProvider {

	private final @NotNull DatagenHelper datagenHelper;

	public CocBlockTagsProvider(@NotNull DatagenHelper datagenHelper, DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, datagenHelper.modid, existingFileHelper);
		this.datagenHelper = datagenHelper;
	}

	@Override
	protected void addTags() {
		datagenHelper.blockTags.forEach((tag, blocks) -> {
			TagAppender<Block> tagAppender = tag(tag);
			blocks.forEach(block -> tagAppender.add(block.get()));
		});
	}
}
