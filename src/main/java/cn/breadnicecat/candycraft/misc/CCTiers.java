package cn.breadnicecat.candycraft.misc;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:30
 */
public class CCTiers {
	public static final Tier MARSHMALLOW = new TierImpl(0, 59, 2.0F, 0.0F, 15, ForgeHooks.getTagFromVanillaTier(Tiers.WOOD), () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS));
	public static final Tier LICORICE = new TierImpl(1, 131, 4.0F, 1.0F, 5, ForgeHooks.getTagFromVanillaTier(Tiers.STONE), () -> Ingredient.of(CCItemTags.LICORICE));
	public static final Tier HONEYCOMB = new TierImpl(2, 250, 6.0F, 2.0F, 14, ForgeHooks.getTagFromVanillaTier(Tiers.IRON), () -> Ingredient.of(CCItemTags.HONEYCOMB));
	public static final Tier PEZ = new TierImpl(3, 1561, 8.0F, 3.0F, 10, ForgeHooks.getTagFromVanillaTier(Tiers.DIAMOND), () -> Ingredient.of(CCItemTags.PEZ));
	public static final Tier JAWBREAKER = new TierImpl(4, 2031, 9.0F, 4.0F, 15, ForgeHooks.getTagFromVanillaTier(Tiers.NETHERITE), () -> Ingredient.of(CCItemTags.JAWBREAKER));

	/**
	 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
	 * @date 2022/12/23 17:37
	 */
	public static class TierImpl implements Tier {
		private final int level;
		private final int uses;
		private final float speed;
		private final float attackDamageBonus;
		private final int enchantmentValue;
		@Nullable
		private final TagKey<Block> tag;
		@NotNull
		private final Lazy<Ingredient> repairIngredient;

		/**
		 * @param level             挖掘等级
		 * @param uses              最大使用次数
		 * @param speed             速度
		 * @param attackDamageBonus 攻击伤害加成
		 * @param enchantmentValue  附魔幸运加成
		 * @param tag               可以挖掘的方块应该附带的标签
		 * @param repairIngredient  修复材料
		 */
		public TierImpl(int level, int uses, float speed, float attackDamageBonus, int enchantmentValue,
		                @Nullable TagKey<Block> tag, @NotNull Supplier<Ingredient> repairIngredient) {
			this.level = level;
			this.uses = uses;
			this.speed = speed;
			this.attackDamageBonus = attackDamageBonus;
			this.enchantmentValue = enchantmentValue;
			this.tag = tag;
			this.repairIngredient = Lazy.of(repairIngredient);
		}

		@Override
		public int getUses() {
			return this.uses;
		}

		@Override
		public float getSpeed() {
			return this.speed;
		}

		@Override
		public float getAttackDamageBonus() {
			return this.attackDamageBonus;
		}

		@Override
		@Deprecated
		public int getLevel() {
			return this.level;
		}

		@Override
		public int getEnchantmentValue() {
			return this.enchantmentValue;
		}

		@Nullable
		@Override
		public TagKey<Block> getTag() {
			return this.tag;
		}

		@NotNull
		@Override
		public Ingredient getRepairIngredient() {
			return this.repairIngredient.get();
		}


	}

}
