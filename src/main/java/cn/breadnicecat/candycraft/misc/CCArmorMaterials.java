package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.block.CCBlockManager;
import cn.breadnicecat.candycraft.item.CCItemManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static cn.breadnicecat.candycraft.CandyCraft.prefix;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:15
 */
public class CCArmorMaterials {
	public static final ArmorMaterial JELLY_CROWN = new ArmorMaterialImpl("jelly_crown", 15, new int[]{4, 0, 0, 0}, 20, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.EMPTY);
	public static final ArmorMaterial TRAMPOJELLY_BOOTS = new ArmorMaterialImpl("trampojelly_boots", 10, new int[4], 8, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.of(CCItemManager.cranberry_scale.get()));

	public static final ArmorMaterial WATER_MASK = new ArmorMaterialImpl("water_mask", 10, new int[4], 8, SoundEvents.ARMOR_EQUIP_TURTLE, 0F, 0F, () -> Ingredient.of(CCBlockManager.jelly_shock_absorber.block().get()));
	public static final ArmorMaterial LICORICE = new ArmorMaterialImpl("licorice", 13, new int[]{1, 4, 5, 2}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(CCItemTags.LICORICE));
	public static final ArmorMaterial HONEYCOMB = new ArmorMaterialImpl("honeycomb", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(CCItemTags.HONEYCOMB));
	public static final ArmorMaterial PEZ = new ArmorMaterialImpl("pez", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(CCItemTags.PEZ));
	public static final ArmorMaterial JAWBREAKER = new ArmorMaterialImpl("jawbreaker", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(CCItemTags.JAWBREAKER));

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/23 17:34
	 */
	public static class ArmorMaterialImpl implements ArmorMaterial {

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
			return prefix(name).toString();//前面加上modid：就可以修改盔甲贴图路径了
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


}