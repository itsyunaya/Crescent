package com.itsyunaya.crescent.features.notifiers.toasts;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class CustomToast implements Toast {
    private final Text title;
    private final Text description;

    private boolean shouldHide = false;

    //TODO: make toast visibility length configurable
    private static final long DURATION = 5000L;

    public CustomToast(Text title, Text description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public Visibility getVisibility() {
        return shouldHide ? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public void update(ToastManager manager, long time) {

    }

    //TODO: finish custom toast
    @Override
    public void draw(DrawContext context, TextRenderer textRenderer, long startTime) {
        context.drawTextWithBackground(textRenderer, title, 18, 5, 300, 15);
        context.drawText(textRenderer, description, 18, 18, 1, true);
        Identifier sprite = Identifier.of("minecraft", "textures/gui/icons.png");

        Function<Identifier, RenderLayer> renderLayers = RenderLayer::getGuiTextured;

        float u = 0f;
        float v = 0f;
        int width = 16;
        int height = 16;
        int textureWidth = 256;
        int textureHeight = 256;
        int color = 1374928456;

        context.drawTexture(
                renderLayers,
                sprite,
                20, 20,
                u, v,
                width, height,
                textureWidth, textureHeight,
                color
        );

        if (startTime >= DURATION) {
            shouldHide = true;
        }
    }

    public static void showDefaultToast() {
        MinecraftClient mc = MinecraftClient.getInstance();

        SystemToast toast = new SystemToast(
                SystemToast.Type.FILE_DROP_FAILURE, // category
                Text.literal("Default Toast"),  // title
                Text.literal("MEOWWWWWW.") // description
        );

        mc.getToastManager().add(toast);
    }
}
