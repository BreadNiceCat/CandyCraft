package cn.breadnicecat.candycraft.integration.jei;

import cn.breadnicecat.candycraft.CandyCraft;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 16:55
 */
@JeiPlugin
public class CCJEIPlugin implements IModPlugin {

	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return CandyCraft.prefix("jei");
	}
}
