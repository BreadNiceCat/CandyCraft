package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.block.CCBlockManager;
import com.mojang.datafixers.DSL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:09
 */
public final class CCBlockEntityManager {
	/**
	 * 正确注册方块实体(TE)的方法
	 * 1.写方块类并注册
	 * 2.写TE类并注册
	 * 3.再让方块类实现EntityBlock,重写newBlockEntity方法,重写getTicker则表示TE可以tick,在服务端用 NetworkHooks.openGui打开GUI
	 * 4.写Menu类，用来表面GUI格子的具体位置
	 * 5.写Screen类，用来渲染GUI
	 */
	private static final DeferredRegister<BlockEntityType<?>> REGISTER = CandyCraft.createRegister(ForgeRegistries.BLOCK_ENTITIES);
	public static final RegistryObject<BlockEntityType<BlockEntitySugarFactory>> sugar_factory = REGISTER.register("sugar_factory", () -> BlockEntityType.Builder.of(BlockEntitySugarFactory::new, CCBlockManager.sugar_factory.block().get()).build(DSL.remainderType()));
	public static final RegistryObject<BlockEntityType<BlockEntityAdvancedSugarFactory>> advanced_sugar_factory = REGISTER.register("advanced_sugar_factory", () -> BlockEntityType.Builder.of(BlockEntityAdvancedSugarFactory::new, CCBlockManager.advanced_sugar_factory.block().get()).build(DSL.remainderType()));
	public static final RegistryObject<BlockEntityType<BlockEntityLicoriceFurnace>> licorice_furnace = REGISTER.register("licorice_furnace", () -> BlockEntityType.Builder.of(BlockEntityLicoriceFurnace::new, CCBlockManager.licorice_furnace.block().get()).build(DSL.remainderType()));
	
	public static void init() {
	}
	
	static {
		CandyCraft.clockIn();
	}
	
	
}
