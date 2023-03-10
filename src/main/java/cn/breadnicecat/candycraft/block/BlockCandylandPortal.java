package cn.breadnicecat.candycraft.block;

import cn.breadnicecat.candycraft.event.SpawnCandyLandPortalEvent;
import cn.breadnicecat.candycraft.misc.*;
import cn.breadnicecat.candycraft.recipe.CCRecipeManager;
import cn.breadnicecat.candycraft.recipe.CaramelPortalRecipe;
import cn.breadnicecat.candycraft.utils.NetherLikePortalShape;
import cn.breadnicecat.candycraft.utils.TickUnit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2022/12/30 22:32
 */
public class BlockCandylandPortal extends NetherPortalBlock {
    public static final BooleanProperty UPGRADE = BooleanProperty.create("upgrade");

    public BlockCandylandPortal(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(UPGRADE, false));
    }

    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        //??????randomTick
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if (pLevel.dimension() == CCDIMs.CANDYLAND && itemInHand.is(CCItemTags.RETURN_TICKET)) {
            Optional<NetherLikePortalShape> shape = tryFindBuiltPortal(pLevel, pPos);
            if (shape.isPresent()) {
                NetherLikePortalShape portalShape = shape.get();
                portalShape.forEachBlockInPortal((l, pos) -> {
                    l.setBlock(pos, l.getBlockState(pos).setValue(UPGRADE, true), 2 | 16);
                    l.explode(null, pos.getX(), pos.getY(), pos.getZ(), 0.5f, Explosion.BlockInteraction.NONE);
                });
                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void entityInside(@NotNull BlockState pState, @NotNull Level level, @NotNull BlockPos pPos, @NotNull Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack stack = itemEntity.getItem();
            Vec3 delta = itemEntity.getDeltaMovement();
            itemEntity.setItem(ItemStack.EMPTY);
            if (stack.is(Items.BREAD)) {//??????
                level.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 2.5f, Explosion.BlockInteraction.NONE);
                return;
            }
            for (Recipe<Container> r : level.getRecipeManager().getAllRecipesFor(CCRecipeManager.caramel_portal_type.get())) {
                CaramelPortalRecipe r1 = (CaramelPortalRecipe) r;
                if (r1.matches(stack, level)) {
                    ItemStack result = r1.assemble(stack);
                    level.addFreshEntity(new ItemEntity(level, pPos.getX(), pPos.getY(), pPos.getZ(), result, -delta.x(), delta.y(), -delta.z()));
                    return;
                }
            }
        } else if (entity.isAlive() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            ResourceKey<Level> destination = teleportDestination(pState, level, pPos);
            if (destination != null) {
                MinecraftServer server = level.getServer();
                if (server != null) {
                    ServerLevel cl = server.getLevel(destination);
                    if (cl != null) {
                        if (entity instanceof LivingEntity livE) {
                            livE.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, (int) TickUnit.toTick(TimeUnit.SECONDS, 16), 5));
                        }
                        entity.changeDimension(cl, new CandylandTeleporter());
                    }
                }
            }
        }
    }

    private Optional<NetherLikePortalShape> tryFindBuiltPortal(Level level, @Nullable BlockPos portalPos) {
        if (portalPos == null) return Optional.empty();
        Optional<NetherLikePortalShape> shape = NetherLikePortalShape.findPortalShape(level, portalPos, (b) -> b.is(CCBlockTags.CANDYLAND_PORTAL_FRAME), (b) -> b.is(this));
        if (shape.isEmpty()) return shape;
        return MinecraftForge.EVENT_BUS.post(new SpawnCandyLandPortalEvent(level, portalPos, level.getBlockState(portalPos), shape.get(), true)) ? Optional.empty() : shape;
    }


    @Override
    public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Random pRand) {
        if (teleportDestination(pState, pLevel, pPos) != null) {
            //[VanillaCopy]?????????PortalBlock
            for (int i = 0; i < 4; ++i) {
                double d0 = (double) pPos.getX() + pRand.nextDouble();
                double d1 = (double) pPos.getY() + pRand.nextDouble();
                double d2 = (double) pPos.getZ() + pRand.nextDouble();
                double d3 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) pRand.nextFloat() - 0.5D) * 0.5D;
                int j = pRand.nextInt(2) * 2 - 1;
                if (!pLevel.getBlockState(pPos.west()).is(this) && !pLevel.getBlockState(pPos.east()).is(this)) {
                    d0 = (double) pPos.getX() + 0.5D + 0.25D * (double) j;
                    d3 = pRand.nextFloat() * 2.0F * (float) j;
                } else {
                    d2 = (double) pPos.getZ() + 0.5D + 0.25D * (double) j;
                    d5 = pRand.nextFloat() * 2.0F * (float) j;
                }
                pLevel.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UPGRADE);
    }

    /**
     * @return null??????????????????
     */
    protected @Nullable ResourceKey<Level> teleportDestination(BlockState pState, Level level, BlockPos pPos) {
        if (pState.getValue(UPGRADE) && level.dimension() == CCDIMs.CANDYLAND && CCConfig.CAN_RETURN_FORM_CANDYLAND.get()) {
            return Level.OVERWORLD;
        } else if (level.dimension() == Level.OVERWORLD && CCConfig.CAN_JOIN_CANDYLAND.get()) {
            return CCDIMs.CANDYLAND;
        } else {
            return null;
        }
    }

}