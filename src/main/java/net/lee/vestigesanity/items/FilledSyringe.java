package net.lee.vestigesanity.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FilledSyringe extends Item {

    private String bloodType;

    public FilledSyringe(Settings item$Settings_1, String bloodType) {
        super(item$Settings_1);
        this.setBloodType(bloodType);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.heal(1.0F);
        playerEntity.playSound(SoundEvents.BLOCK_WOOD_BREAK, 1.0F, 1.0F);
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.clear();

        Text tooltipText = null;
        if (itemStack.getTag() != null && itemStack.getTag().getSize() > 0) {
            tooltipText = tooltipText = new LiteralText(itemStack.getTag().getTag("tooltip").asString());
            Style newStyle = new Style();
            newStyle.setBold(true);
            newStyle.setColor(Formatting.GOLD);
            tooltipText.setStyle(newStyle);
            tooltip.add(tooltipText);
        } else {
            tooltip.add(new TranslatableText("item.vestigesanity.filled_syringe"));
        }

    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

}
