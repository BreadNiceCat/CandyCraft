package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/20 9:52
 */
public class BlockAcidMintFlower extends BlockCandyPlant {

    public BlockAcidMintFlower(Properties pProperties) {
        super(pProperties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Entity pEntity) {
        if (pEntity instanceof LivingEntity livE) {
            livE.addEffect(new MobEffectInstance(MobEffects.POISON, (int) TickUnit.toTick(TimeUnit.SECONDS, 10)));
        }
    }
}
