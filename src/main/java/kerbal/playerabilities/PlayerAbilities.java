package kerbal.playerabilities;

import kerbal.playerabilities.networking.PlayerAbilityPackets;
import kerbal.playerabilities.spell.Spell;
import kerbal.playerabilities.spell.SpellRegisterCallback;
import kerbal.playerabilities.spell.UpdraftSpell;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerAbilities implements ModInitializer {
    public static final String modId = "playerabilities";
    public static final Logger LOGGER = LoggerFactory.getLogger(modId);
    public static final int spellCount = 5;

	@Override
	public void onInitialize() {
		LOGGER.info("Beginning " + modId + " initialization...");
        PlayerAbilityPackets.registerC2SPackets();
        PlayerAbilityPackets.registerS2CPackets();
        registerSpell(new UpdraftSpell());
        LOGGER.info("Finished " + modId + " initialization!");
	}

    public void registerSpell(Spell spell) {
        SpellRegisterCallback.EVENT.register(name -> {
            if (name == spell.getName()) return TypedActionResult.success(spell);
            return TypedActionResult.pass(null);
        });
    }
}