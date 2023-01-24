package cn.breadnicecat.candycraft.gui.screen;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.gui.menu.SugarFactoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:58
 */
public class SugarFactoryScreen extends CCBaseScreen<SugarFactoryMenu> {
	protected static final ResourceLocation bg = CandyCraft.prefixGUITex("gui_sugar_factory");
	/**
	 * bg渲染开始位置
	 */
	protected int x;
	protected int y;
	
	public SugarFactoryScreen(SugarFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}
	
	@Override
	public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.x = leftPos;
		this.y = topPos + 51;
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}
	
	@Override
	protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
		this.font.draw(pPoseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY + 36, 0xb5ff71);
	}
	
	@Override
	protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShaderTexture(0, bg);
		blit(pPoseStack, x, y, 0, 0, 173, 113);
		int pro = menu.blockEntity.data.get(0);
		if (pro > 0) {
			blit(pPoseStack, x + 27, y + 9, 0, 114, (int) (120 * menu.blockEntity.getProgressPercent()), 12);
		}
	}
}
