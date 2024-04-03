package kerbal.playerabilities.spell;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

import java.util.Optional;

public interface SpellRegisterCallback {
    Event<SpellRegisterCallback> EVENT = EventFactory.createArrayBacked(SpellRegisterCallback.class,
            (listeners) -> (String spellName) -> {
                for (SpellRegisterCallback listener : listeners) {
                    TypedActionResult<Spell> result = listener.registerSpell(spellName);

                    if (result.getResult() == ActionResult.SUCCESS) {
                        return result;
                    }
                }

                return TypedActionResult.fail(null);
            });

    TypedActionResult<Spell> registerSpell(String spellName);

    static Optional<Spell> getSpellFromName(String name) {
        TypedActionResult<Spell> result = SpellRegisterCallback.EVENT.invoker().registerSpell(name);
        return Optional.ofNullable(result.getValue());
    }
}
