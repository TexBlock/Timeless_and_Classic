package timeless_and_classic.client.handlers;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import timeless_and_classic.client.screens.TimelessWorkbenchScreen;
import timeless_and_classic.core.registry.TimelessContainers;
import timeless_and_classic.core.timeless_and_classic;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = timeless_and_classic.ID, value = Dist.CLIENT)
public class HClient
{
    private static Field mouseOptionsField;

    public static void setup()
    {
        registerScreenFactories();
    }

    private static void registerScreenFactories()
    {
        ScreenManager.registerFactory(TimelessContainers.WORKBENCH.get(), TimelessWorkbenchScreen::new);
    }
}
