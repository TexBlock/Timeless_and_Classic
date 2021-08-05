package timeless_and_classic.core.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timeless_and_classic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timeless_and_classic.core.timeless_and_classic;

import java.util.function.Supplier;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, timeless_and_classic.ID);

    public static final RegistryObject<TileEntityType<TimelessWorkbenchTileEntity>> TIMELESS_WORKBENCH = register("timeless_workbench", TimelessWorkbenchTileEntity::new, () -> new Block[]{TimelessBlocks.TIMELESS_WORKBENCH.get()});

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String id, Supplier<T> factoryIn, Supplier<Block[]> validBlocksSupplier)
    {
        return REGISTER.register(id, () -> TileEntityType.Builder.create(factoryIn, validBlocksSupplier.get()).build(null));
    }
}
