package cn.breadnicecat.candycraft.data;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/12 16:22
 */
public class CCLootTableProvider extends LootTableProvider {
	public CCLootTableProvider(DataGenerator pGenerator) {
		super(pGenerator);
	}

	@Override
	protected @NotNull List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
		ArrayList<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> loots = new ArrayList<>();
		CCBlockLoot loot = new CCBlockLoot();
		CCDatagenManager.blockLoot.forEach((blo, func) -> loot.add(blo.get(), func));
		loots.add(Pair.of(() -> loot, LootContextParamSets.BLOCK));
		return loots;
	}

	@Override
	protected void validate(@NotNull Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationtracker) {
	}
}
