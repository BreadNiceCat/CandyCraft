package cn.breadnicecat.candycraft;

import cn.breadnicecat.candycraft.block.BlockManager;
import cn.breadnicecat.candycraft.item.ItemManager;
import cn.breadnicecat.candycraft.misc.CCTab;
import cn.breadnicecat.codeovencore.CodeOvenCore;
import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/11 11:26
 */
@Mod(CandyCraft.MODID)
public class CandyCraft {
	public static final String MODID = "candycraft";
	public static final CodeOvenCoreInstance CORE_INSTANCE = CodeOvenCore.getInstance(MODID);
	public static final CreativeModeTab TAB = new CCTab();

	public CandyCraft() {
		ItemManager.init();
		BlockManager.init();
	}
}
