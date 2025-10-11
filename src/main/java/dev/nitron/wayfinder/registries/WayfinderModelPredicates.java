package dev.nitron.wayfinder.registries;

import dev.nitron.wayfinder.Wayfinder;
import dev.nitron.wayfinder.item.WayfindersCompass;
import dev.nitron.wayfinder.util.WayfindersCompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class WayfinderModelPredicates {
    public static void init(){
        ModelPredicateProviderRegistry.register(WayfinderItems.WAYFINDERS_COMPASS, Identifier.of(Wayfinder.MOD_ID, "angle"),
                (stack, world, entity, seed) -> {
            WayfindersCompassAnglePredicateProvider provider = new WayfindersCompassAnglePredicateProvider();
            return provider.unclampedCall(stack, world, entity, seed);
        });
    }
}
