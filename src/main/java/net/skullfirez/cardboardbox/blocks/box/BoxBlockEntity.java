package net.skullfirez.cardboardbox.blocks.box;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.skullfirez.cardboardbox.blocks.smallbox.SmallBoxBlockEntity;
import net.skullfirez.cardboardbox.setup.Registration;
import net.skullfirez.cardboardbox.util.ImplementedInventory;

public class BoxBlockEntity extends LootableContainerBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(18, ItemStack.EMPTY);

    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.CARDBOARD_BOX_ENTITY, pos, state);
    }

    public BoxBlockEntity(BlockEntityType<SmallBoxBlockEntity> smallCardboardBoxEntity, BlockPos pos, BlockState state) {
        super(smallCardboardBoxEntity, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.readInventoryNbt(tag);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        return this.writeInventoryNbt(tag);
    }

    public void readInventoryNbt(NbtCompound nbt) {
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt) && nbt.contains("Items", 9)) {
            Inventories.readNbt(nbt, this.inventory);
        }

    }

    public NbtCompound writeInventoryNbt(NbtCompound nbt) {
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, false);
        }

        return nbt;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BoxScreenHandler(syncId, playerInventory, this);
    }
}
