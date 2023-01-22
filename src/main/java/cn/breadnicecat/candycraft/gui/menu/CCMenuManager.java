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

    private static final DeferredRegister<MenuType<?>> REGISTER = CandyCraft.createRegister(ForgeRegistries.CONTAINERS);
    public static final RegistryObject<MenuType<SugarFactoryMenu>> sugar_factory_menu = register("sugar_factory_menu", SugarFactoryMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return REGISTER.register(name, () -> new MenuType<>(factory));
    }

    static {
        CandyCraft.clockIn();
    }

    public static void init() {
    }
}
