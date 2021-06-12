package net.skullfirez.cardboardbox.setup;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.skullfirez.cardboardbox.blocks.box.BoxBlock;
import net.skullfirez.cardboardbox.blocks.box.BoxBlockEntity;
import net.skullfirez.cardboardbox.blocks.box.BoxScreenHandler;
import net.skullfirez.cardboardbox.blocks.smallbox.SmallBoxBlock;
import net.skullfirez.cardboardbox.blocks.smallbox.SmallBoxBlockEntity;
import net.skullfirez.cardboardbox.blocks.smallbox.SmallBoxScreenHandler;
import net.skullfirez.cardboardbox.items.PackingPeanutItem;

public class Registration {

    //Normal
    public static BoxBlock CARDBOARD_BOX;
    public static BlockItem CARDBOARD_BOX_ITEM;
    public static BlockEntityType<BoxBlockEntity> CARDBOARD_BOX_ENTITY;
    public static ScreenHandlerType<BoxScreenHandler> CARDBOARD_BOX_SCREEN_HANDLER;

    //Small
    public static SmallBoxBlock SMALL_CARDBOARD_BOX;
    public static BlockItem SMALL_CARDBOARD_BOX_ITEM;
    public static BlockEntityType<SmallBoxBlockEntity> SMALL_CARDBOARD_BOX_ENTITY;
    public static ScreenHandlerType<SmallBoxScreenHandler> SMALL_CARDBOARD_BOX_SCREEN_HANDLER;

    public static Item SAWDUST;
    public static PackingPeanutItem PACKING_PEANUT;

    public static final String MOD_ID = "cardboardbox";
    public static final Identifier BOX = new Identifier(MOD_ID, "cardboard_box");
    public static final Identifier SMALL_BOX = new Identifier(MOD_ID, "small_cardboard_box");

    public static void init() {
        //Blocks
        CARDBOARD_BOX = Registry.register(Registry.BLOCK, BOX, new BoxBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(0.4f).breakByTool(FabricToolTags.SHEARS)));
        CARDBOARD_BOX_ITEM = Registry.register(Registry.ITEM, BOX, new BlockItem(CARDBOARD_BOX, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        SMALL_CARDBOARD_BOX = Registry.register(Registry.BLOCK, SMALL_BOX, new SmallBoxBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(0.4f).breakByTool(FabricToolTags.SHEARS)));
        SMALL_CARDBOARD_BOX_ITEM = Registry.register(Registry.ITEM, SMALL_BOX, new BlockItem(SMALL_CARDBOARD_BOX, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        //BlockEntities
        CARDBOARD_BOX_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, CARDBOARD_BOX).build(null));
        CARDBOARD_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOX, BoxScreenHandler::new);

        SMALL_CARDBOARD_BOX_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SMALL_BOX, FabricBlockEntityTypeBuilder.create(SmallBoxBlockEntity::new, SMALL_CARDBOARD_BOX).build(null));
        SMALL_CARDBOARD_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(SMALL_BOX, SmallBoxScreenHandler::new);
        //Items
        SAWDUST = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sawdust"), new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));
        PACKING_PEANUT = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "packing_peanut"), new PackingPeanutItem(new FabricItemSettings().group(ItemGroup.MATERIALS)));
        //Misc
        FuelRegistry.INSTANCE.add(SAWDUST, 30);
        FuelRegistry.INSTANCE.add(CARDBOARD_BOX_ITEM, 300);
        FuelRegistry.INSTANCE.add(SMALL_CARDBOARD_BOX_ITEM, 300);
        //FlammableBlockRegistry.getDefaultInstance().add(CARDBOARD_BOX, 60, 100);
    }
}
