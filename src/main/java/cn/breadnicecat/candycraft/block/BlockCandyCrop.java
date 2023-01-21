package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.misc.CCPlantTypes;
import cn.breadnicecat.candycraft.utils.CommonUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

import static net.minecraftforge.common.ForgeHooks.onCropsGrowPost;
import static net.minecraftforge.common.ForgeHooks.onCropsGrowPre;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/20 14:29
 */
public class BlockCandyCrop extends BlockCandyPlant {

    private final Supplier<Item> seed;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),//0
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),//1
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),//2
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),//3
    };

    public int getMaxAge() {
        return 7;
    }

    public BlockCandyCrop(Properties pProperties, Supplier<Item> seed) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
        this.seed = seed;
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return CCPlantTypes.CANDY_CROP;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return seed.get().getDefaultInstance();
    }

    public int getAge(BlockState b) {
        return b.getValue(AGE);
    }

    public int getStage(BlockState b) {
        return switch (getAge(b)) {
            case 0, 1 -> 0;
            case 2, 3, 4 -> 1;
            case 5, 6 -> 2;
            default -> 3;
        };
    }


    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPES[getStage(pState)];
    }

    public boolean canSurvive(@NotNull BlockState pState, @NotNull LevelReader pLevel, @NotNull BlockPos pPos) {
        return (pLevel.getRawBrightness(pPos, 0) >= 8 || pLevel.canSeeSky(pPos)) && super.canSurvive(pState, pLevel, pPos);
    }

    /**
     * [VanillaCopy]from {@link net.minecraft.world.level.block.CropBlock}
     */
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState pState, ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            int i = getAge(pState) + 1;
            if (i <= getMaxAge()) {
                if (onCropsGrowPre(pLevel, pPos, pState, CommonUtils.probability(pRandom, 20))) {
                    pLevel.setBlock(pPos, pState.setValue(AGE, i), 2);
                    onCropsGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return getAge(pState) < getMaxAge();
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return pState.is(CCBlockManager.flour_farm.block().get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
