package timeless_and_classic.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static timeless_and_classic.core.registry.ItemRegistry.BULLET_556;

public class box_556_item extends BlockItem {
    public box_556_item(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addItemStackToInventory(new ItemStack(BULLET_556.get(),50));

        playerIn.getHeldItem(handIn).shrink(1);

        // Currently have to remove the damage feature of the box as placing down does not save the ammo state in any way
        // playerIn.getHeldItem(handIn).damageItem(1,playerIn,playerEntity -> playerEntity.getHeldItem(handIn).shrink(1));

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
