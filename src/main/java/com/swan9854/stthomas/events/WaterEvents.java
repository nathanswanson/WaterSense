
package com.swan9854.stthomas.events;

import com.swan9854.stthomas.WaterSense;
import com.swan9854.stthomas.blocks.StaticFluid;
import com.swan9854.stthomas.items.DebuggerItem;
import com.swan9854.stthomas.math.WaterMath;
import com.swan9854.stthomas.utils.BlockMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WaterEvents {
	
	private StaticFluid water;
	private DebuggerItem debugger;
	
	public WaterEvents()
	{
		debugger = WaterSense.debugger;
		water = WaterSense.water;
	}
	
    @SubscribeEvent
    public void ItemRegistry(RegistryEvent.Register<Item> event) {
    	ItemBlock ib = new ItemBlock(water);
    	ib.setRegistryName(water.getRegistryName());
    	event.getRegistry().registerAll(debugger, ib);

    }
    
    @SubscribeEvent
    public void BlockRegistry(RegistryEvent.Register<Block> event) {
          	
    	event.getRegistry().registerAll(water);
    	
    }
    
    @SubscribeEvent
    public void rightClickDisable(PlayerInteractEvent.RightClickBlock e)
    {
    	if(e.getEntityPlayer().getHeldItem(e.getHand()).getItem().equals(Items.BUCKET) && e.getWorld().getBlockState(e.getPos()).getBlock().equals(WaterSense.water))
    	{
    		if(!e.getWorld().isRemote)
    		{
    			int slot = e.getEntityPlayer().inventory.currentItem;
    			e.getEntityPlayer().inventory.removeStackFromSlot(slot);
    			BlockMap map = WaterMath.scanwater(new BlockMap(e.getPos()), e.getWorld());
    			WaterMath.RemoveWaterFromBody(map, 1000, e.getWorld());
    			e.getEntityPlayer().inventory.add(slot, new ItemStack(Items.WATER_BUCKET));
    			e.setCanceled(true);
    		}
    	}
    }
    
    @SubscribeEvent
	public void registerRenders(ModelRegistryEvent event) {
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(water), 0, new ModelResourceLocation(water.getRegistryName(), "inventory"));
    	ModelLoader.setCustomModelResourceLocation(debugger, 0, new ModelResourceLocation(debugger.getRegistryName(), "inventory"));
	}
    
    
}