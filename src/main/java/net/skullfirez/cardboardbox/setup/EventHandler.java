package net.skullfirez.cardboardbox.setup;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.skullfirez.cardboardbox.mixin.AxeStrippedAccessor;

import java.util.Map;
import java.util.Random;

public class EventHandler {

    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            Random random = new Random();
            Map<Block, Block> STRIPPED_BLOCKS = AxeStrippedAccessor.getStrippedBlocks();
            BlockPos blockPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            ItemEntity itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), Registration.SAWDUST.getDefaultStack());
            if (!world.isClient && !player.isSpectator() && STRIPPED_BLOCKS.containsKey(blockState.getBlock())) {
                if (player.getMainHandStack().isIn(FabricToolTags.AXES) || player.getOffHandStack().isIn(FabricToolTags.AXES)) {
                    if (random.nextFloat() < 0.1F) {
                        world.spawnEntity(itemEntity);
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}
