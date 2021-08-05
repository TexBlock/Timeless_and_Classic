package timeless_and_classic.core.registry;

import com.mrcrayfish.guns.crafting.DyeItemRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timeless_and_classic.client.handlers.RecipeSerializer;
import timeless_and_classic.core.timeless_and_classic;

public class TimelessRecipeRegistry {
    public static final DeferredRegister<IRecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, timeless_and_classic.ID);

    public static final RegistryObject<SpecialRecipeSerializer<DyeItemRecipe>> DYE_ITEM = REGISTER.register("dye_item", () -> new SpecialRecipeSerializer<>(DyeItemRecipe::new));
    public static final RegistryObject<RecipeSerializer> WORKBENCH = REGISTER.register("timeless_workbench", RecipeSerializer::new);
}
