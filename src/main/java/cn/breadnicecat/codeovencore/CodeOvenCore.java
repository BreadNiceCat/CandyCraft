package cn.breadnicecat.codeovencore;

import java.util.HashMap;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/21 14:41
 */
//@Mod(CodeOvenCore.MODID)
public class CodeOvenCore {
	public static final String MODID = "codeovencore";
	private static final HashMap<String, CodeOvenCoreInstance> INSTANCES = new HashMap<>();

	public static CodeOvenCoreInstance getInstance(String modid) {
		return INSTANCES.computeIfAbsent(modid, CodeOvenCoreInstance::new);
	}
}
