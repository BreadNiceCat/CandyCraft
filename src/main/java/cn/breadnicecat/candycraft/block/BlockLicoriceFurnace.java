package cn.breadnicecat.candycraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/10 16:45
 */

public class BlockLicoriceFurnace extends AbstractFurnaceBlock {
    public BlockLicoriceFurnace(Properties properties) {
        super(properties);
    }

    @Override
    protected void openContainer(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return null;
    }
}
