package net.lee.vestigesanity.items;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class VestigeItemRegistry {

    public static final Item EMPTY_SYRINGE = new EmptySyringe(new Item.Settings().group(ItemGroup.MISC));
    public static final Item FILLED_SYRINGE = new FilledSyringe(new Item.Settings().group(ItemGroup.MISC).maxCount(1), "None");

    public void initItems() {
        Registry.register(Registry.ITEM, new Identifier("vestigesanity", "empty_syringe"), EMPTY_SYRINGE);
        Registry.register(Registry.ITEM, new Identifier("vestigesanity", "filled_syringe"), FILLED_SYRINGE);

        UseEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            ItemStack item = playerEntity.getStackInHand(hand);

            if (item.getItem() instanceof EmptySyringe) {

                if (entity.getType() == EntityType.COW) {

                    Item newSyringe = new FilledSyringe(new Item.Settings().group(ItemGroup.MISC).maxCount(1), "Cow");

                    CompoundTag syringeData = new CompoundTag();
                    double x = (int)(Math.random()*((10-1)+1))+1;

                    syringeData.putString("tooltip", "CowData Quality + " + x + "%");

                    // I don't know how to remove one from a stack yet
                    //item.setCount(-1);
                    ItemStack syringeStack = new ItemStack(FILLED_SYRINGE, 1);


                    // syringeStack.getItem().appendTooltip(syringeStack, world, tooltipText, TooltipContext.Default.NORMAL);
                    syringeStack.setTag(syringeData);

                    playerEntity.setStackInHand(hand, ItemStack.EMPTY);
                    playerEntity.setStackInHand(hand, syringeStack);

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}
