package cn.breadnicecat.codeovencore.helper;

import cn.breadnicecat.codeovencore.CodeOvenCoreInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/24 13:39
 */
public class BlockRegisterHelper extends Helper {
	public final DeferredRegister<Block> register;

	public BlockRegisterHelper(CodeOvenCoreInstance instance) {
		super(instance);
		register = getRegister(Block.class);
	}

	public <I extends Block> RegistryObject<I> registerBlock(String name, Supplier<I> block) {
		return register.register(name, block);
	}

	public <I extends Block> RegistryObject<I> registerSimpleBlock(String name, Supplier<I> block) {
		return registerBlock(name, block);
	}
}
