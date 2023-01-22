package cn.breadnicecat.candycraft.block.blockentity;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.block.CCBlockManager;
import com.mojang.datafixers.DSL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 10:09
 */
public final class CCBlockEntityManager {

    private static final DeferredRegister<BlockEntityType<?>> REGISTER = CandyCraft.createRegister(ForgeRegistries.BLOCK_ENTITIES);
    public static final RegistryObject<BlockEntityType<BlockEntitySugarFactory>> sugar_factory = REGISTER.register("sugar_factory", () -> BlockEntityType.Builder.of(BlockEntitySugarFactory::new, CCBlockManager.sugar_factory.block().get()).build(DSL.remainderType()));

    static {
        CandyCraft.clockIn();
    }

    public static void init() {
    }
}
