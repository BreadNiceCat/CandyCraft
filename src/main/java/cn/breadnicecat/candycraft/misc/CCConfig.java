package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 17:50
 */
public final class CCConfig {
    public static final ForgeConfigSpec commonConfigSpec;

    public static final ForgeConfigSpec.BooleanValue CAN_JOIN_CANDYLAND,
            CAN_RETURN_FORM_CANDYLAND;

    static {
        CandyCraft.clockIn();
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("DIM Settings");
        CAN_JOIN_CANDYLAND = builder.comment("Players can't join Candyland by portal if false.").define("can_join_candyland", true);
        CAN_RETURN_FORM_CANDYLAND = builder.comment("Players can't return from Candyland to Overworld with upgraded portal if false.").define("can_return_form_Candyland", true);
        builder.pop();
        commonConfigSpec = builder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfigSpec);
    }

    public static void init() {
    }
}
