package net.skullfirez.cardboardbox.blocks.smallbox;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.skullfirez.cardboardbox.blocks.box.BoxBlock;
import net.skullfirez.cardboardbox.blocks.box.BoxBlockEntity;

public class SmallBoxBlock extends BoxBlock {
    public SmallBoxBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SmallBoxBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.5f, 1f);
    }
}
