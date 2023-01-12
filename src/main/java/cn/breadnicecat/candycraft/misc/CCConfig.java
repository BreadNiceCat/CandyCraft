package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 17:50
 */
public class CCConfig {
	public static final ForgeConfigSpec commonConfigSpec;

	public static final ForgeConfigSpec.BooleanValue canJoinCandyland;

	static {
		CandyCraft.clockIn();
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		canJoinCandyland = builder.define("canJoinCandyland", true);

		commonConfigSpec = builder.build();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfigSpec);
	}

	public static void init() {
	}
}
