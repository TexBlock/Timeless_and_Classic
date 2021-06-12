package timeless_and_classic.blocks;

import com.mrcrayfish.guns.block.RotatedObjectBlock;
import com.mrcrayfish.guns.tileentity.WorkbenchTileEntity;
import com.mrcrayfish.guns.util.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import timeless_and_classic.client.render.tileentity.TimelessWorkbenchTileEntity;

import javax.annotation.Nullable;
import java.util.*;

public class Timeless_Workbench extends RotatedObjectBlock
{
    private final Map<BlockState, VoxelShape> SHAPES = new HashMap<>();

    public Timeless_Workbench(Block.Properties properties)
    {
        super(properties);
    }

    private VoxelShape getShape(BlockState state)
    {
        if(SHAPES.containsKey(state))
        {
            return SHAPES.get(state);
        }
        Direction direction = state.get(HORIZONTAL_FACING);
        List<VoxelShape> shapes = new ArrayList<>();
        shapes.add(Block.makeCuboidShape(0.5, 0, 0.5, 15.5, 13, 15.5));
        shapes.add(Block.makeCuboidShape(0, 13, 0, 16, 15, 16));
        shapes.add(VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.makeCuboidShape(0, 15, 0, 16, 16, 2), Direction.SOUTH))[direction.getHorizontalIndex()]);
        VoxelShape shape = VoxelShapeHelper.combineAll(shapes);
        SHAPES.put(state, shape);
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return this.getShape(state);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return this.getShape(state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult result)
    {
        if(!world.isRemote())
        {
            TileEntity tileEntity = world.getTileEntity(pos);
            if(tileEntity instanceof INamedContainerProvider)
            {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (INamedContainerProvider) tileEntity, pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TimelessWorkbenchTileEntity();
    }
}
