package kerbal.playerabilities.networking;

import kerbal.playerabilities.PlayerAbilities;
import kerbal.playerabilities.spell.SpellInstance;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class PlayerAbilityPackets {
    public static final Identifier SPELL_CASTING_ID = new Identifier(PlayerAbilities.modId, "spell_cast");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SPELL_CASTING_ID, PlayerAbilityPackets::receiveSpellPacket);
    }

    public static void registerS2CPackets() {}

    public static void receiveSpellPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        SpellInstance instance = new SpellInstance(buf.readString());
        instance.cast(server, player, handler, buf, responseSender);
    }
}
