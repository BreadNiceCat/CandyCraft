package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.block.blockentity.handle.CCItemStackHandler;
import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:08
 */
public class BlockEntitySugarFactory extends BlockEntity implements Container {
    protected final BiConsumer<CCItemStackHandler, Integer> onChanged = (handler, slot) -> setChanged();
    protected final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    protected int maxProcessedTick = (int) TickUnit.toTick(TimeUnit.SECONDS, 10);
    protected int processedTick = 0;
    protected boolean isWorking = false;

    public BlockEntitySugarFactory(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public BlockEntitySugarFactory(BlockPos pPos, BlockState pBlockState) {
        this(CCBlockEntityManager.sugar_factory.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("input", items.get(1).serializeNBT());
        pTag.put("output", items.get(2).serializeNBT());
        pTag.putInt("processedTick", processedTick);
        pTag.putBoolean("isWorking", isWorking);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("input")) items.get(1).deserializeNBT(pTag.getCompound("input"));
        if (pTag.contains("output")) items.get(2).deserializeNBT(pTag.getCompound("output"));
        if (pTag.contains("processedTick")) processedTick = pTag.getInt("processedTick");
        if (pTag.contains("isWorking")) isWorking = pTag.getBoolean("isWorking");
    }

    public void tick() {
        checkIsWorking();
        if (isWorking) {
            processedTick++;
        }
    }


    private void checkIsWorking() {

    }

    //Container
    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack i : items) {
            if (!i.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return items.get(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(items, pSlot, pAmount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(items, pSlot);
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        //TODO
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return pPlayer.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        items.clear();
    }
}
