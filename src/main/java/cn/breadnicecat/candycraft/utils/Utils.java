package cn.breadnicecat.candycraft.utils;

import net.minecraft.resources.ResourceLocation;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/17 14:17
 */
public class Utils {
	public static ResourceLocation pathExtend(ResourceLocation raw, String prefix, String postfix) {
		return new ResourceLocation(raw.getNamespace(), prefix + raw.getPath() + postfix);
	}

	public static ResourceLocation pathPrefix(ResourceLocation raw, String prefix) {
		return pathExtend(raw, prefix, "");
	}

	public static ResourceLocation pathPostfix(ResourceLocation raw, String postfix) {
		return pathExtend(raw, "", postfix);
	}
}
