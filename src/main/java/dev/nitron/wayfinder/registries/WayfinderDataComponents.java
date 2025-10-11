package dev.nitron.wayfinder.registries;

import dev.nitron.wayfinder.Wayfinder;
import dev.nitron.wayfinder.item.component.SignalscopeComponent;
import dev.nitron.wayfinder.item.component.WayfindersCompassComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class WayfinderDataComponents {
    public static final ComponentType<SignalscopeComponent> SIGNALSCOPE_COMPONENT_COMPONENT_TYPE = registerComponent("signalscope",
            (signalscopeComponentBuilder -> signalscopeComponentBuilder.codec(SignalscopeComponent.CODEC)));

    public static final ComponentType<WayfindersCompassComponent> WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE = registerComponent("wayfinders_compass",
            (wayfindersCompassComponentBuilder ->  wayfindersCompassComponentBuilder.codec(WayfindersCompassComponent.CODEC)));

    private static <T> ComponentType<T> registerComponent(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Wayfinder.MOD_ID, id), (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void init(){
    }
}
