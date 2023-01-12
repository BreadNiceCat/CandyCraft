package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static cn.breadnicecat.candycraft.CandyCraft.prefix;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 23:08
 */
public class CCItemTags {
	/**
	 * 可以放进制糖机
	 */
	public static final TagKey<Item> SUGARY = create("sugary");
	public static final TagKey<Item> CANDY_PLANK = create("candy_plank");

	public static void init() {
	}

	static {
		CandyCraft.clockIn();
		CCDatagenManager.similarTags.put(CCBlockTags.SUGARY_BLOCK, CCItemTags.SUGARY);//含糖方块一定含糖（废话）
	}

	public static TagKey<Item> create(String name) {
		return ItemTags.create(prefix(name));
	}
}
