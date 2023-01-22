package cn.breadnicecat.candycraft.block.blockentity.handle;

import net.minecraftforge.items.ItemStackHandler;

import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 12:28
 */
public class CCItemStackHandler extends ItemStackHandler {

    private BiConsumer<CCItemStackHandler, Integer> onChanged;

    public CCItemStackHandler(BiConsumer<CCItemStackHandler, Integer> onChanged) {
        this(1, onChanged);
    }

    public CCItemStackHandler(int slotSize, BiConsumer<CCItemStackHandler, Integer> onChanged) {
        super(slotSize);
        this.onChanged = onChanged;
    }

    @Override
    protected void onContentsChanged(int slot) {
        onChanged.accept(this, slot);
    }
}
