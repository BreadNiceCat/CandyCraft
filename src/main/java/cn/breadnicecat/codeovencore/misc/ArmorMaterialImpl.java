package cn.breadnicecat.codeovencore.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 17:34
 */
public class ArmorMaterialImpl implements ArmorMaterial {

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredient;

	/**
	 * @param name                 名字
	 * @param durabilityMultiplier 耐久基数，四个栏位分别乘11，15，16，11
	 * @param slotProtections      四个栏位分别提供的护甲值 [4]
	 * @param enchantmentValue     附魔幸运加成
	 * @param sound                穿上时的声音
	 * @param toughness            盔甲韧性
	 * @param knockbackResistance  抗击退
	 * @param repairIngredient     修复材料
	 */
	public ArmorMaterialImpl(String name, int durabilityMultiplier, @Nonnull int[] slotProtections, int enchantmentValue,
	                         SoundEvent sound, float toughness, float knockbackResistance,
	                         @Nonnull Supplier<Ingredient> repairIngredient) {
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
