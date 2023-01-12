package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 22:18
 */
public class CCItemTagsProvider extends ItemTagsProvider {


	public CCItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(pGenerator, pBlockTagsProvider, CandyCraft.MODID, existingFileHelper);
	}

	/**
	 * copy方法:将blockTag里的方块复制到itemTag里(Copies the entries from a block tag into an item tag.)
	 */
	@Override
	protected void addTags() {
		CCDatagenManager.similarTags.forEach(this::copy);
		CCDatagenManager.itemTags.forEach((tag, items) -> {
			TagAppender<Item> appender = tag(tag);
			items.forEach(item -> appender.add(item.get()));
		});
	}
}
