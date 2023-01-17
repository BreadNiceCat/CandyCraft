package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.utils.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/16 22:24
 */
public final class CCDIMs {
	public static final ResourceKey<Level> CANDYLAND = ResourceKey.create(Registry.DIMENSION_REGISTRY, CandyCraft.prefix("candyland"));
	public static final ResourceKey<DimensionType> CANDYLAND_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, Utils.pathPostfix(CANDYLAND.getRegistryName(), "_type"));

	public static void init() {
	}

	static {
		CandyCraft.clockIn(CCDIMs.class);
	}
}
