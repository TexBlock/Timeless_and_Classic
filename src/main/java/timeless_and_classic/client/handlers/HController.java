package timeless_and_classic.client.handlers;

import com.mrcrayfish.controllable.client.gui.navigation.BasicNavigationPoint;
import com.mrcrayfish.controllable.event.GatherNavigationPointsEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import timeless_and_classic.client.screens.TimelessWorkbenchScreen;

public class HController {
    @SubscribeEvent
    public void onGatherNavigationPoints(GatherNavigationPointsEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        if(mc.currentScreen instanceof TimelessWorkbenchScreen)
        {
            TimelessWorkbenchScreen workbench = (TimelessWorkbenchScreen) mc.currentScreen;
            int startX = workbench.getGuiLeft();
            int startY = workbench.getGuiTop();

            for(int i = 0; i < workbench.getTabs().size(); i++)
            {
                int tabX = startX + 28 * i + (28 / 2);
                int tabY = startY - (28 / 2);
                event.addPoint(new BasicNavigationPoint(tabX, tabY));
            }

            for(int i = 0; i < 6; i++)
            {
                int itemX = startX + 172 + (80 / 2);
                int itemY = startY + i * 19 + 63 + (19 / 2);
                event.addPoint(new BasicNavigationPoint(itemX, itemY));
            }
        }
    }
}
