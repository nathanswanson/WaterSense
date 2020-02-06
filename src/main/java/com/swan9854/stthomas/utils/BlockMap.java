package com.swan9854.stthomas.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class BlockMap 
{
	private boolean[][] posMap;
	
	private int centerX;
	private int centerY;
	
	private BlockPos start;
	
	public BlockMap(BlockPos start)
	{
		this.start = start;
		
		centerX = 0;
		centerY = 0;
		//start with one block
		posMap = new boolean[1][1];
		//start of object creation should be water
		posMap[0][0] = true;
	}
	
	public void addByPosition(int indexX, int indexY)
	{
		verifyMap(indexX, indexY);
		//setPos(indexX, indexY);
		//posMap[indexX + this.getCenterX()][indexY + this.getCenterY()] = true;
		//System.out.println(Arrays.deepToString(posMap).replace("],", "\n").replace("[[", "\n").replace("true", "\u2B1B"));
		
	}
	
	public void setPos(int indexX, int indexY)
	{
		verifyMap(indexX, indexY);
		posMap[indexX + this.getCenterX()][indexY + this.getCenterY()] = true;
	}
	
	private void verifyMap(int indexX, int indexY) {
		boolean negativeX = indexX < 0;
		boolean negativeY = indexY < 0;
		
		// TODO Auto-generated method stub
		int lengthSave = posMap.length;
		int lengthSaveY = posMap[0].length;
		
		int centerX = this.getCenterX();
		int centerY = this.getCenterY();
		
		if(!negativeX)
		{
			for(int XIterate = indexX + 1; XIterate > lengthSave - centerX; XIterate--)
			{
				addRow();
			}
		}
		else
		{
			int savedCenterX = this.getCenterX();
			for(int XIterate = Math.abs(indexX); savedCenterX < XIterate; XIterate--)
			{
				addFrontRow();
			}
		}
		if(!negativeY)
		{
			for(int YIterate = indexY + 1; YIterate > lengthSaveY - centerY; YIterate--)
			{
				addColumn();
			}
		}
		else
		{
			int savedCenterY = this.getCenterY();
			for(int YIterate = Math.abs(indexY); savedCenterY < YIterate; YIterate--)
			{
				addFrontColumn();
			}
		}
	}

	private void addColumn() {
		// TODO Auto-generated method stub
		boolean[][] newMap = new boolean[getMap().length][getMap()[0].length + 1];
		//refills array
		for(int x = 0; x < posMap.length;x++)
		{
			for(int y = 0; y < posMap[0].length; y++)
			{
				newMap[x][y] = posMap[x][y];
			}
		}
	
		posMap = newMap;
	}

	private void addRow() {
		// TODO Auto-generated method stub	
		//create new map with the indexX set as length
		boolean[][] newMap = new boolean[getMap().length + 1][getMap()[0].length];
		//refills array
		for(int x = 0; x < posMap.length;x++)
		{
			for(int y = 0; y < posMap[0].length; y++)
			{
				newMap[x][y] = this.getMap()[x][y];
			}
		}
		
		
		this.setMap(newMap);
	}
	
	private void addFrontColumn()
	{
		//since we are adding a negative column the 0 is now added one
		this.setCenterY(this.getCenterY() + 1);
		
		boolean[][] newMap = new boolean[getMap().length][getMap()[0].length + 1];
		//refills array
		for(int x = 0; x < this.getMap().length;x++)
		{
			for(int y = 0; y < this.getMap()[0].length; y++)
			{
				newMap[x][y + 1] = this.getMap()[x][y];
			}
		}
		
		
		posMap = newMap;
	}

	private void addFrontRow()
	{
	
	//since we are adding a negative column the 0 is now added one
		this.setCenterX(this.getCenterX() + 1);
		
		boolean[][] newMap = new boolean[getMap().length + 1][getMap()[0].length];
		//refills array
		for(int x = 0; x < this.getMap().length;x++)
		{
			for(int y = 0; y < this.getMap()[0].length; y++)
			{
				newMap[x + 1][y] = this.getMap()[x][y];
			}
		}
		
		
		posMap = newMap;
	}
	
	private void setCenterY(int i) {
		// TODO Auto-generated method stub
		this.centerY = i;
	}

	private int getCenterY() {
		// TODO Auto-generated method stub
		return this.centerY;
	}

	public boolean[][] getMap()
	{
		return this.posMap;
	}
	
	//test
	public BlockPos getBlockPos()
	{
		return this.start;
	}
	
	private void setMap(boolean[][] input)
	{
		this.posMap = input;
	}	
	
	private int getCenterX()
	{
		return this.centerX;
	}
	
	private void setCenterX(int input)

	{
		this.centerX = input;
	}
	
	public BlockMap printMap()
	{
		System.out.println("\u2B1B = water");
		for(int x = 0; x < this.getMap().length; x++)
		{
			for(int y = 0; y < this.getMap()[0].length; y++)
			{
				System.out.print((this.getMap()[x][y]) ? "\u2B1B" : "\u2B1C");
			}
			System.out.println();
		}
		return this;
	}


	public boolean eval(int biasedX, int biasedZ) {
		// TODO Auto-generated method stub
		try
		{
			return this.getMap()[biasedX][biasedZ];
		} catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.toString() + " code failed with a biasedX of " + biasedX + " biasedZ of " + biasedZ);
		}
		return false;
	}
	
	public boolean evalOnCord(int biasedX, int biasedZ) {
		// TODO Auto-generated method stub
		try
		{
			return this.getMap()[biasedX + this.getCenterX()][biasedZ + this.getCenterY()];
		} catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.toString() + " --code failed with a biasedX of " + biasedX + " biasedZ of " + biasedZ);
		}
		return false;
	}
	
	public int getSizeOfWater()
	{
		int index = 0;
		
		for(int x = 0; x < this.getMap().length; x++)
		{
			for(int y = 0; y < this.getMap()[0].length; y++)
			{
				if(this.getMap()[x][y])
					index++;
			}
		}
		return index;
	}
	
	public BlockPos getBlock(int x,int z)
	{
		int posX = this.getBlockPos().getX() + (x - this.getCenterX());
		int posY = this.getBlockPos().getY();
		int posZ = this.getBlockPos().getZ() + (z - this.getCenterY());
		return new BlockPos(posX,posY,posZ);
	}
	
	public int lengthX()
	{
		return this.getMap().length;
	}
	
	public int lengthY()
	{
		return this.getMap()[0].length;
	}
}

