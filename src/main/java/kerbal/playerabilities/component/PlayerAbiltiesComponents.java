package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import kerbal.playerabilities.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public final class PlayerAbiltiesComponents implements EntityComponentInitializer {
    private class ManaComponentFactory implements ComponentFactory {
        @Override
        public Component createComponent(Object o) {
            return new ManaComponent(o);
        }
    }
    public static final ComponentKey<ManaComponent> MANA = ComponentRegistry.getOrCreate(new Identifier(PlayerAbilities.modId, "mana"), ManaComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MANA, new ManaComponentFactory());
    }
}
