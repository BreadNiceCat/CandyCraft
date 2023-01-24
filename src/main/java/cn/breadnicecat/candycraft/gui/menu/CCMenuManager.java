package cn.breadnicecat.candycraft.gui.menu;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:17
 */
public class CCMenuManager {
	/**
	 * Screen需要在事件里注册
	 */
	private static final DeferredRegister<MenuType<?>> REGISTER = CandyCraft.createRegister(ForgeRegistries.CONTAINERS);
	public static final RegistryObject<MenuType<SugarFactoryMenu>> sugar_factory_menu = register("sugar_factory_menu", SugarFactoryMenu::new);
	public static final RegistryObject<MenuType<AdvancedSugarFactoryMenu>> advanced_sugar_factory_menu = register("advanced_sugar_factory_menu", AdvancedSugarFactoryMenu::new);
	public static final RegistryObject<MenuType<LicoriceFurnaceMenu>> licorice_furnace_menu = register("licorice_furnace_menu", LicoriceFurnaceMenu::new);
	
	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
		return REGISTER.register(name, () -> new MenuType<>(factory));
	}
	
	static {
		CandyCraft.clockIn();
	}
	
	public static void init() {
	}
}
