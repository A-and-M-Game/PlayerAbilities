package kerbal.playerabilities;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import kerbal.playerabilities.component.ManaComponent;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerAbilities implements ModInitializer {
    public static final String modId = "playerabilities";
    public static final Logger LOGGER = LoggerFactory.getLogger(modId);
    public static final int spellCount = 5;

	@Override
	public void onInitialize() {
		LOGGER.info("Beginning " + modId + " initialization...");
        PlayerAbilityPackets.register();
        LOGGER.info("Finished " + modId + " initialization!");
	}
}