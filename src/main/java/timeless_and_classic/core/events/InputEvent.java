package timeless_and_classic.core.events;

import com.mrcrayfish.guns.client.handler.ShootingHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import org.lwjgl.glfw.GLFW;
import timeless_and_classic.client.TimelessKeyBinds;
import timeless_and_classic.common.network.HPacket;
import timeless_and_classic.common.network.ServerHandler;
import timeless_and_classic.common.network.messages.FiremodeMessage;
import timeless_and_classic.core.timeless_and_classic;

/**
 * Author: Mr. Pineapple
 */
@Mod.EventBusSubscriber(modid = timeless_and_classic.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InputEvent
{

}