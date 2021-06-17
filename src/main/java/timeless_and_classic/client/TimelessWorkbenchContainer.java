package timeless_and_classic.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import timeless_and_classic.client.handlers.TimelessWorkbenchRecipes;
import timeless_and_classic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timeless_and_classic.core.registry.TimelessContainers;

public class TimelessWorkbenchContainer extends Container
{
    private final TimelessWorkbenchTileEntity workbench;
    private final BlockPos pos;

    public TimelessWorkbenchContainer(int windowId, IInventory playerInventory, TimelessWorkbenchTileEntity workbench)
    {
        super(TimelessContainers.WORKBENCH.get(), windowId);
        this.workbench = workbench;
        this.pos = workbench.getPos();

        int offset = TimelessWorkbenchRecipes.isEmpty(workbench.getWorld()) ? 0 : 28;

        this.addSlot(new Slot(workbench, 0, 174, 18)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack.getItem() instanceof DyeItem;
            }

            @Override
            public int getSlotStackLimit()
            {
                return 1;
            }
        });

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 102 + y * 18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 160));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return workbench.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();

            if(index == 0)
            {
                if(!this.mergeItemStack(slotStack, 1, 36, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if(slotStack.getItem() instanceof DyeItem)
                {
                    if(!this.mergeItemStack(slotStack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index < 28)
                {
                    if(!this.mergeItemStack(slotStack, 28, 36, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index <= 36 && !this.mergeItemStack(slotStack, 1, 28, false))
                {
                    return ItemStack.EMPTY;
                }
            }

            if(slotStack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if(slotStack.getCount() == stack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return stack;
    }

    public TimelessWorkbenchTileEntity getWorkbench()
    {
        return workbench;
    }

    public BlockPos getPos()
    {
        return pos;
    }

}
