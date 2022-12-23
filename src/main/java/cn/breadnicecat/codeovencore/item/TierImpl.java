package cn.breadnicecat.codeovencore.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/23 17:37
 */
public class TierImpl implements Tier {
	private final int level;
	private final int uses;
	private final float speed;
	private final float attackDamageBonus;
	private final int enchantmentValue;
	@Nonnull
	private final TagKey<Block> tag;
	@Nonnull
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
	                @Nullable TagKey<Block> tag, @Nonnull Supplier<Ingredient> repairIngredient) {
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

	@Nullable
	public TagKey<Block> getTag() {
		return this.tag;
	}

	@Nonnull
	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}


}
