package cn.breadnicecat.candycraft.block.blockentity;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 12:28
 */
public class CCItemStackHandler extends ItemStackHandler implements Iterable<ItemStack> {
	
	private BiConsumer<CCItemStackHandler, Integer> onChanged;
	
	public CCItemStackHandler(BiConsumer<CCItemStackHandler, Integer> onChanged) {
		this(1, onChanged);
	}
	
	public CCItemStackHandler(int slotSize, BiConsumer<CCItemStackHandler, Integer> onChanged) {
		super(slotSize);
		this.onChanged = onChanged;
	}
	
	public CCItemStackHandler(int slotSize, Runnable onChanged) {
		super(slotSize);
		this.onChanged = (a, b) -> onChanged.run();
	}
	
	public boolean isEmpty(int slot) {
		return getStackInSlot(slot).isEmpty();
	}
	
	public List<ItemStack> getAllItems() {
		ArrayList<ItemStack> list = new ArrayList<>();
		forEach(list::add);
		return list;
	}
	
	public boolean isEmpty() {
		for (ItemStack stack : this) {
			if (stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public ItemStack removeItem(int slot) {
		ItemStack stack = stacks.set(slot, ItemStack.EMPTY);
		onContentsChanged(slot);
		return stack;
	}
	
	@Override
	protected void onContentsChanged(int slot) {
		onChanged.accept(this, slot);
	}
	
	@NotNull
	@Override
	public ListIterator<ItemStack> iterator() {
		return iterator(0);
	}
	
	public ListIterator<ItemStack> iterator(int slotBegin) {
		return new ListIterator<ItemStack>() {
			private int curSlot = slotBegin;
			
			@Override
			public boolean hasNext() {
				return curSlot + 1 < getSlots();
			}
			
			@Override
			public ItemStack next() {
				return getStackInSlot(curSlot++);//先获取，再+1
			}
			
			@Override
			public boolean hasPrevious() {
				return curSlot > 0;
			}
			
			@Override
			public ItemStack previous() {
				return getStackInSlot(curSlot--);
			}
			
			@Override
			public int nextIndex() {
				return curSlot + 1;
			}
			
			@Override
			public int previousIndex() {
				return curSlot - 1;
			}
			
			@Override
			public void remove() {
				set(ItemStack.EMPTY);
			}
			
			@Override
			public void set(ItemStack stack) {
				setStackInSlot(curSlot, stack);
			}
			
			@Override
			public void add(ItemStack stack) {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	public void clearAll() {
		stacks.clear();
		onContentsChanged(-1);
	}
}
