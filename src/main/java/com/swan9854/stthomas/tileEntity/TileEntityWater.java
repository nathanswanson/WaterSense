package com.swan9854.stthomas.tileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityWater extends TileEntity {

	private Integer count;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if(count == null)
			count = new Integer(1000);
		compound.setInteger("count", count);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if(count == null)
			count = new Integer(1000);
		
		count = compound.getInteger("count");
		super.readFromNBT(compound);
	}
	
	public int getCount() {
		if(count == null)
			count = new Integer(1000);
		
		return count;
	}
	
	public void incrementCount(int amount) {
		if(count == null)
			count = new Integer(1000);
		
		count+=amount;
		markDirty();
	}
	
	public void decrementCount(int amount) {
		if(count == null)
			count = new Integer(1000);
		
		count-=amount;
		markDirty();
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return false;
    }
}