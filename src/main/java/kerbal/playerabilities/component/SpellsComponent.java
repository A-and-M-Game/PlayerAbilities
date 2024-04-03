package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import kerbal.playerabilities.PlayerAbilities;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpellsComponent implements MultiStringComponent, AutoSyncedComponent {
    public String[] spells;

    public SpellsComponent() {
        spells = new String[PlayerAbilities.spellCount];
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        String totalString = tag.getString("spells");
        spells = totalString.split(" ");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        String totalString = "";
        for (String str : spells) {
            totalString += str + " ";
        }
        tag.putString("spells", totalString);
    }

    @Override
    public String getValue(int i) {
        return spells[i];
    }

    @Override
    public void setValue(int i, String str) {
        spells[i] = str;
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity player) {
        String totalString = "";
        for (String str : spells) {
            totalString += str + " ";
        }
        buf.writeString(totalString);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        String totalString = buf.readString();
        spells = totalString.split(" ");
    }
}
