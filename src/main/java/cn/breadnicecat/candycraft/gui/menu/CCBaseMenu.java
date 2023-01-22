package cn.breadnicecat.candycraft.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:16
 */
public abstract class CCBaseMenu<T extends BlockEntity> extends AbstractContainerMenu {
    protected final Player player;
    protected final InvWrapper inventory;
    private final T blockEntity;

    @SuppressWarnings("unchecked")
    public CCBaseMenu(@Nullable MenuType<?> pMenuType, int pContainerId, Inventory playerInv, BlockPos pos) {
        super(pMenuType, pContainerId);
        this.player = playerInv.player;
        this.inventory = new InvWrapper(playerInv);
        this.blockEntity = (T) player.getLevel().getBlockEntity(pos);
        init();
    }

    protected void init() {
        addPlayerInvSlot();
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), pPlayer, blockEntity.getBlockState().getBlock());
    }

    private void addPlayerInvSlot() {
        addPlayerInvSlotRange(142, 0);
        addPlayerInvSlotRange(84, 9);
        addPlayerInvSlotRange(102, 18);
        addPlayerInvSlotRange(120, 27);
    }

    private void addPlayerInvSlotRange(int y, int indexOffset) {
        for (int i = 0; i < 9; i++) {
            addSlot(new SlotItemHandler(inventory, i + indexOffset, 8 + 18 * i, y));
        }
    }
}
