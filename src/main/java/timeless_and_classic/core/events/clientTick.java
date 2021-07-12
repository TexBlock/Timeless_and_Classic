package timeless_and_classic.core.events;

import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.client.handler.ControllerHandler;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.network.PacketHandler;
import com.mrcrayfish.guns.network.message.MessageShooting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import timeless_and_classic.core.timeless_and_classic;

@Mod.EventBusSubscriber(modid = timeless_and_classic.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class clientTick
{

}
