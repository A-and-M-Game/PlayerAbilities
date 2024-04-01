package kerbal.playerabilities;

import kerbal.playerabilities.hud.ManaBarOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class PlayerAbilitiesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new ManaBarOverlay());
	}
}