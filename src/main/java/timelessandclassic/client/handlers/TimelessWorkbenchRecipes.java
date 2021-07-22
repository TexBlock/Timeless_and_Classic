package timelessandclassic.client.handlers;

import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import timelessandclassic.core.registry.TimelessRecipeTypes;

import javax.annotation.Nullable;
import java.util.stream.Collectors;

public class TimelessWorkbenchRecipes {
    public static boolean isEmpty(World world)
    {
        return world.getRecipeManager().getRecipes().stream().noneMatch(recipe -> recipe.getType() == TimelessRecipeTypes.WORKBENCH);
    }

    public static NonNullList<TimelessWorkbenchRecipeH> getAll(World world)
    {
        return world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == TimelessRecipeTypes.WORKBENCH)
                .map(recipe -> (TimelessWorkbenchRecipeH) recipe)
                .collect(Collectors.toCollection(NonNullList::create));
    }

    @Nullable
    public static TimelessWorkbenchRecipeH getRecipeById(World world, ResourceLocation id)
    {
        return world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == TimelessRecipeTypes.WORKBENCH)
                .map(recipe -> (TimelessWorkbenchRecipeH) recipe)
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst().orElse(null);
    }
}
