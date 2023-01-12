package cn.breadnicecat.candycraft.event;

import cn.breadnicecat.candycraft.CandyCraft;
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
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventListener {
	public static void onFMLCommonSetup(FMLCommonSetupEvent event) {
	}

	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event) {
		CandyCraft.LOGGER.info("FMLClientSetupEvent hit");
		{
			RenderType translucent = RenderType.translucent();
			setRendererLayer(candyland_portal, translucent);
			setRendererLayer(trampojelly, translucent);
			setRendererLayer(red_trampojelly, translucent);
			setRendererLayer(purple_trampojelly, translucent);
			setRendererLayer(jelly_shock_absorber, translucent);

			RenderType cutout = RenderType.cutoutMipped();
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
		}
	}

	public static <T extends BlockEntity> void register(RegistryObject<BlockEntityType<T>> regObj, BlockEntityRendererProvider<T> pRenderProvider) {
		BlockEntityRenderers.register(regObj.get(), pRenderProvider);
	}

	@OnlyIn(Dist.CLIENT)
	private static void setRendererLayer(BlockRegistryObject<? extends Block> regisryObject, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(regisryObject.block().get(), type);
	}
}
