package net.tooltiprareness.mixin;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.tooltiprareness.TooltipRareness;

@Environment(EnvType.CLIENT)
@Mixin(value = Screen.class, priority = 999)
public class ScreenMixin {

    @Shadow
    protected TextRenderer textRenderer;
    @Shadow
    protected Text title;
    @Shadow
    @Nullable
    protected MinecraftClient client;

    @Nullable
    @Unique
    private ItemStack itemStack;

    @Inject(method = "getTooltipFromItem", at = @At("HEAD"))
    private void getTooltipFromItem(ItemStack stack, CallbackInfoReturnable<List<Text>> info) {
        if (stack.equals(null))
            itemStack = null;
        else
            itemStack = stack;
    }

    @Shadow
    public List<Text> getTooltipFromItem(ItemStack stack) {
        return null;
    }

    @ModifyConstant(method = "renderTooltipFromComponents", constant = @Constant(intValue = 1347420415), require = 0)
    private int renderTooltipFromComponentsColorOneMixin(int original, MatrixStack matrices, List<TooltipComponent> components, int x, int y) {
        if (components.size() > 1 && itemStack != null && TooltipRareness.CONFIG.showFrameColor && !(client.currentScreen instanceof CreativeInventoryScreen)) {
            if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.common_item.tooltip").getString()))
                return TooltipRareness.CONFIG.common_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.uncommon_item.tooltip").getString()))
                return TooltipRareness.CONFIG.uncommon_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.rare_item.tooltip").getString()))
                return TooltipRareness.CONFIG.rare_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.epic_item.tooltip").getString()))
                return TooltipRareness.CONFIG.epic_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.legendary_item.tooltip").getString()))
                return TooltipRareness.CONFIG.legendary_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.admin_item.tooltip").getString()))
                return TooltipRareness.CONFIG.administrator_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.mythic_item.tooltip").getString()))
                return TooltipRareness.CONFIG.mythic_frame_color;
        }

        return original;
    }

    @ModifyConstant(method = "renderTooltipFromComponents", constant = @Constant(intValue = 1344798847), require = 0)
    private int renderTooltipFromComponentsColorTwoMixin(int original, MatrixStack matrices, List<TooltipComponent> components, int x, int y) {
        if (components.size() > 1 && TooltipRareness.CONFIG.showFrameColor && !(client.currentScreen instanceof CreativeInventoryScreen))
            if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.common_item.tooltip").getString()))
                return TooltipRareness.CONFIG.common_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.uncommon_item.tooltip").getString()))
                return TooltipRareness.CONFIG.uncommon_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.rare_item.tooltip").getString()))
                return TooltipRareness.CONFIG.rare_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.epic_item.tooltip").getString()))
                return TooltipRareness.CONFIG.epic_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.legendary_item.tooltip").getString()))
                return TooltipRareness.CONFIG.legendary_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.admin_item.tooltip").getString()))
                return TooltipRareness.CONFIG.administrator_second_frame_color;
            else if (getTooltipFromItem(itemStack).get(1).getString().equals(new TranslatableText("item.tooltiprareness.mythic_item.tooltip").getString()))
                return TooltipRareness.CONFIG.mythic_second_frame_color;
        return original;
    }

    // Background color - issue with architectury mod -> incompat with REI
    @ModifyConstant(method = "renderTooltipFromComponents", constant = @Constant(intValue = -267386864), require = 0)
    private int renderTooltipFromComponentsColorThirdMixin(int original) {
        if (TooltipRareness.CONFIG.changeBackgroundColor)
            return TooltipRareness.CONFIG.backgroundColor;
        return original;
    }

    @Inject(method = "renderTooltipFromComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"))
    private void renderTooltipFromComponentsMixin(MatrixStack matrices, List<TooltipComponent> components, int x, int y, CallbackInfo info) {
        if (components.size() > 1 && !TooltipRareness.CONFIG.showRarenessTooltip && !(client.currentScreen instanceof CreativeInventoryScreen))
            components.remove(1);
    }

    @ModifyVariable(method = "renderTooltipFromComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V"), ordinal = 7)
    private int renderTooltipFromComponentsMixin(int original, MatrixStack matrices, List<TooltipComponent> components, int x, int y) {
        if (components.size() > 1 && !TooltipRareness.CONFIG.showRarenessTooltip && !(client.currentScreen instanceof CreativeInventoryScreen)) {
            return original - components.get(1).getHeight();
        } else
            return original;
    }

    // @Inject(method = "Lnet/minecraft/client/gui/screen/Screen;renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;Ljava/util/Optional;II)V", at = @At(value = "INVOKE", target
    // = "Lnet/minecraft/client/gui/screen/Screen;renderTooltipFromComponents(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;II)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    // public void renderTooltip(MatrixStack matrices, List<Text> lines, Optional<TooltipData> data, int x, int y, CallbackInfo info, List<TooltipComponent> list) {
    // // data.ifPresent((datax) -> {
    // Collections.swap(list, 1, 2);
    // // });
    // }
}
