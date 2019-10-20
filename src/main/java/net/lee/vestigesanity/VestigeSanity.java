package net.lee.vestigesanity;

import net.fabricmc.api.ModInitializer;

import net.lee.vestigesanity.items.VestigeItemRegistry;



public class VestigeSanity implements ModInitializer {

    @Override
    public void onInitialize() {

        VestigeItemRegistry vestigeItemRegistry = new VestigeItemRegistry();
        vestigeItemRegistry.initItems();

    }
}
