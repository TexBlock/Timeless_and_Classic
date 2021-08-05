package timelessandclassic.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static timelessandclassic.core.registry.ItemRegistry.BULLET_45;

public class box_45_item extends BlockItem {
    public box_45_item(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addItemStackToInventory(new ItemStack(BULLET_45.get(),16));

        playerIn.getHeldItem(handIn).shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
