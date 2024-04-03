package kerbal.playerabilities.spell;

import kerbal.playerabilities.component.PlayerAbiltiesComponents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import java.nio.charset.Charset;

public class SpellInstance {
    public Spell baseSpell;

    public SpellInstance(String spellName) {
        baseSpell = SpellRegisterCallback.getSpellFromName(spellName).orElse(new NullSpell(spellName));
    }

    public byte[] toBytes() {
        byte[] bytes = (baseSpell.getName() + " ").getBytes(Charset.defaultCharset());
        return bytes;
    }

    public void cast(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        PlayerAbiltiesComponents.MANA.get(player).addValue(-baseSpell.getManaCost());
        baseSpell.cast(server, player, handler, buf, responseSender);
    }
}
