package com.swan9854.stthomas.blocks;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.swan9854.stthomas.math.WaterMath;
import com.swan9854.stthomas.tileEntity.BlockTileEntity;
import com.swan9854.stthomas.tileEntity.TileEntityWater;
import com.swan9854.stthomas.utils.AABBAssigner;
import com.swan9854.stthomas.utils.BlockMap;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StaticFluid extends BlockTileEntity<TileEntityWater> {
	//1 through 10
	public static final PropertyInteger AGE = PropertyInteger.create("size", 1, 9);
	private boolean isActivate;
	private int index;
    public StaticFluid (String name) {
        super(Material.ROCK);
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(8)));
        setHardness(1.0f);
        setHarvestLevel("pickaxe", 0);
        setResistance(1.0f);
        setCreativeTab(CreativeTabs.DECORATIONS);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.setSoundType(SoundType.GLASS);

    }
   
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

       
        if (blockState != iblockstate)
        {
            return true;
        }

        if (block == this)
        {
            return false;
        }
        

        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
    
    public int getMetaFromState(IBlockState state) { return 0; }

    public IBlockState getStateFromMeta(int meta) { return this.getDefaultState(); }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABBAssigner.WATER_AABB[state.getValue(StaticFluid.AGE) - 1];
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
    
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.TRANSLUCENT;
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        //System.out.print("NEIGHBOR CHANGED");
    }

    public void updateModel(World worldIn, BlockPos pos)
    {
    	TileEntityWater tile = getTileEntity(worldIn, pos);
    	int fractional = 1000/8;
    	for(int i = 0; i < 8;i++)
    	{
    		if(tile.getCount() < fractional * (i + 1))
    		{
    			try
    			{
    				worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(StaticFluid.AGE, i+1), 2);
    				if(tile.getCount() <= 0)
    					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    				
    					
    			} catch(IllegalArgumentException e)
    			{
    				System.out.print(pos);
    			}
    			break;
    		}
    	}
    }
    
    public void addWater(int amount,World worldIn, BlockPos pos)
    {
    	//unimplemented
    	TileEntityWater tile = getTileEntity(worldIn, pos);
    	tile.incrementCount(amount);
    	updateModel(worldIn,pos);
    }
    
    public void removeWater(int amount, World worldIn, BlockPos pos)
    {
    	TileEntityWater tile = getTileEntity(worldIn, pos);
    	if(tile == null)
    		throw new NullPointerException();
    	
    	tile.decrementCount(amount);
    	int val = tile.getCount();
		updateModel(worldIn,pos);
    }
    
	@Override
	public Class<TileEntityWater> getTileEntityClass() {
		return TileEntityWater.class;
	}
	
	@Nullable
	@Override
	public TileEntityWater createTileEntity(World world, IBlockState state) {
		return new TileEntityWater();
	}


}