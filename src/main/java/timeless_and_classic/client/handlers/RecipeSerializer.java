package timeless_and_classic.client.handlers;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RecipeSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TimelessWorkbenchRecipeH>
{
    @Override
    public TimelessWorkbenchRecipeH read(ResourceLocation recipeId, JsonObject json)
    {
        String group = JSONUtils.getString(json, "group", "");
        ImmutableList.Builder<ItemStack> builder = ImmutableList.builder();
        JsonArray input = JSONUtils.getJsonArray(json, "materials");
        for(int i = 0; i < input.size(); i++)
        {
            JsonObject itemObject = input.get(i).getAsJsonObject();
            ItemStack stack = ShapedRecipe.deserializeItem(itemObject);
            builder.add(stack);
        }
        if(!json.has("result"))
            throw new JsonSyntaxException("Missing vehicle entry");

        JsonObject resultObject = JSONUtils.getJsonObject(json, "result");
        ItemStack resultItem = ShapedRecipe.deserializeItem(resultObject);
        return new TimelessWorkbenchRecipeH(recipeId, resultItem, builder.build(), group);
    }

    @Nullable
    @Override
    public TimelessWorkbenchRecipeH read(ResourceLocation recipeId, PacketBuffer buffer)
    {
        String group = buffer.readString();
        ItemStack result = buffer.readItemStack();
        ImmutableList.Builder<ItemStack> builder = ImmutableList.builder();
        int size = buffer.readVarInt();
        for(int i = 0; i < size; i++)
            builder.add(buffer.readItemStack());
        return new TimelessWorkbenchRecipeH(recipeId, result, builder.build(), group);
    }

    @Override
    public void write(PacketBuffer buffer, TimelessWorkbenchRecipeH recipe) {
        buffer.writeString(recipe.getGroup());
        buffer.writeItemStack(recipe.getItem());
        buffer.writeVarInt(recipe.getMaterials().size());
        for(ItemStack stack : recipe.getMaterials())
        {
            buffer.writeItemStack(stack);
        }
    }

}
