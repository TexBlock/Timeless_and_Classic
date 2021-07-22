package timelessandclassic.common.network;

import com.mrcrayfish.guns.network.message.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.FMLHandshakeHandler;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import timelessandclassic.common.network.messages.FiremodeMessage;
import timelessandclassic.common.network.messages.NotifiCraft;
import timelessandclassic.core.timeless_and_classic;


/*
    Original creator Mr.Crayfish - Adjusted for use in TaC by ClumsyAlien
 */

import java.util.function.Supplier;

public class HPacket
{
    public static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel handshakeChannel;
    private static SimpleChannel playChannel;
    private static int nextMessageId = 0;

    public static void init()
    {
        handshakeChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(timeless_and_classic.ID, "handshake"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        handshakeChannel.messageBuilder(MessageUtilities.C2SAcknowledge.class, 99)
                .loginIndex(MessageUtilities.LoginIndexedMessage::getLoginIndex, MessageUtilities.LoginIndexedMessage::setLoginIndex)
                .decoder(MessageUtilities.C2SAcknowledge::decode)
                .encoder(MessageUtilities.C2SAcknowledge::encode)
                .consumer(FMLHandshakeHandler.indexFirst((handler, msg, s) -> HHandshakeUtilities.handleAcknowledge(msg, s)))
                .add();

        handshakeChannel.messageBuilder(MessageUtilities.S2CUpdateGuns.class, 1)
                .loginIndex(MessageUtilities.LoginIndexedMessage::getLoginIndex, MessageUtilities.LoginIndexedMessage::setLoginIndex)
                .decoder(MessageUtilities.S2CUpdateGuns::decode)
                .encoder(MessageUtilities.S2CUpdateGuns::encode)
                .consumer(FMLHandshakeHandler.biConsumerFor((handler, msg, supplier) -> HHandshakeUtilities.handleUpdateGuns(msg, supplier)))
                .markAsLoginPacket()
                .add();

        playChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(timeless_and_classic.ID, "play"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        registerPlayMessage(NotifiCraft.class, NotifiCraft::new, LogicalSide.SERVER);
        registerPlayMessage(FiremodeMessage.class, FiremodeMessage::new, LogicalSide.SERVER);
    }

    /**
     * Register an {@link IMessage} to the play network channel.
     *
     * @param clazz the class of the message
     * @param messageSupplier a supplier to create an get of the message
     * @param side the logical side this message is to be handled on
     * @param <T> inferred by first parameter, class must implement {@link IMessage}
     */
    private static <T extends IMessage> void registerPlayMessage(Class<T> clazz, Supplier<T> messageSupplier, LogicalSide side)
    {
        playChannel.registerMessage(nextMessageId++, clazz, IMessage::encode, buffer -> {
            T t = messageSupplier.get();
            t.decode(buffer);
            return t;
        }, (t, supplier) -> {
            if(supplier.get().getDirection().getReceptionSide() != side)
                throw new RuntimeException("Attempted to handle message " + clazz.getSimpleName() + " on the wrong logical side!");
            t.handle(supplier);
        });
    }

    /**
     * Gets the handshake network channel for MrCrayfish's Gun Mod
     */
    public static SimpleChannel getHandshakeChannel()
    {
        return handshakeChannel;
    }

    /**
     * Gets the play network channel for MrCrayfish's Gun Mod
     */
    public static SimpleChannel getPlayChannel()
    {
        return playChannel;
    }
}
