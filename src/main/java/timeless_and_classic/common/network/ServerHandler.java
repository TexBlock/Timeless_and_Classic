package timeless_and_classic.common.network;

import com.mrcrayfish.guns.item.IColored;
import com.mrcrayfish.guns.util.InventoryUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import timeless_and_classic.client.handlers.TimelessWorkbenchRecipeH;
import timeless_and_classic.client.handlers.TimelessWorkbenchRecipes;
import timeless_and_classic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timeless_and_classic.client.TimelessWorkbenchContainer;
import timeless_and_classic.core.registry.SoundRegistry;
import timeless_and_classic.items.TimelessGunItem;

import java.util.List;


/*
    Original creator Mr.Crayfish - Adjusted for use in TaC by ClumsyAlien
 */

public class ServerHandler
{
    public static void handleUnjam(ServerPlayerEntity player) {
        ItemStack stack = player.getHeldItemMainhand();
        if(stack.getItem() instanceof TimelessGunItem) {
            if(stack.getTag().getBoolean("isJammed")) {
                stack.getTag().remove("isJammed");
                player.playSound(SoundRegistry.M60_FIRE.get(), SoundCategory.PLAYERS, 1F, 1F);
            }
        }
    }

    public static void handleFireMode(ServerPlayerEntity player) {
        ItemStack stack = player.getHeldItemMainhand();
        //if(stack.getItem() instanceof GunItem) {
            //if(stack.getTag().getBoolean("auto"))
            //{
                stack.getTag().remove("auto");
                stack.getTag().putBoolean("auto",false);
        //    }
        //    else
        //    {
                stack.getTag().remove("auto");
                stack.getTag().putBoolean("auto",true);
        //    }
        //}
    }

    public static void handleCraft(ServerPlayerEntity player, ResourceLocation id, BlockPos pos)
    {
        World world = player.world;

        if(player.openContainer instanceof TimelessWorkbenchContainer)
        {
            TimelessWorkbenchContainer workbench = (TimelessWorkbenchContainer) player.openContainer;
            if(workbench.getPos().equals(pos))
            {
                TimelessWorkbenchRecipeH recipe = TimelessWorkbenchRecipes.getRecipeById(world, id);
                if(recipe == null)
                {
                    return;
                }

                List<ItemStack> materials = recipe.getMaterials();
                if(materials != null)
                {
                    for(ItemStack stack : materials)
                    {
                        if(!InventoryUtil.hasItemStack(player, stack))
                        {
                            return;
                        }
                    }

                    for(ItemStack stack : materials)
                    {
                        InventoryUtil.removeItemStack(player, stack);
                    }

                    TimelessWorkbenchTileEntity workbenchTileEntity = workbench.getWorkbench();

                    /* Gets the color based on the dye */
                    ItemStack stack = recipe.getItem();
                    ItemStack dyeStack = workbenchTileEntity.getInventory().get(0);
                    if(dyeStack.getItem() instanceof DyeItem)
                    {
                        DyeItem dyeItem = (DyeItem) dyeStack.getItem();
                        int color = dyeItem.getDyeColor().getColorValue();

                        if(stack.getItem() instanceof IColored && ((IColored) stack.getItem()).canColor(stack))
                        {
                            IColored colored = (IColored) stack.getItem();
                            colored.setColor(stack, color);
                            workbenchTileEntity.getInventory().set(0, ItemStack.EMPTY);
                        }
                    }

                    InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 1.125, pos.getZ() + 0.5, stack);
                }
            }
        }
    }
}
