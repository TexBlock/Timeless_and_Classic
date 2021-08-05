package timeless_and_classic.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import timeless_and_classic.core.timeless_and_classic;

public class TimelessKeyBinds
{
    public static final KeyBinding KEY_UNJAM = new KeyBinding("key." + timeless_and_classic.ID + ".unjam_gun", GLFW.GLFW_KEY_G, "key.categories." + timeless_and_classic.ID);

    public static final KeyBinding KEY_FIREMODE = new KeyBinding("key." + timeless_and_classic.ID + ".fire_mode", GLFW.GLFW_KEY_B, "key.categories." + timeless_and_classic.ID);

    public static void register() {
         ClientRegistry.registerKeyBinding(KEY_FIREMODE);
    }
}
