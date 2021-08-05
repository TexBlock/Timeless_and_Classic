package timelessandclassic.core.registry;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timelessandclassic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timelessandclassic.client.TimelessWorkbenchContainer;
import timelessandclassic.core.timeless_and_classic;

public class TimelessContainers{
    public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, timeless_and_classic.ID);

    public static final RegistryObject<ContainerType<TimelessWorkbenchContainer>> WORKBENCH = register("timeless_workbench", (IContainerFactory<TimelessWorkbenchContainer>) (windowId, playerInventory, data) -> {
        TimelessWorkbenchTileEntity workstation = (TimelessWorkbenchTileEntity) playerInventory.player.world.getTileEntity(data.readBlockPos());
        return new TimelessWorkbenchContainer(windowId, playerInventory, workstation);
    });

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String id, ContainerType.IFactory<T> factory)
    {
        return REGISTER.register(id, () -> new ContainerType<>(factory));
    }
}
