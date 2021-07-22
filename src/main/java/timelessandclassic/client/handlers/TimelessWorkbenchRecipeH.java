package timelessandclassic.client.handlers;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import timelessandclassic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timelessandclassic.core.registry.TimelessRecipeRegistry;
import timelessandclassic.core.registry.TimelessRecipeTypes;

public class TimelessWorkbenchRecipeH implements IRecipe<TimelessWorkbenchTileEntity>
{
    private final ResourceLocation id;
    private final ItemStack item;
    private final ImmutableList<ItemStack> materials;
    private final String group;

    public TimelessWorkbenchRecipeH(ResourceLocation id, ItemStack item, ImmutableList<ItemStack> materials, String group)
    {
        this.id = id;
        this.item = item;
        this.materials = materials;
        this.group = group;
    }

    public ItemStack getItem()
    {
        return this.item.copy();
    }

    public ImmutableList<ItemStack> getMaterials()
    {
        return this.materials;
    }

    @Override
    public boolean matches(TimelessWorkbenchTileEntity inv, World worldIn)
    {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(TimelessWorkbenchTileEntity inv)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return this.item.copy();
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return TimelessRecipeRegistry.WORKBENCH.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return TimelessRecipeTypes.WORKBENCH;
    }

    @Override
    public String getGroup()
    {
        return group;
    }
}
