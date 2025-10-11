package dev.nitron.wayfinder.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class WayfindersCompassAnglePredicateProvider implements ClampedModelPredicateProvider {
    @Override
    public float unclampedCall(ItemStack itemStack, @Nullable ClientWorld clientWorld, @Nullable LivingEntity livingEntity, int i) {
        Entity entity = (Entity)(livingEntity != null ? livingEntity : itemStack.getHolder());
        if (entity == null) {
            return 0.0F;
        } else {
            clientWorld = this.getClientWorld(entity, clientWorld);
            return clientWorld == null ? 0.0F : this.getAngle(itemStack, clientWorld, i, entity);
        }
    }

    @Nullable
    private ClientWorld getClientWorld(Entity entity, @Nullable ClientWorld world) {
        return world == null && entity.getWorld() instanceof ClientWorld ? (ClientWorld)entity.getWorld() : world;
    }

    private float getAngle(ItemStack stack, ClientWorld world, int seed, Entity entity) {
        float yawDegrees = -entity.getYaw();
        return MathHelper.floorMod((yawDegrees - 180.0f) / 360.0f, 1.0f);
    }
}
