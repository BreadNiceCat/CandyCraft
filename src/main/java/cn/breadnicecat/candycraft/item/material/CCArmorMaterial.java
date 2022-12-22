package cn.breadnicecat.candycraft.item.material;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.item.ItemManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:15
 */
public class CCArmorMaterial implements ArmorMaterial {
	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	public static CCArmorMaterial PEZ = new CCArmorMaterial("pez", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(ItemManager.pez.get()));
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredient;

	public CCArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = Lazy.of(repairIngredient);
	}

	@Override
	public int getDurabilityForSlot(@NotNull EquipmentSlot pSlot) {
		return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(@NotNull EquipmentSlot pSlot) {
		return slotProtections[pSlot.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	@Override
	public @NotNull SoundEvent getEquipSound() {
		return sound;
	}

	@Override
	public @NotNull Ingredient getRepairIngredient() {
		return repairIngredient.get();
	}

	@Override
	public @NotNull String getName() {
		return CandyCraft.CORE_INSTANCE.prefix(name).toString();
	}

	@Override
	public float getToughness() {
		return toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}
}
