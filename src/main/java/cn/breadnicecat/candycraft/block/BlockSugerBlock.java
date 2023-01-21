package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.event.SpawnCandyLandPortalEvent;
import cn.breadnicecat.candycraft.misc.CCBlockTags;
import cn.breadnicecat.candycraft.misc.CCDIMs;
import cn.breadnicecat.candycraft.utils.LevelUtils;
import cn.breadnicecat.candycraft.utils.NetherLikePortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/31 11:13
 */
public class BlockSugerBlock extends Block {
    public BlockSugerBlock(Properties prop) {
        super(prop);
    }


    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if ((pLevel.dimension() == Level.OVERWORLD || pLevel.dimension() == CCDIMs.CANDYLAND)) {
            if (itemInHand.getItem() instanceof BucketItem bucketItem && bucketItem.getFluid().isSame(Fluids.LAVA)) {
                //PortalShape.findEmptyPortalShape必须传入火焰pos,而参数pPos是当前方块的位置
                BlockPos portalPos = LevelUtils.move(pPos, pHit.getDirection(), 1);
                //拿着岩浆桶在主世界或糖果世界可以开门
                Optional<NetherLikePortalShape> shape = tryFindPortal(pLevel, portalPos);
                if (shape.isPresent()) {
                    if (bucketItem.emptyContents(pPlayer, pLevel, portalPos, pHit)) {
                        pPlayer.setItemInHand(pHand, BucketItem.getEmptySuccessItem(itemInHand, pPlayer));
                        shape.get().build(CCBlockManager.candyland_portal.block().get().defaultBlockState());
                        return InteractionResult.sidedSuccess(pLevel.isClientSide());
                    }
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @NotNull
    private Optional<NetherLikePortalShape> tryFindPortal(Level level, @Nullable BlockPos portalPos) {
        if (portalPos == null) return Optional.empty();
        Optional<NetherLikePortalShape> shape = NetherLikePortalShape.findPortalShape(level, portalPos, (b) -> b.is(CCBlockTags.CANDYLAND_PORTAL_FRAME), BlockStateBase::isAir);
        if (shape.isEmpty()) return shape;
        return MinecraftForge.EVENT_BUS.post(new SpawnCandyLandPortalEvent(level, portalPos, level.getBlockState(portalPos), shape.get(), false)) ? Optional.empty() : shape;
    }

}
