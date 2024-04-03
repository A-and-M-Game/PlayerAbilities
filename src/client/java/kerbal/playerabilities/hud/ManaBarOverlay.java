package kerbal.playerabilities.hud;

import kerbal.playerabilities.PlayerAbilities;
import kerbal.playerabilities.component.ManaComponent;
import kerbal.playerabilities.component.PlayerAbiltiesComponents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.MapColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ManaBarOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        //Draws the orb.
        drawContext.drawTexture(new Identifier(PlayerAbilities.modId, "textures/gui/mana_orb.png"),
                screenWidth / 2 + 95, screenHeight - 16, 0, 0, 11, 11, 11, 11);

        //Draws the mana bar.
        int startingX = screenWidth / 2 + 108;
        int endingX = screenWidth - 8;
        int drawY = screenHeight-14;

        Identifier endCap = new Identifier(PlayerAbilities.modId, "textures/gui/mana_bar_cap.png");
        Identifier endSegmentOff = new Identifier(PlayerAbilities.modId, "textures/gui/mana_bar_end_segment_off.png");
        Identifier endSegmentOn = new Identifier(PlayerAbilities.modId, "textures/gui/mana_bar_end_segment_on.png");
        Identifier mainSegmentOff = new Identifier(PlayerAbilities.modId, "textures/gui/mana_bar_main_segment_off.png");
        Identifier mainSegmentOn = new Identifier(PlayerAbilities.modId, "textures/gui/mana_bar_main_segment_on.png");

        float totalMana = PlayerAbiltiesComponents.MANA.get(client.player).getValue();
        float litSegments = (totalMana / ManaComponent.getMaxValue()) * (endingX - startingX);

        drawContext.drawTexture(endCap, startingX, drawY, 0, 0, 1, 7, 1, 7);
        drawSegment(drawContext, startingX + 1, drawY, litSegments, startingX, endSegmentOn, endSegmentOff);
        drawSegment(drawContext, startingX + 2, drawY, litSegments, startingX, endSegmentOn, endSegmentOff);
        for (int i = startingX + 3; i < endingX - 2; i++) {
            drawSegment(drawContext, i, drawY, litSegments, startingX, mainSegmentOn, mainSegmentOff);
        }
        drawSegment(drawContext, endingX - 2, drawY, litSegments, startingX, endSegmentOn, endSegmentOff);
        drawSegment(drawContext, endingX - 1, drawY, litSegments, startingX, endSegmentOn, endSegmentOff);
        drawContext.drawTexture(endCap, endingX, drawY, 0, 0, 1, 7, 1, 7);

        drawContext.draw();
    }

    private void drawSegment(DrawContext drawContext, int x, int y, float litSegments, int startingX, Identifier texture1, Identifier texture2) {
        if (x < litSegments + startingX + 3) {
            drawContext.drawTexture(texture1, x, y, 0, 0, 1, 7, 1, 7);
        } else {
            drawContext.drawTexture(texture2, x, y, 0, 0, 1, 7, 1, 7);
        }
    }
}
