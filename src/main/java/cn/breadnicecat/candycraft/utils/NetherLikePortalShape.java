package cn.breadnicecat.candycraft.utils;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/9 22:59
 * 部分逻辑从PortalShape复制
 */
public class NetherLikePortalShape {

	//	private static final int MIN_WIDTH = 2;
//	public static final int MAX_WIDTH = 21;
//	private static final int MIN_HEIGHT = 3;
//	public static final int MAX_HEIGHT = 21;//坑爹玩意,全被final直接插入了,改都改不了
	private final Predicate<BlockState> isFrame;
	private final Predicate<BlockState> canIgnore;
	public final LevelAccessor level;
	public final Direction.Axis axis;
	public final Direction rightDir;
	public final int width;
	private int numPortalBlocks;
	public int height;
	//	@Nullable 为什么是Nullable呢,传null进去直接就报NPE
	public BlockPos bottomLeft;

	public NetherLikePortalShape(LevelAccessor pLevel, BlockPos pBottomLeft, Direction.Axis pAxis, Predicate<BlockState> isFrame, Predicate<BlockState> canIgnore) {
		this.isFrame = isFrame;
		this.canIgnore = canIgnore;
		this.level = pLevel;
		this.axis = pAxis;
		this.rightDir = pAxis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
		this.bottomLeft = this.calculateBottomLeft(pBottomLeft);
		if (this.bottomLeft == null) {
			this.bottomLeft = pBottomLeft;
			this.width = 1;
			this.height = 1;
		} else {
			this.width = this.calculateWidth();
			if (this.width > 0) {
				this.height = this.calculateHeight();
			}
		}

	}

	/**
	 * 使用这个方法检测传送门
	 *
	 * @param canIgnore 传送门框架内部分方块能否被忽略,例如地狱门内可以存在火方块和空气方块
	 */
	public static Optional<NetherLikePortalShape> findEmptyPortalShape(LevelAccessor pLevel, BlockPos pBottomLeft, Predicate<BlockState> isFrame, Predicate<BlockState> canIgnore) {
		return findPortalShape(pLevel, pBottomLeft, (portalShape) -> portalShape.isValid() && portalShape.numPortalBlocks == 0, isFrame, canIgnore);
	}

	public static Optional<NetherLikePortalShape> findPortalShape(LevelAccessor pLevel, BlockPos pBottomLeft, Predicate<NetherLikePortalShape> isProper, Predicate<BlockState> isFrame, Predicate<BlockState> canIgnore) {
		Optional<NetherLikePortalShape> optional = Optional.of(new NetherLikePortalShape(pLevel, pBottomLeft, Direction.Axis.X, isFrame, canIgnore)).filter(isProper);
		if (optional.isPresent()) {
			return optional;
		} else {
			return Optional.of(new NetherLikePortalShape(pLevel, pBottomLeft, Direction.Axis.Z, isFrame, canIgnore)).filter(isProper);
		}
	}

	public static Vec3 getRelativePosition(BlockUtil.FoundRectangle p_77739_, Direction.Axis p_77740_, Vec3 p_77741_, EntityDimensions p_77742_) {
		double d0 = (double) p_77739_.axis1Size - (double) p_77742_.width;
		double d1 = (double) p_77739_.axis2Size - (double) p_77742_.height;
		BlockPos blockpos = p_77739_.minCorner;
		double d2;
		if (d0 > 0.0D) {
			float f = (float) blockpos.get(p_77740_) + p_77742_.width / 2.0F;
			d2 = Mth.clamp(Mth.inverseLerp(p_77741_.get(p_77740_) - (double) f, 0.0D, d0), 0.0D, 1.0D);
		} else {
			d2 = 0.5D;
		}

		double d4;
		if (d1 > 0.0D) {
			Direction.Axis direction$axis = Direction.Axis.Y;
			d4 = Mth.clamp(Mth.inverseLerp(p_77741_.get(direction$axis) - (double) blockpos.get(direction$axis), 0.0D, d1), 0.0D, 1.0D);
		} else {
			d4 = 0.0D;
		}

		Direction.Axis direction$axis1 = p_77740_ == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
		double d3 = p_77741_.get(direction$axis1) - ((double) blockpos.get(direction$axis1) + 0.5D);
		return new Vec3(d2, d4, d3);
	}

	public static PortalInfo createPortalInfo(ServerLevel pLevel, BlockUtil.FoundRectangle p_77701_, Direction.Axis pAxis, Vec3 p_77703_, EntityDimensions pDimensions, Vec3 p_77705_, float p_77706_, float pXRot) {
		BlockPos blockpos = p_77701_.minCorner;
		BlockState blockstate = pLevel.getBlockState(blockpos);
		Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
		double d0 = p_77701_.axis1Size;
		double d1 = p_77701_.axis2Size;
		int i = pAxis == direction$axis ? 0 : 90;
		Vec3 vec3 = pAxis == direction$axis ? p_77705_ : new Vec3(p_77705_.z, p_77705_.y, -p_77705_.x);
		double d2 = (double) pDimensions.width / 2.0D + (d0 - (double) pDimensions.width) * p_77703_.x();
		double d3 = (d1 - (double) pDimensions.height) * p_77703_.y();
		double d4 = 0.5D + p_77703_.z();
		boolean flag = direction$axis == Direction.Axis.X;
		Vec3 vec31 = new Vec3((double) blockpos.getX() + (flag ? d2 : d4), (double) blockpos.getY() + d3, (double) blockpos.getZ() + (flag ? d4 : d2));
		return new PortalInfo(vec31, vec3, p_77706_ + (float) i, pXRot);
	}


	public boolean isValid() {
		return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
	}

	/**
	 * @param portal 一般情况下直接传入默认BlockState就行
	 */
	public void createPortalBlocks(BlockState portal) {
		BlockState blockstate = portal.setValue(NetherPortalBlock.AXIS, this.axis);
		BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach((pos) -> this.level.setBlock(pos, blockstate, 18));
	}

	public boolean isComplete() {
		return this.isValid() && this.numPortalBlocks == this.width * this.height;
	}

	@Nullable
	private BlockPos calculateBottomLeft(BlockPos bottomLeft) {
		for (int i = Math.max(this.level.getMinBuildHeight(), bottomLeft.getY() - 21); bottomLeft.getY() > i && isEmpty(this.level.getBlockState(bottomLeft.below())); bottomLeft = bottomLeft.below()) {
		}

		Direction direction = this.rightDir.getOpposite();
		int j = this.getDistanceUntilEdgeAboveFrame(bottomLeft, direction) - 1;
		return j < 0 ? null : bottomLeft.relative(direction, j);
	}

	private boolean isEmpty(BlockState blockState) {
		return canIgnore.test(blockState);
	}

	private int calculateWidth() {
		int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
		return i >= 2 && i <= 21 ? i : 0;
	}

	private int getDistanceUntilEdgeAboveFrame(BlockPos pPos, Direction pDirection) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int i = 0; i <= 21; ++i) {
			blockpos$mutableblockpos.set(pPos).move(pDirection, i);
			BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
			if (!isEmpty(blockstate)) {
				if (isFrame.test(blockstate/*, this.level, blockpos$mutableblockpos*/)) {
					return i;
				}
				break;
			}

			BlockState blockstate1 = this.level.getBlockState(blockpos$mutableblockpos.move(Direction.DOWN));
			if (!isFrame.test(blockstate1/*, this.level, blockpos$mutableblockpos*/)) {
				break;
			}
		}

		return 0;
	}

	private int calculateHeight() {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int i = this.getDistanceUntilTop(blockpos$mutableblockpos);
		return i >= 3 && i <= 21 && this.hasTopFrame(blockpos$mutableblockpos, i) ? i : 0;
	}

	private boolean hasTopFrame(BlockPos.MutableBlockPos p_77731_, int p_77732_) {
		for (int i = 0; i < this.width; ++i) {
			BlockPos.MutableBlockPos blockpos$mutableblockpos = p_77731_.set(this.bottomLeft).move(Direction.UP, p_77732_).move(this.rightDir, i);
			if (!isFrame.test(this.level.getBlockState(blockpos$mutableblockpos)/*, this.level, blockpos$mutableblockpos*/)) {
				return false;
			}
		}

		return true;
	}

	private int getDistanceUntilTop(BlockPos.MutableBlockPos pPos) {
		for (int i = 0; i < 21; ++i) {
			pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
			if (!isFrame.test(this.level.getBlockState(pPos)/*, this.level, pPos*/)) {
				return i;
			}

			pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
			if (!isFrame.test(this.level.getBlockState(pPos)/*, this.level, pPos*/)) {
				return i;
			}

			for (int j = 0; j < this.width; ++j) {
				pPos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
				BlockState blockstate = this.level.getBlockState(pPos);
				if (!isEmpty(blockstate)) {
					return i;
				}
//				if (blockstate.is(Blocks.NETHER_PORTAL)) {
//					++this.numPortalBlocks;
//				}
			}
		}

		return 21;
	}
}

