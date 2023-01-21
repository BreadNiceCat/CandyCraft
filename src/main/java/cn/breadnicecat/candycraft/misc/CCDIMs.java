package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;


/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/16 22:24
 */
public final class CCDIMs {
    public static final int CANDYLAND_HEIGHT = 384, CANDYLAND_MIN_Y = -64, CANDYLAND_MAX_Y = CANDYLAND_MIN_Y + CANDYLAND_HEIGHT;
    public static final ResourceKey<Level> CANDYLAND = ResourceKey.create(Registry.DIMENSION_REGISTRY, CandyCraft.prefix("candyland"));
}
