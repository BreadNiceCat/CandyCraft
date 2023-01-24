package cn.breadnicecat.candycraft.block.blockentity;

import org.jetbrains.annotations.Range;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/23 14:42
 */
public interface IHasProgress {
	@Range(from = 0, to = 1)
	default float getProgressPercent() {
		return hasProgress() ? getCurrentProgress() < getMaxProgress() ? (float) getCurrentProgress() / getMaxProgress() : 1f : 0f;
	}
	
	default boolean hasProgress() {
		return getCurrentProgress() > 0;
	}
	
	int getCurrentProgress();
	
	int getMaxProgress();
	
}
