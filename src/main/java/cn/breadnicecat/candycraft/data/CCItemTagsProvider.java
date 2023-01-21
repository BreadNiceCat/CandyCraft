package cn.breadnicecat.candycraft.data;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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

	@SuppressWarnings("deprecation")
	@Override
	protected void copy(@NotNull TagKey<Block> pBlockTag, @NotNull TagKey<Item> pItemTag) {
		Tag.Builder itemB = this.getOrCreateRawBuilder(pItemTag);
		Tag.Builder blockB = super.blockTags.apply(pBlockTag);
		blockB.getEntries().forEach(pEntry -> {
			Tag.Entry entry = pEntry.entry();
			if (entry instanceof Tag.ElementEntry eleE) {//是否是必要的条目
				if (eleE.verifyIfPresent(
						pName -> {//其blockitem是否存在
							Optional<Item> itemOptional = Registry.ITEM.getOptional(pName);
							return itemOptional.isPresent() && itemOptional.get() instanceof BlockItem;
						}, (r) -> true)
				) {//若存在：则copy
					itemB.add(pEntry);
				} else {
					CandyCraft.getLogger().warn("跳过注册itemTag的条目:{}。原因是其对应的BlockItem不存在。也许是其BlockItem的注册名与Block的不一致？", pEntry);
				}
			} else {
				CandyCraft.getLogger().warn("跳过注册itemTag的条目:{}。原因是其方块条目不是必要的或不是具体的方块。", pEntry);
			}
		});
	}
}
