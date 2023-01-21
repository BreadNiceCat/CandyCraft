package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 12:01
 */
public class BlockFlourFarmland extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    public static final int MAX_MOISTURE = 7;
    protected static final VoxelShape FARM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static final Supplier<BlockState> backToState = () -> CCBlockManager.flour.block().get().defaultBlockState();

    public BlockFlourFarmland(Properties pProperties) {
        super(pProperties);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        int i = pState.getValue(MOISTURE);
        if (!isNearWater(pLevel, pPos) && !pLevel.isRainingAt(pPos.above())) {
            if (i > 0) {
                pLevel.setBlock(pPos, pState.setValue(MOISTURE, i - 1), 2);
            } else {
                turnBack(pState, pLevel, pPos);
            }
        } else if (i < 7) {
            pLevel.setBlock(pPos, pState.setValue(MOISTURE, MAX_MOISTURE), 2);
        }
    }

    @Override
    public void fallOn(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos, @NotNull Entity pEntity, float pFallDistance) {
        if (!pLevel.isClientSide && ForgeHooks.onFarmlandTrample(pLevel, pPos, backToState.get(), pFallDistance, pEntity)) { // Forge: Move logic to Entity#canTrample
            turnBack(pState, pLevel, pPos);
        }
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    public void turnBack(BlockState pState, @NotNull Level pLevel, BlockPos pPos) {
        pLevel.setBlockAndUpdate(pPos, pushEntitiesUp(pState, backToState.get(), pLevel, pPos));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return backToState.get().getBlock().asItem().getDefaultInstance();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(@NotNull BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull PathComputationType pType) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return FARM_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean useShapeForLightOcclusion(@NotNull BlockState pState) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState updateShape(@NotNull BlockState pState, @NotNull Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        if (pFacing == Direction.UP && !pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return !this.defaultBlockState().canSurvive(pContext.getLevel(), pContext.getClickedPos()) ? Blocks.DIRT.defaultBlockState() : super.getStateForPlacement(pContext);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRand) {
        if (!pState.canSurvive(pLevel, pPos)) {
            turnBack(pState, pLevel, pPos);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
        pBuilder.add(MOISTURE);
    }

    private boolean isNearWater(LevelReader pLevel, @NotNull BlockPos pPos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, 0, -4), pPos.offset(4, 1, 4))) {
            if (pLevel.getFluidState(blockpos).is(FluidTags.WATER)) {
                return true;
            }
        }
        return FarmlandWaterManager.hasBlockWaterTicket(pLevel, pPos);
    }
}

