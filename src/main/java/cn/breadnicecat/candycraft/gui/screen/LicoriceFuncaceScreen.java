package cn.breadnicecat.candycraft.gui.screen;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.gui.menu.LicoriceFurnaceMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/24 10:29
 */
public class LicoriceFuncaceScreen extends CCBaseScreen<LicoriceFurnaceMenu> {
	protected static final ResourceLocation bg = CandyCraft.prefixGUITex("gui_sugar_furnace");
	/**
	 * bg渲染开始位置
	 */
	protected int x;
	protected int y;
	
	
	public LicoriceFuncaceScreen(LicoriceFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}
	
	@Override
	public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.x = leftPos - 1;
		this.y = topPos;
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
	}
	
	@Override
	protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShaderTexture(0, bg);
		blit(pPoseStack, x, y, 0, 0, 176, 166);
		if (menu.blockEntity.hasFuelHeat()) {
			int p = (int) (14 * menu.blockEntity.getFuelHeatLeftPercent());//渲染高度
			int k = 14 - p;//未渲染高度
			//简称偷鸡摸狗反过来渲染
			blit(pPoseStack, x + 57, y + 37 + k, 176, k, 14, p);
		}
		if (menu.blockEntity.hasProgress()) {
			blit(pPoseStack, x + 80, y + 35, 176, 14, (int) (22 * menu.blockEntity.getProgressPercent()), 16);
		}
	}
	
	@Override
	protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
		this.font.draw(pPoseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 0xd7d7d7);
	}
}
