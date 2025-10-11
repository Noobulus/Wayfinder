package dev.nitron.wayfinder.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public record WayfindersCompassComponent(boolean open) {
    public static final Codec<WayfindersCompassComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("open").forGetter(WayfindersCompassComponent::open)
    ).apply(instance, WayfindersCompassComponent::new));
}