package dev.nitron.wayfinder.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import dev.nitron.wayfinder.Wayfinder;
import dev.nitron.wayfinder.item.component.WayfindersCompassComponent;
import dev.nitron.wayfinder.registries.WayfinderComponents;
import dev.nitron.wayfinder.registries.WayfinderDataComponents;
import dev.nitron.wayfinder.registries.WayfinderSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WayfindersCompass extends Item implements ColorableItem {
    public WayfindersCompass(Settings settings) {
        super(settings.component(WayfinderDataComponents.WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE, new WayfindersCompassComponent(false)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient){
            user.playSoundToPlayer(WayfinderSounds.COMPASS_OPEN, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
        Wayfinder.grantAdvancement(user, Identifier.of(Wayfinder.MOD_ID, "a_compass_that_only_points_north"), "incode");
        user.getStackInHand(hand).set(WayfinderDataComponents.WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE, new WayfindersCompassComponent(true));
        user.setCurrentHand(hand);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof PlayerEntity player)) return;
        if (!world.isClient && stack.get(WayfinderDataComponents.WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE).open()) {
            boolean isUsingThisStack = player.isUsingItem() && player.getActiveItem() == stack;

            if (!isUsingThisStack) {
                stack.set(WayfinderDataComponents.WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE, new WayfindersCompassComponent(false));
            }
        }
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player && world.isClient) player.playSoundToPlayer(WayfinderSounds.COMPASS_CLOSE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        stack.set(WayfinderDataComponents.WAYFINDERS_COMPASS_COMPONENT_COMPONENT_TYPE, new WayfindersCompassComponent(false));
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(0xf7db70);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF752d07;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF591804;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF0140502;
    }
}
