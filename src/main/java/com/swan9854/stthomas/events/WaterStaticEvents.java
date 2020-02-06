package com.swan9854.stthomas.events;

import com.swan9854.stthomas.WaterSense;
import com.swan9854.stthomas.blocks.StaticFluid;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WaterStaticEvents {
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(ChunkEvent.Load event)
    { 
          
      Chunk theChunk = event.getChunk();
          
      // replace all blocks of a type with another block type
      for (int x = 0; x < 16; ++x) 
      {
         for (int z = 0; z < 16; ++z) 
         {
             for (int y = 0; y < theChunk.getHeightValue(x, z)+1; ++y) 
             {
                if (theChunk.getBlockState(x, y, z).getBlock() == Blocks.WATER)
                {
                	//WaterSense.water.createTileEntity(theChunk.getWorld(), WaterSense.water.getDefaultState());
                    theChunk.setBlockState(new BlockPos(x, y, z), WaterSense.water.getDefaultState());
                }
              }
          }
      }
      theChunk.markDirty();
    }

}

