package kerbal.playerabilities.spell;

import kerbal.playerabilities.PlayerAbilities;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class NullSpell extends Spell {
    private String spellName;

    public NullSpell(String spellName) {
        this.spellName = spellName;
    }
    @Override
    public void cast(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        PlayerAbilities.LOGGER.error("Attempted to cast invalid spell! Spell name: " + spellName);
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public float getManaCost() {
        return 0;
    }
}