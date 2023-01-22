package cn.breadnicecat.candycraft.gui.screen;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.gui.menu.SugarFactoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:58
 */
public class SugarFactoryScreen extends CCBaseScreen<SugarFactoryMenu> {
    private static final ResourceLocation bg = CandyCraft.prefixGUITex("gui_sugar_factory");

    public SugarFactoryScreen(SugarFactoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, bg);
    }
}
