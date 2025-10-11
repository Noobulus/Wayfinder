package dev.nitron.wayfinder.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class LifeformAnalyzerItem extends Item implements ColorableItem {
    public LifeformAnalyzerItem(Settings settings) {
        super(settings);
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

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()){
            tooltip.add(Text.literal("Hold down [").append(Text.literal("Shift").withColor(0xf7db70)).append("] for details.").formatted(Formatting.DARK_GRAY));
            tooltip.add(Text.literal(" "));
            tooltip.add(Text.literal("Use on mobs in order ").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("to scan, research and learn ").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("about what they do. ").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Hold down [").append(Text.literal("Shift").formatted(Formatting.GRAY)).append("] for details.").formatted(Formatting.DARK_GRAY));
        }
    }
}
