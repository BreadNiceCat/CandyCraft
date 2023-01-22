package cn.breadnicecat.candycraft.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:58
 */
public class CCBaseScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    private final ResourceLocation texLoc;

    public CCBaseScreen(T pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation texLoc) {
        super(pMenu, pPlayerInventory, pTitle);
        this.texLoc = texLoc;
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        RenderSystem.setShaderTexture(0, texLoc);
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
