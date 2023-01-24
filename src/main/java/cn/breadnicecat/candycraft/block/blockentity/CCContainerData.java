package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.utils.Accessor;
import net.minecraft.world.inventory.ContainerData;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 20:34
 */
public class CCContainerData implements ContainerData {
	
	private final Accessor<Integer>[] accessors;
	
	@SafeVarargs
	public CCContainerData(Accessor<Integer>... accessors) {
		this.accessors = accessors;
	}
	
	@Override
	public int get(int pIndex) {
		return accessors[pIndex].get();
	}
	
	@Override
	public void set(int pIndex, int pValue) {
		accessors[pIndex].set(pValue);
	}
	
	@Override
	public int getCount() {
		return accessors.length;
	}
}
