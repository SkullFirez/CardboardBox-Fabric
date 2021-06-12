package net.skullfirez.cardboardbox.blocks.smallbox;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.skullfirez.cardboardbox.blocks.box.BoxScreenHandler;
import net.skullfirez.cardboardbox.setup.Registration;

public class SmallBoxScreenHandler extends BoxScreenHandler {
    private Inventory inventory;

    public SmallBoxScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(9));
    }

    public SmallBoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(Registration.SMALL_CARDBOARD_BOX_SCREEN_HANDLER, syncId);
        checkSize(inventory, 9);
        this.inventory = inventory;
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player);

        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        int l;
        int m;
        // Block Inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(inventory, j + i * 3, 62 + j * 18, 18 + i * 18));
            }
        }


        // Player Inventory (27 storage + 9 hotbar)
        for (l = 0; l < 3; l++) {
            for (m = 0; m < 9; m++) {
                this.addSlot(new Slot(playerInventory, l * 9 + m + 9, 8 + m * 18, 92 + l * 18));
            }
        }


        for (m = 0; m < 9; m++) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 150));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
