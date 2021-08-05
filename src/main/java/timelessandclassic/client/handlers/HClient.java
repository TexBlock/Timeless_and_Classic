package timelessandclassic.client.handlers;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import timelessandclassic.client.screens.TimelessWorkbenchScreen;
import timelessandclassic.core.registry.TimelessContainers;
import timelessandclassic.core.timeless_and_classic;

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
