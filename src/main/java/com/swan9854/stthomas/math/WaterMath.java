package com.swan9854.stthomas.math;

import com.swan9854.stthomas.WaterSense;
import com.swan9854.stthomas.blocks.StaticFluid;
import com.swan9854.stthomas.utils.BlockMap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterMath {
	private static BlockMap scanwater(BlockMap map, World world, int x , int z) {
		//this method is run on the assumption that the incoming position is a water block
		
		//add block to registry
		map.addByPosition(x, z);
		map.setPos(x, z);
		//find referenced block
		int blockX = map.getBlockPos().getX() + x;
		int blockY = map.getBlockPos().getY();
		int blockZ = map.getBlockPos().getZ() + z;
		
		
		int[][] posList = {{1,0},{-1,0},{0,1},{0,-1}};
		for(int i = 0; i < 4; i++)
		{
			BlockPos newPos = new BlockPos(blockX,blockY,blockZ).add(posList[i][0], 0, posList[i][1]);
			
			int biasedX = x + posList[i][0];
			int biasedZ = z + posList[i][1]; 
						
			map.addByPosition(biasedX, biasedZ);
			//boolean val = map.eval(biasedX, biasedZ);
			if(world.getBlockState(newPos).getBlock() == WaterSense.water & !map.evalOnCord(biasedX, biasedZ))
			{
				scanwater(map, world, biasedX, biasedZ);
			}
		}
		
		return map;
	}
	
	public static BlockMap scanwater(BlockMap map, World world)
	{
		return scanwater(map,world,0,0);
	}

	public static void RemoveWaterFromBody(BlockMap water, int amount, World worldIn) {
		// TODO Auto-generated method stub
		boolean[][] map = water.getMap();
		int fraction = amount / water.getSizeOfWater();
		
		for(int x = 0; x < map.length; x++)
			for(int y =0; y < map[0].length;y++)
			{
				if(water.eval(x, y))
				{
					BlockPos pos = water.getBlock(x, y);
					
	        		StaticFluid ibs = (StaticFluid)worldIn.getBlockState(pos).getBlock();
	        		if(ibs instanceof StaticFluid)
	        			ibs.removeWater(fraction, worldIn, pos);

				}
			}
	}
}
