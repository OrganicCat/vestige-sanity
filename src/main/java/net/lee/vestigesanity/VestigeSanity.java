package net.lee.vestigesanity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class VestigeSanity implements ModInitializer {

    public static final Item EMPTY_SYRINGE = new EmptySyringe(new Item.Settings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("vestigesanity", "empty_syringe"), EMPTY_SYRINGE);
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        UseEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            ItemStack item = playerEntity.getStackInHand(hand);
            System.out.println("Empty syringe detected on right click");


            if (item.getItem() instanceof EmptySyringe) {
                System.out.println("Clicked a creature");
                if (entity.getType() == EntityType.COW) {
                    playerEntity.setStackInHand(hand, ItemStack.EMPTY);
                    playerEntity.setStackInHand(hand, new ItemStack(EMPTY_SYRINGE));
                    System.out.println("Cow Juice received");
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });

        System.out.println("Hello Fabric world!");
    }
}
