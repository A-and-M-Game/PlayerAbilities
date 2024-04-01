package kerbal.playerabilities;

import kerbal.playerabilities.hud.ManaBarOverlay;
import kerbal.playerabilities.spell.Spell;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class PlayerAbilitiesClient implements ClientModInitializer {
    private static KeyBinding[] spellBindings;

	@Override
	public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new ManaBarOverlay());
        spellBindings = new KeyBinding[PlayerAbilities.spellCount];
        for (int i = 1; i < spellBindings.length + 1; i++) {
            spellBindings[i - 1] = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.playerabilities.spell" + i, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_0 + i, "category.playerabilities.spells"));
        }
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (int i = 1; i < spellBindings.length + 1; i++) {
                if (spellBindings[i - 1].wasPressed()) {
                    client.player.sendMessage(Text.literal("Spell" + i), false);
                }
            }
        });
	}
}