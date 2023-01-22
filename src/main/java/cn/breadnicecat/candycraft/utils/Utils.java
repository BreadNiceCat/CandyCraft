package cn.breadnicecat.candycraft.utils;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * mc特有,但是无法分类
 *
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/17 14:17
 */
public class Utils {
    public static ResourceLocation pathExtend(ResourceLocation raw, String prefix, String postfix) {
        return new ResourceLocation(raw.getNamespace(), prefix + raw.getPath() + postfix);
    }

    public static ResourceLocation pathPrefix(ResourceLocation raw, String prefix) {
        return pathExtend(raw, prefix, "");
    }

    public static ResourceLocation pathPostfix(ResourceLocation raw, String postfix) {
        return pathExtend(raw, "", postfix);
    }

    @SafeVarargs
    public static InventoryChangeTrigger.TriggerInstance hasItemTags(TagKey<Item>... tags) {
        ItemPredicate[] pred = new ItemPredicate[tags.length];
        for (int i = 0; i < tags.length; i++) {
            pred[i] = ItemPredicate.Builder.item().of(tags[i]).build();
        }
        return InventoryChangeTrigger.TriggerInstance.hasItems(pred);
    }

}
