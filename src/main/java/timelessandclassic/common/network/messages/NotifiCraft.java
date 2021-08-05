package timelessandclassic.common.network.messages;

import com.mrcrayfish.guns.network.message.IMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import timelessandclassic.common.network.ServerHandler;

import java.util.function.Supplier;

public class NotifiCraft implements IMessage
{
    private ResourceLocation id;
    private BlockPos pos;

    public NotifiCraft() {}

    public NotifiCraft(ResourceLocation id, BlockPos pos)
    {
        this.id = id;
        this.pos = pos;
    }

    @Override
    public void encode(PacketBuffer buffer)
    {
        buffer.writeResourceLocation(this.id);
        buffer.writeBlockPos(this.pos);
    }

    @Override
    public void decode(PacketBuffer buffer)
    {
        this.id = buffer.readResourceLocation();
        this.pos = buffer.readBlockPos();
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() ->
        {
            ServerPlayerEntity player = supplier.get().getSender();
            if(player != null)
            {
                ServerHandler.handleCraft(player, this.id, this.pos);
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
