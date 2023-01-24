package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.item.CCItemManager;
import cn.breadnicecat.candycraft.utils.UndimodifiableObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 16:31
 */
public final class CCTab extends CreativeModeTab {
	
	private HashSet<EnchantmentCategory> enchantmentCategories = new HashSet<>();
	
	public CCTab() {
		super("candycrafttab");
	}
	
	@Override
	public @NotNull ItemStack makeIcon() {
		return CCItemManager.pez.get().getDefaultInstance();
	}
	
	public @NotNull CreativeModeTab addEnchantmentCategories(@NotNull EnchantmentCategory... pEnchantmentCategories) {
		Collections.addAll(enchantmentCategories, pEnchantmentCategories);
		return super.setEnchantmentCategories(enchantmentCategories.toArray(new EnchantmentCategory[0]));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void fillItemList(@NotNull NonNullList<ItemStack> pItems) {
		UndimodifiableObject<EnchantedBookItem> bk = new UndimodifiableObject<>();
		for (Item item : Registry.ITEM) {
			if (item instanceof EnchantedBookItem bki) {
				bk.set(bki);
			} else {
				item.fillItemCategory(this, pItems);
			}
		}
		//附魔书应该在最后
		bk.get().fillItemCategory(this, pItems);
	}
}
