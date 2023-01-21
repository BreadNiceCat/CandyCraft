package cn.breadnicecat.candycraft.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/16 23:07
 */
public class CandylandTeleporter implements ITeleporter {
    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        return repositionEntity.apply(false);
    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        Vec3 position = entity.position();
        return new PortalInfo(new Vec3(position.x(), CCDIMs.CANDYLAND_MAX_Y + 1, position.z()), Vec3.ZERO, entity.getYRot(), entity.getXRot());
    }

    @Override
    public boolean isVanilla() {
        return false;
    }
}
