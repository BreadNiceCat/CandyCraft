package cn.breadnicecat.candycraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 13:27
 */
public class Pudding extends Flour {
	public static final BooleanProperty IS_ICE_FLAVOR = BooleanProperty.create("ice_flavor");

	public Pudding(Properties pProperties) {
		super(pProperties);
		registerDefaultState(defaultBlockState().setValue(IS_ICE_FLAVOR, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(IS_ICE_FLAVOR);
	}
}