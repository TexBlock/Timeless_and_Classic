package timeless_and_classic.core.registry;

import com.mrcrayfish.guns.crafting.WorkbenchRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import timeless_and_classic.client.handlers.TimelessWorkbenchRecipeH;

public class TimelessRecipeTypes {
    public static final IRecipeType<TimelessWorkbenchRecipeH> WORKBENCH = register("timeless_and_classic:timeless_workbench");

    static <T extends IRecipe<?>> IRecipeType<T> register(final String key)
    {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>()
        {
            @Override
            public String toString()
            {
                return key;
            }
        });
    }
}