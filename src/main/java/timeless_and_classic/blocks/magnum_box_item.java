package timeless_and_classic.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static timeless_and_classic.core.registry.ItemRegistry.MAGNUM_BULLET;

public class magnum_box_item extends BlockItem {
    public magnum_box_item(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addItemStackToInventory(new ItemStack(MAGNUM_BULLET.get(),12));

        playerIn.getHeldItem(handIn).shrink(1);

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
