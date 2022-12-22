package cn.breadnicecat.candycraft.item.material;

import cn.breadnicecat.candycraft.item.ItemManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/22 21:30
 */
public class CCToolTier implements Tier {
	public static final CCToolTier PEZ = new CCToolTier(3, 1561, 8.0F, 3.0F, 10, ForgeHooks.getTagFromVanillaTier(Tiers.DIAMOND), () -> Ingredient.of(ItemManager.pez.get()));

	private final int level;
	private final int uses;
	private final float speed;
	private final float attackDamageBonus;
	private final int enchantmentValue;
	@Nonnull
	private final TagKey<Block> tag;
	@Nonnull
	private final Lazy<Ingredient> repairIngredient;

	public CCToolTier(int level, int uses, float speed, float attackDamageBonus, int enchantmentValue,
	                  @Nonnull TagKey<Block> tag, @Nonnull Supplier<Ingredient> repairIngredient) {
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
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Nonnull
	public TagKey<Block> getTag() {
		return this.tag;
	}

	@Nonnull
	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

}
