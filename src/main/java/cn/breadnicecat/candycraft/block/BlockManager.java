package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.codeovencore.helper.BlockRegisterHelper;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 14:00
 */
@SuppressWarnings("unused")//Stupid Warning
public class BlockManager {
	private static BlockRegisterHelper helper = CandyCraft.CORE_INSTANCE.getBlockRegisterHelper();

	public static RegistryObject<Block> sugar_factory = helper.registerSimpleBlock("sugar_factory", SugerFactory::new);

	static {

	}

	public static void init() {
	}
}
