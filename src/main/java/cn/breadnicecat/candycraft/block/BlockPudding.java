package cn.breadnicecat.candycraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 13:27
 */
public class BlockPudding extends Block {
    public static final BooleanProperty IS_ICE_FLAVOR = BooleanProperty.create("ice_flavor");

    public BlockPudding(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        registerDefaultState(this.stateDefinition.any().setValue(IS_ICE_FLAVOR, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_ICE_FLAVOR);
    }
}
