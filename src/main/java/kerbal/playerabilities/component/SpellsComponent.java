package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import kerbal.playerabilities.PlayerAbilities;
import net.minecraft.nbt.NbtCompound;

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
}
