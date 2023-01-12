package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 16:31
 */
public class CCTab extends CreativeModeTab {
	public CCTab() {
		super("candycrafttab");
		CCDatagenManager.langEn.put("itemGroup.candycrafttab", "CandyCraft");
	}

	@Override
	public @NotNull ItemStack makeIcon() {
		return CCItemManager.pez.get().getDefaultInstance();
	}
}
