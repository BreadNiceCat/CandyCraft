package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.misc.CCBlockTags;
import cn.breadnicecat.candycraft.misc.CCPlantTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/1 13:08
 */
public class BlockCandySapling extends SaplingBlock {
    public BlockCandySapling(AbstractTreeGrower pTreeGrower, Properties pProperties) {
        super(pTreeGrower, pProperties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return CCPlantTypes.CANDY_PLANT;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return pState.is(CCBlockTags.CANDY_PLANT_CAN_PLACE_ON);
    }

    /**
     * 不应用骨粉催
     */
    @Override
    public boolean isValidBonemealTarget(@NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, boolean pIsClient) {
        return false;
    }
}
