package cn.breadnicecat.candycraft.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.JsonOps;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 20:02
 */
public class ItemUtils {
    public static JsonElement toJson(ItemStack stack) {
        return ItemStack.CODEC.encodeStart(JsonOps.INSTANCE, stack).get().orThrow();
    }

    public static ItemStack fromJson(JsonElement element, boolean canBeAir) {
        ItemStack stack = ItemStack.CODEC.decode(JsonOps.INSTANCE, element).get().orThrow().getFirst();
        if (!canBeAir && stack.isEmpty()) {
            throw new JsonSyntaxException("ItemStack can't be empty");
        }
        return stack;
    }

    public static boolean hasBlockItem(Block block) {
        return block.asItem() != Items.AIR;
    }

    public static ItemStack setCountByChain(ItemStack stack, int count) {
        stack.setCount(count);
        return stack;
    }
}
