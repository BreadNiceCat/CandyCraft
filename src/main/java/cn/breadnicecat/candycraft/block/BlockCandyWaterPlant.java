package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/20 22:17
 */
public class BlockCandyWaterPlant extends BlockCandyPlant implements LiquidBlockContainer {
    boolean a, b;

    public BlockCandyWaterPlant(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return pLevel.getFluidState(pPos.above()).is(Fluids.WATER) && super.mayPlaceOn(pState, pLevel, pPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull FluidState getFluidState(@NotNull BlockState p_154537_) {
        return Fluids.WATER.defaultFluidState();
    }


    @Override
    public boolean canPlaceLiquid(@NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(@NotNull LevelAccessor pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull FluidState pFluidState) {
        return false;
    }
}
