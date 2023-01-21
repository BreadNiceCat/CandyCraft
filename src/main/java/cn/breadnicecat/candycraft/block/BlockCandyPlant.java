package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.misc.CCBlockTags;
import cn.breadnicecat.candycraft.misc.CCPlantTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/20 16:28
 */
public class BlockCandyPlant extends BushBlock {
    public BlockCandyPlant(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return CCPlantTypes.CANDY_PLANT;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState pState, @NotNull LevelReader pLevel, BlockPos pPos) {
        return mayPlaceOn(pLevel.getBlockState(pPos.below()), pLevel, pPos.below());
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return pState.is(CCBlockTags.CANDY_PLANT_CAN_PLACE_ON);
    }

}
