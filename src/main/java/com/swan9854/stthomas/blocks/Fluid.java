package com.swan9854.stthomas.blocks;

import javax.annotation.Nullable;

import com.swan9854.stthomas.utils.AABBAssigner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class Fluid extends Block{

	//assigned as object
	private Integer MBOfWater;
	private Integer velocity;
	
	public Fluid(Material materialIn) {
		super(materialIn);
		// TODO Auto-generated constructor stub
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABBAssigner.WATER_AABB[9];
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
    
    public int getWaterLevel()
    {
    	return MBOfWater != null ? MBOfWater: 1000;
    }
    
    public void addWater()
    {
    	//unimplemented
    }
    
    public void removeWater(int amount)
    {
    	//unimplemented
    }
    
    public int velocity()
    {
    	return velocity != null ? velocity: 0;
    }
}
