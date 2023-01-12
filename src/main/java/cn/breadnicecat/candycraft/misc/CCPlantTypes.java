package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraftforge.common.PlantType;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/11 15:16
 */
public class CCPlantTypes {
	public static PlantType CandyPlain = PlantType.get("candy_plain");

	static {
		CandyCraft.clockIn();
	}

	public static void init() {
	}
}
