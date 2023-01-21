package cn.breadnicecat.candycraft.event;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.block.CCBlockRegisterHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import static cn.breadnicecat.candycraft.block.CCBlockManager.*;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/1 11:22
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = CandyCraft.MODID)
public class ModEventListener {
    public static void onFMLCommonSetup(FMLCommonSetupEvent event) {
    }

    /**
     * 设置渲染图层和实体渲染
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        //setRendererLayer
        {
            RenderType translucent = RenderType.translucent();//alpha(-[0,255]
            setRendererLayer(candyland_portal, translucent);
            setRendererLayer(trampojelly, translucent);
            setRendererLayer(red_trampojelly, translucent);
            setRendererLayer(purple_trampojelly, translucent);
            setRendererLayer(jelly_shock_absorber, translucent);
            setRendererLayer(strawberry_ice_cream, translucent);
            setRendererLayer(grenadine_block, translucent);

            RenderType cutout = RenderType.cutoutMipped();//alpha=0|255
            setRendererLayer(caramel_glass_pane, cutout);
            setRendererLayer(round_caramel_glass_pane, cutout);
            setRendererLayer(diamond_caramel_glass_pane, cutout);
            setRendererLayer(diamond_caramel_glass_pane, cutout);
            setRendererLayer(caramel_leaves, cutout);
            setRendererLayer(chocolate_leaves, cutout);
            setRendererLayer(magic_leaves, cutout);
            setRendererLayer(white_chocolate_leaves, cutout);
            setRendererLayer(candied_cherry_leaves, cutout);
            setRendererLayer(chocolate_sapling, cutout);
            setRendererLayer(candied_cherry_sapling, cutout);
            setRendererLayer(caramel_sapling, cutout);
            setRendererLayer(white_chocolate_sapling, cutout);
            setRendererLayer(caramel_glass, cutout);
            setRendererLayer(round_caramel_glass, cutout);
            setRendererLayer(diamond_caramel_glass, cutout);
            setRendererLayer(marshmallow_door, cutout);
            setRendererLayer(cotton_candy_web, cutout);
            setRendererLayer(honeycomb_torch, cutout);
            setRendererLayer(wall_honeycomb_torch, cutout);
            setRendererLayer(acid_mint_flower, cutout);
            setRendererLayer(banana_seaweed, cutout);
            setRendererLayer(fraise_tagada_flower, cutout);
            setRendererLayer(golden_sugar_flower, cutout);
            setRendererLayer(sweet_grass_0, cutout);
            setRendererLayer(sweet_grass_1, cutout);
            setRendererLayer(sweet_grass_2, cutout);
            setRendererLayer(sweet_grass_3, cutout);
            setRendererLayer(dragibus_crops, cutout);
            setRendererLayer(rope_raspberry, cutout);
            setRendererLayer(lollipop_stem, cutout);
            setRendererLayer(lollipop_block, cutout);
            setRendererLayer(mint, cutout);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends BlockEntity> void register(RegistryObject<BlockEntityType<T>> regObj, BlockEntityRendererProvider<T> pRenderProvider) {
        BlockEntityRenderers.register(regObj.get(), pRenderProvider);
    }

    @OnlyIn(Dist.CLIENT)
    private static void setRendererLayer(CCBlockRegisterHelper.BlockRegistryObject<? extends Block> regisryObject, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(regisryObject.block().get(), type);
    }
}
