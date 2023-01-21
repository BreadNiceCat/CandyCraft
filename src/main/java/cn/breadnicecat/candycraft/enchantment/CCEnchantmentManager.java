package cn.breadnicecat.candycraft.enchantment;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.item.ItemFork;
import cn.breadnicecat.candycraft.utils.CommonUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/16 18:13
 */
public class CCEnchantmentManager {
	public static final DeferredRegister<Enchantment> REGISTER = CandyCraft.createRegister(Enchantment.class);

	public static EnchantmentCategory forkEnchantmentCategory = createEnchantmentCategory("fork", (item) -> item instanceof ItemFork);

	public static final RegistryObject<Enchantment> devourer = register("devourer", () -> new Devourer(Enchantment.Rarity.RARE, forkEnchantmentCategory, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}));

	public static RegistryObject<Enchantment> register(String name, Supplier<Enchantment> enchantment) {
		return register(name, CommonUtils.getEnLangByName(name), enchantment);
	}

	public static RegistryObject<Enchantment> register(String name, @Nullable String enLang, Supplier<Enchantment> enchantment) {
		if (enLang != null) CCDatagenManager.langEn.put("enchantment." + CandyCraft.MODID + "." + name, enLang);
		return REGISTER.register(name, enchantment);
	}

	public static EnchantmentCategory createEnchantmentCategory(String name, Predicate<Item> delegate) {
		EnchantmentCategory ec = EnchantmentCategory.create(name, delegate);
		CandyCraft.TAB.addEnchantmentCategories(ec);
		return ec;
	}

	public static void init() {
	}

	static {
		CandyCraft.clockIn();
	}
}
