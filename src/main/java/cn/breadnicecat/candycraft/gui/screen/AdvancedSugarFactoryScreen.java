package cn.breadnicecat.candycraft.gui.screen;

import cn.breadnicecat.candycraft.gui.menu.SugarFactoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/23 22:20
 */
public class AdvancedSugarFactoryScreen extends SugarFactoryScreen {
	public AdvancedSugarFactoryScreen(SugarFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}
	
	@Override
	protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShaderTexture(0, bg);
		blit(pPoseStack, x, y, 0, 126, 173, 113);
		if (menu.blockEntity.hasProgress()) {
			blit(pPoseStack, x + 27, y + 9, 0, 240, Math.round(120 * menu.blockEntity.getProgressPercent()), 12);
		}
	}
	
	@Override
	protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
		this.font.draw(pPoseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY + 36, 0xf84356);
	}
}
