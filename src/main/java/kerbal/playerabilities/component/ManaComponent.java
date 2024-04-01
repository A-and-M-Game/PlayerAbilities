package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManaComponent implements FloatComponent, AutoSyncedComponent, ServerTickingComponent {
    private final static float minValue = 0;
    private final static float maxValue = 100;
    private float mana;
    private Object provider;

    public ManaComponent(Object provider) {
        this.provider = provider;
        this.mana = minValue;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        mana = tag.getFloat("mana");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putFloat("mana", mana);
    }

    @Override
    public float getValue() {
        return mana;
    }

    public static float getMaxValue() {
        return maxValue;
    }

    public static float getMinValue() {
        return minValue;
    }

    public float getRegenRate() {
        return 0.5f;
    }

    @Override
    public void setValue(float value) {
        value = Math.max(minValue, value);
        value = Math.min(maxValue, value);
        if (value == mana) return;
        mana = value;
        PlayerAbiltiesComponents.MANA.sync(provider);
    }

    @Override
    public void addValue(float offset) {
        setValue(mana + offset);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity player) {
        buf.writeFloat(mana);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        mana = buf.readFloat();
    }

    @Override
    public void serverTick() {
        addValue(getRegenRate());
    }
}
