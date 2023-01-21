package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.data.CCDatagenManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static cn.breadnicecat.candycraft.CandyCraft.prefix;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 23:08
 */
public final class CCItemTags {
	/**
	 * 可以放进制糖机
	 */
	public static final TagKey<Item> SUGARY = create("sugary");
	public static final TagKey<Item> MARSHMALLOW_PLANKS = create("marshmallow_planks");
	public static final TagKey<Item> MARSHMALLOW_LOG = create("marshmallow_log");
	public static final TagKey<Item> LICORICE = create("licorice");
	public static final TagKey<Item> HONEYCOMB = create("honeycomb");
	public static final TagKey<Item> PEZ = create("pez");
	public static final TagKey<Item> JAWBREAKER = create("jawbreaker");
	public static final TagKey<Item> RETURN_TICKET = create("return_ticket");
	public static final TagKey<Item> CARAMEL_GLASS = create("caramel_glass");


	static {
		CCDatagenManager.similarTags.put(CCBlockTags.SUGARY_BLOCK, CCItemTags.SUGARY);//含糖方块一定含糖（废话）
	}

	public static TagKey<Item> create(String name) {
		return ItemTags.create(prefix(name));
	}
}
