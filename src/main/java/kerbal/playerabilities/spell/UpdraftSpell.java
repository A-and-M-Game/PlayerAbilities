package kerbal.playerabilities.spell;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class UpdraftSpell extends Spell {
    @Override
    public void cast(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        player.addVelocity(0f, 25f, 0f);
    }

    @Override
    public String getName() {
        return "updraft";
    }

    @Override
    public float getManaCost() {
        return 30;
    }
}
