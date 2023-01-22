package cn.breadnicecat.candycraft.misc;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.data.CCDatagenManager;
import cn.breadnicecat.candycraft.data.recipe.CaramelPortalRecipeBuilder;
import cn.breadnicecat.candycraft.utils.CommonUtils;
import cn.breadnicecat.candycraft.utils.ItemUtils;
import cn.breadnicecat.candycraft.utils.Pair;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static cn.breadnicecat.candycraft.block.CCBlockManager.*;
import static cn.breadnicecat.candycraft.item.CCItemManager.*;
import static cn.breadnicecat.candycraft.utils.Utils.hasItemTags;
import static cn.breadnicecat.candycraft.utils.Utils.pathPostfix;
import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/19 10:23
 */
public class CCRecipes {
    private static HashSet<ResourceLocation> recipes = new HashSet<>();

    static {
        CandyCraft.clockIn();
        //工作台
        {
            Pair<String, Supplier<CriterionTriggerInstance>> has_any_marshmallow_log = criterion("has_any_marshmallow_log", () -> hasItemTags(CCItemTags.MARSHMALLOW_LOG)),
                    has_any_marshmallow_planks = criterion("has_any_marshmallow_planks", () -> hasItemTags(CCItemTags.MARSHMALLOW_PLANKS)),
                    has_licorice = criterion("has_licorice", () -> hasItemTags(CCItemTags.LICORICE)),
                    has_honeycomb = criterion("has_honeyconb", () -> hasItemTags(CCItemTags.HONEYCOMB)),
                    has_pez = criterion("has_pez", () -> hasItemTags(CCItemTags.PEZ)),
                    has_jawbreaker = criterion("has_jawbreaker", () -> hasItemTags(CCItemTags.JAWBREAKER)),
                    has_any_caramel_glass = criterion("has_any_caramel_glass", () -> hasItemTags(CCItemTags.CARAMEL_GLASS)),
                    has_nougat_powder = criterion("has_nougat_powder", () -> hasItems(nougat_powder.get())),
                    has_candy_cane = criterion("has_candy_cane", () -> hasItems(candy_cane.get()));
            Collections.addAll(CCDatagenManager.recipes,//写着方便
                    shapeless(sugar_block.block()::get, 1, () -> new Ingredient[]{Ingredient.of(Items.SUGAR)}, new int[]{4}, criterion("has_sugar", () -> hasItems(Items.SUGAR))),
                    //棉花软糖
                    shapeless(marshmallow_planks.block()::get, 4, () -> Ingredient.of(marshmallow_log.block().get()), 1, has_any_marshmallow_log),
                    shapeless(light_marshmallow_planks.block()::get, 4, () -> Ingredient.of(light_marshmallow_log.block().get()), 1, has_any_marshmallow_log),
                    shapeless(dark_marshmallow_planks.block()::get, 4, () -> Ingredient.of(dark_marshmallow_log.block().get()), 1, has_any_marshmallow_log),
                    shaped(marshmallow_stick::get, 1, "#", "#", "", "#", () -> new Ingredient[]{Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS)}, has_any_marshmallow_planks),
                    sword(marshmallow_sword::get, () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS), () -> Ingredient.of(marshmallow_stick.get()), has_any_marshmallow_planks),
                    axe(marshmallow_axe::get, () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS), () -> Ingredient.of(marshmallow_stick.get()), has_any_marshmallow_planks),
                    pickaxe(marshmallow_pickaxe::get, () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS), () -> Ingredient.of(marshmallow_stick.get()), has_any_marshmallow_planks),
                    hoe(marshmallow_hoe::get, () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS), () -> Ingredient.of(marshmallow_stick.get()), has_any_marshmallow_planks),
                    shovel(marshmallow_shovel::get, () -> Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS), () -> Ingredient.of(marshmallow_stick.get()), has_any_marshmallow_planks),
                    shaped(marshmallow_crafting_table.block()::get, 1, "##", "##", "", "#", () -> new Ingredient[]{Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS)}, has_any_marshmallow_planks),
                    shaped(marshmallow_door.block()::get, 1, "##", "##", "##", "#", () -> new Ingredient[]{Ingredient.of(CCItemTags.MARSHMALLOW_PLANKS)}, has_any_marshmallow_planks),
                    //盐甘草糖
                    shapeless(licorice_block.block()::get, 1, () -> Ingredient.of(licorice.get()), 9, has_licorice),
                    shaped(licorice_furnace.block()::get, 1, "###", "# #", "###", "#", () -> new Ingredient[]{Ingredient.of(licorice.get())}, has_licorice),
                    shaped(licorice_brick.block()::get, 4, "##", "##", "", "#", () -> new Ingredient[]{Ingredient.of(licorice_block.block().get())}, has_licorice),
                    sword(licorice_sword::get, () -> Ingredient.of(CCItemTags.LICORICE), () -> Ingredient.of(marshmallow_stick.get()), has_licorice),
                    axe(licorice_axe::get, () -> Ingredient.of(CCItemTags.LICORICE), () -> Ingredient.of(marshmallow_stick.get()), has_licorice),
                    pickaxe(licorice_pickaxe::get, () -> Ingredient.of(CCItemTags.LICORICE), () -> Ingredient.of(marshmallow_stick.get()), has_licorice),
                    hoe(licorice_hoe::get, () -> Ingredient.of(CCItemTags.LICORICE), () -> Ingredient.of(marshmallow_stick.get()), has_licorice),
                    shovel(licorice_shovel::get, () -> Ingredient.of(CCItemTags.LICORICE), () -> Ingredient.of(marshmallow_stick.get()), has_licorice),
                    helmet(licorice_helmet::get, () -> Ingredient.of(CCItemTags.LICORICE), has_licorice),
                    chestplate(licorice_chestplate::get, () -> Ingredient.of(CCItemTags.LICORICE), has_licorice),
                    leggings(licorice_leggings::get, () -> Ingredient.of(CCItemTags.LICORICE), has_licorice),
                    boots(licorice_boots::get, () -> Ingredient.of(CCItemTags.LICORICE), has_licorice),
                    //蜜蜡
                    shapeless(honeycomb_block.block()::get, 1, () -> Ingredient.of(honeycomb.get()), 9, has_honeycomb),
                    shapeless(honeycomb_lamp.block()::get, 1, () -> new Ingredient[]{Ingredient.of(honeycomb_torch.block().get()), Ingredient.of(honeycomb_block.block().get())}, new int[]{1, 1}, has_honeycomb),
                    shaped(honeycomb_torch.block()::get, 1, "@", "/", "", "@/", () -> new Ingredient[]{Ingredient.of(CCItemTags.HONEYCOMB), Ingredient.of(marshmallow_stick.get())}, has_honeycomb),
                    sword(honeycomb_sword::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), () -> Ingredient.of(marshmallow_stick.get()), has_honeycomb),
                    axe(honeycomb_axe::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), () -> Ingredient.of(marshmallow_stick.get()), has_honeycomb),
                    pickaxe(honeycomb_pickaxe::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), () -> Ingredient.of(marshmallow_stick.get()), has_honeycomb),
                    hoe(honeycomb_hoe::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), () -> Ingredient.of(marshmallow_stick.get()), has_honeycomb),
                    shovel(honeycomb_shovel::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), () -> Ingredient.of(marshmallow_stick.get()), has_honeycomb),
                    helmet(honeycomb_helmet::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), has_honeycomb),
                    chestplate(honeycomb_chestplate::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), has_honeycomb),
                    leggings(honeycomb_leggings::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), has_honeycomb),
                    boots(honeycomb_boots::get, () -> Ingredient.of(CCItemTags.HONEYCOMB), has_honeycomb),
                    //皮礼士糖
                    sword(pez_sword::get, () -> Ingredient.of(CCItemTags.PEZ), () -> Ingredient.of(marshmallow_stick.get()), has_pez),
                    axe(pez_axe::get, () -> Ingredient.of(CCItemTags.PEZ), () -> Ingredient.of(marshmallow_stick.get()), has_pez),
                    pickaxe(pez_pickaxe::get, () -> Ingredient.of(CCItemTags.PEZ), () -> Ingredient.of(marshmallow_stick.get()), has_pez),
                    hoe(pez_hoe::get, () -> Ingredient.of(CCItemTags.PEZ), () -> Ingredient.of(marshmallow_stick.get()), has_pez),
                    shovel(pez_shovel::get, () -> Ingredient.of(CCItemTags.PEZ), () -> Ingredient.of(marshmallow_stick.get()), has_pez),
                    helmet(pez_helmet::get, () -> Ingredient.of(CCItemTags.PEZ), has_pez),
                    chestplate(pez_chestplate::get, () -> Ingredient.of(CCItemTags.PEZ), has_pez),
                    leggings(pez_leggings::get, () -> Ingredient.of(CCItemTags.PEZ), has_pez),
                    boots(pez_boots::get, () -> Ingredient.of(CCItemTags.PEZ), has_pez),
                    shaped(fork::get, 1, "  #", " #", "#", "#", () -> new Ingredient[]{Ingredient.of(pez.get())}, has_pez),
                    shapeless(pez_dust::get, 1, () -> Ingredient.of(pez.get()), 1, has_pez),
                    shapeless(pez_block.block()::get, 1, () -> Ingredient.of(pez_dust.get()), 9, has_pez),
                    //Jawbreaker
                    sword(jawbreaker_sword::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), () -> Ingredient.of(marshmallow_stick.get()), has_jawbreaker),
                    axe(jawbreaker_axe::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), () -> Ingredient.of(marshmallow_stick.get()), has_jawbreaker),
                    pickaxe(jawbreaker_pickaxe::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), () -> Ingredient.of(marshmallow_stick.get()), has_jawbreaker),
                    hoe(jawbreaker_hoe::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), () -> Ingredient.of(marshmallow_stick.get()), has_jawbreaker),
                    shovel(jawbreaker_shovel::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), () -> Ingredient.of(marshmallow_stick.get()), has_jawbreaker),
                    helmet(jawbreaker_helmet::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), has_jawbreaker),
                    chestplate(jawbreaker_chestplate::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), has_jawbreaker),
                    leggings(jawbreaker_leggings::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), has_jawbreaker),
                    boots(jawbreaker_boots::get, () -> Ingredient.of(CCItemTags.JAWBREAKER), has_jawbreaker),
                    //
                    shaped(trampojelly_boots::get, 1, "", "@ @", "# #", "@#", () -> new Ingredient[]{Ingredient.of(sugar_crystal.get()), Ingredient.of(jelly_shock_absorber.block().get())}, criterion("has_sugar_crystal_and_jelly_shock_absorber", () -> hasItems(sugar_crystal.get(), jelly_shock_absorber.block().get()))),
                    shaped(water_mask::get, 1, "###", "#@#", "", "#@", () -> new Ingredient[]{Ingredient.of(sugar_crystal.get()), Ingredient.of(cranberry_scale.get())}, criterion("has_sugar_crystal_and_cranberry_scale", () -> hasItems(sugar_crystal.get(), cranberry_scale.get()))),
                    //玻璃
                    shapeless(round_caramel_glass.block()::get, 1, () -> Ingredient.of(caramel_glass.block().get()), 1, has_any_caramel_glass),
                    shapeless(diamond_caramel_glass.block()::get, 1, () -> Ingredient.of(round_caramel_glass.block().get()), 1, has_any_caramel_glass),
                    shapeless(caramel_glass.block()::get, 1, () -> Ingredient.of(diamond_caramel_glass.block().get()), 1, has_any_caramel_glass),
                    shaped(caramel_glass_pane.block()::get, 16, "###", "###", "", "#", () -> new Ingredient[]{Ingredient.of(caramel_glass.block().get())}, has_any_caramel_glass),
                    shaped(round_caramel_glass_pane.block()::get, 16, "###", "###", "", "#", () -> new Ingredient[]{Ingredient.of(round_caramel_glass.block().get())}, has_any_caramel_glass),
                    shaped(diamond_caramel_glass_pane.block()::get, 16, "###", "###", "", "#", () -> new Ingredient[]{Ingredient.of(diamond_caramel_glass.block().get())}, has_any_caramel_glass),
                    shapeless(round_caramel_glass_pane.block()::get, 1, () -> Ingredient.of(caramel_glass_pane.block().get()), 1, has_any_caramel_glass),
                    shapeless(diamond_caramel_glass_pane.block()::get, 1, () -> Ingredient.of(round_caramel_glass_pane.block().get()), 1, has_any_caramel_glass),
                    shapeless(caramel_glass_pane.block()::get, 1, () -> Ingredient.of(diamond_caramel_glass_pane.block().get()), 1, has_any_caramel_glass),
                    //
                    shapeless(candy_cane_block.block()::get, 1, () -> Ingredient.of(candy_cane.get()), 9, has_candy_cane),
                    shaped(candy_cane_wall.block()::get, 6, "###", "###", "", "#", () -> new Ingredient[]{Ingredient.of(candy_cane_block.block().get())}, has_candy_cane),
                    shapeless(nougat_block.block()::get, 1, () -> Ingredient.of(nougat_powder.get()), 9, has_nougat_powder),
                    shapeless(banana_seaweeds_block.block()::get, 1, () -> Ingredient.of(banana_seaweed.block().get()), 9, has_pez),
                    shapeless(candied_cherry_block.block()::get, 1, () -> Ingredient.of(candied_cherry.get()), 9, has_pez),
                    shapeless(raspberry_block.block()::get, 1, () -> Ingredient.of(rope_raspberry.block().get()), 9, has_pez),
                    shapeless(mint_block.block()::get, 1, () -> Ingredient.of(mint.block().get()), 9, has_pez),
                    shapeless(cotton_candy_block.block()::get, 1, () -> Ingredient.of(cotton_candy.get()), 9, has_pez),
                    shaped(waffle::get, 1, "##", "##", "##", "#", () -> new Ingredient[]{Ingredient.of(waffle_nugget.get())}, criterion("has_waffle_nugget", () -> hasItems(waffle_nugget.get())))
            );

        }
        //传送门
        {
            Collections.addAll(CCDatagenManager.recipes,
                    caramelPortal(() -> ItemUtils.setCountByChain(chocolate_coin.get().getDefaultInstance(), 10), () -> Ingredient.of(mint.block().get()), CaramelPortalRecipeBuilder.InDimension.BOTH),
                    caramelPortal(() -> mint_block.block().get().asItem().getDefaultInstance(), () -> Ingredient.of(mint.block().get()), CaramelPortalRecipeBuilder.InDimension.CANDYLAND)
            );
        }
    }

    public static void init() {
    }

    //=====models=====//
    private static Consumer<Consumer<FinishedRecipe>> sword(Supplier<ItemLike> out, Supplier<Ingredient> material, Supplier<Ingredient> handle, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, " #", " #", " /", "#/", () -> new Ingredient[]{material.get(), handle.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> axe(Supplier<ItemLike> out, Supplier<Ingredient> material, Supplier<Ingredient> handle, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "##", "#/", " /", "#/", () -> new Ingredient[]{material.get(), handle.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> shovel(Supplier<ItemLike> out, Supplier<Ingredient> material, Supplier<Ingredient> handle, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, " #", " /", " /", "#/", () -> new Ingredient[]{material.get(), handle.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> pickaxe(Supplier<ItemLike> out, Supplier<Ingredient> material, Supplier<Ingredient> handle, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "###", " /", " /", "#/", () -> new Ingredient[]{material.get(), handle.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> hoe(Supplier<ItemLike> out, Supplier<Ingredient> material, Supplier<Ingredient> handle, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "##", " /", " /", "#/", () -> new Ingredient[]{material.get(), handle.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> helmet(Supplier<ItemLike> out, Supplier<Ingredient> material, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "###", "# #", "", "#", () -> new Ingredient[]{material.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> chestplate(Supplier<ItemLike> out, Supplier<Ingredient> material, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "# #", "###", "###", "#", () -> new Ingredient[]{material.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> leggings(Supplier<ItemLike> out, Supplier<Ingredient> material, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "###", "# #", "# #", "#", () -> new Ingredient[]{material.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> boots(Supplier<ItemLike> out, Supplier<Ingredient> material, Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shaped(out, 1, "# #", "# #", "", "#", () -> new Ingredient[]{material.get()}, criterion);
    }

    private static Consumer<Consumer<FinishedRecipe>> shapeless(Supplier<ItemLike> result, int resultAmount, Supplier<Ingredient> ingredient, int ingredientAmount, @Nullable Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return shapeless(result, resultAmount, () -> new Ingredient[]{ingredient.get()}, new int[]{ingredientAmount}, criterion);
    }

    //=====origin usages=====//

    private static Consumer<Consumer<FinishedRecipe>> caramelPortal(Supplier<ItemStack> output, Supplier<Ingredient> input, CaramelPortalRecipeBuilder.InDimension dim) {
        return (c) -> {
            ItemStack result = output.get();
            new CaramelPortalRecipeBuilder(result, input.get()).inDim(dim).save(c, getID(result.getItem()));
        };
    }

    private static Pair<String, Supplier<CriterionTriggerInstance>> criterion(String name, Supplier<CriterionTriggerInstance> trigger) {
        return Pair.of(name, trigger);
    }

    private static ResourceLocation getID(ItemLike item) {
        //配方可以重复
        ResourceLocation registryName = Objects.requireNonNull(item.asItem().getRegistryName());
        ResourceLocation id = registryName;
        for (int i = 1; ; i++) {
            if (!recipes.add(id)) {//若包含了,则继续运算
                id = pathPostfix(registryName, "_" + i);
            } else {
                return id;
            }
        }
    }


    private static Consumer<Consumer<FinishedRecipe>> shaped(@NotNull Supplier<ItemLike> result, int amount, @NotNull String line1, @NotNull String line2, @NotNull String line3, @NotNull String symbols, @NotNull Supplier<Ingredient[]> defines, @Nullable Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return (c) -> {
            Ingredient[] def = defines.get();
            if (symbols.length() != def.length) {
                throw new IllegalArgumentException("symbols.length()!= defines.length");
            }
            ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(result.get(), amount);
            builder.pattern(CommonUtils.fillUntil(line1, 3, ' '));//允许偷懒只写一部分,这里自动补全
            builder.pattern(CommonUtils.fillUntil(line2, 3, ' '));
            builder.pattern(CommonUtils.fillUntil(line3, 3, ' '));
            char[] sym = symbols.toCharArray();
            for (int i = 0; i < sym.length; i++) {
                builder.define(sym[i], def[i]);
            }
            if (criterion != null) builder.unlockedBy(criterion.first(), criterion.second().get());
            builder.save(c, getID(builder.getResult()));
        };
    }

    private static Consumer<Consumer<FinishedRecipe>> shapeless(Supplier<ItemLike> result, int resultAmount, Supplier<Ingredient[]> ingredients, int[] ingredientAmounts, @Nullable Pair<String, Supplier<CriterionTriggerInstance>> criterion) {
        return (con) -> {
            Ingredient[] ings = ingredients.get();
            if (ings.length != ingredientAmounts.length) {
                throw new IllegalArgumentException("ingredients.get().length!=ingredientAmounts.length");
            }
            ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(result.get(), resultAmount);
            int sum = 0;
            for (int i = 0; i < ings.length; i++) {
                if (ingredientAmounts[i] <= 0) {
                    throw new IllegalArgumentException("one element in ingredientAmounts<=0");
                }
                sum += ingredientAmounts[i];
                builder.requires(ings[i], ingredientAmounts[i]);
            }
            if (sum > 9) {
                throw new IllegalArgumentException("sum of ingredientAmounts >9");
            }
            if (criterion != null) builder.unlockedBy(criterion.first(), criterion.second().get());
            builder.save(con, getID(builder.getResult()));
        };
    }

}
