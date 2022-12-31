package cn.breadnicecat.candycraft.block;

import net.minecraft.world.level.block.Block;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 14:06
 */
public class SugerFactory extends Block {
	public SugerFactory() {
		super(BlockManager.newBlockProp().requiresCorrectToolForDrops());
	}
	//TODO GUI
}
