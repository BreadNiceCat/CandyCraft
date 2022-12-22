package cn.breadnicecat.codeovencore;

import cn.breadnicecat.codeovencore.helper.DatagenHelper;
import cn.breadnicecat.codeovencore.helper.ItemRegisterHelper;
import net.minecraft.resources.ResourceLocation;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/21 14:50
 */
public class CodeOvenCoreInstance {
	public final String modid;
	private ItemRegisterHelper itemRegisterHelper;
	private DatagenHelper datagenHelper;

	protected CodeOvenCoreInstance(String modid) {
		this.modid = modid;
	}

	public ItemRegisterHelper getItemRegisterHelper() {
		return itemRegisterHelper == null ? (itemRegisterHelper = new ItemRegisterHelper(this)) : itemRegisterHelper;
	}

	/**
	 * note: mc运行时无论如何都不应该调用datagen方法，因为datagen事件根本就不会被命中。
	 */
	public DatagenHelper getDatagenHelper() {
		return datagenHelper == null ? (datagenHelper = new DatagenHelper(this)) : datagenHelper;
	}

	/**
	 * 添加前缀(modid:path)
	 */
	public ResourceLocation prefix(String path) {
		return new ResourceLocation(modid, path);
	}
}
