package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/21 18:07
 */
public class BlockLollipop extends BlockCandyPlant {

    private final Supplier<Item> seed;

    public BlockLollipop(Properties pProperties, Supplier<Item> seed) {
        super(pProperties);
        this.seed = seed;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return seed.get().getDefaultInstance();
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        BlockState below = pLevel.getBlockState(pPos.below());
        BlockLollipopStem block = CCBlockManager.lollipop_stem.block().get();
        return below.is(block) && below.getValue(BlockLollipopStem.AGE) >= block.getMaxAge();
    }
}
