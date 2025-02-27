package timeless_and_classic.common.network.messages;

import com.mrcrayfish.guns.network.message.IMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import timeless_and_classic.common.network.ServerHandler;

import java.util.function.Supplier;

public class FiremodeMessage implements IMessage {

    @Override
    public void encode(PacketBuffer buffer) {}

    @Override
    public void decode(PacketBuffer buffer) {}

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            ServerPlayerEntity player = supplier.get().getSender();
            if(player != null && !player.isSpectator()) {
                ServerHandler.handleFireMode(player) ;
            }
        });
        supplier.get().setPacketHandled(true);
    }
}