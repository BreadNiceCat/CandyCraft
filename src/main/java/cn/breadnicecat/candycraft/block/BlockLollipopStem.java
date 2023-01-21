package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.utils.CommonUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/21 16:48
 */
public class BlockLollipopStem extends BlockCandyCrop {
    private static VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Shapes.block()
    };

    public BlockLollipopStem(Properties pProperties, Supplier<Item> seed) {
        super(pProperties, seed);
    }

    @Override
    public int getStage(BlockState b) {
        return switch (getAge(b)) {
            case 0, 1, 2, 3 -> 0;
            case 4, 5, 6 -> 1;
            default -> 2;
        };
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPES[getStage(pState)];
    }

    @Override
    public void randomTick(@NotNull BlockState pState, ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        if (getStage(pState) >= 2) {
            BlockState loll = CCBlockManager.lollipop_block.block().get().defaultBlockState();
            BlockPos above = pPos.above();
            if (pLevel.getBlockState(above).isAir() && canSurvive(loll, pLevel, pPos)) {
                if (CommonUtils.probability(pRandom, 20)) {
                    pLevel.setBlock(above, loll, 2);
                }
            }
        } else {
            super.randomTick(pState, pLevel, pPos, pRandom);
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }

}
