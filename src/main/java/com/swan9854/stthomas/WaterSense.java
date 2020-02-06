package com.swan9854.stthomas;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Logger;

import com.swan9854.stthomas.blocks.StaticFluid;
import com.swan9854.stthomas.events.WaterEvents;
import com.swan9854.stthomas.events.WaterStaticEvents;
import com.swan9854.stthomas.items.DebuggerItem;
import com.swan9854.stthomas.tileEntity.TileEntityWater;
import com.swan9854.stthomas.utils.BlockMap;

@Mod(modid = WaterSense.MODID, name = WaterSense.NAME, version = WaterSense.VERSION)
public class WaterSense
{
    public static final String MODID = "watersense";
    public static final String NAME = "Water Sense";
    public static final String VERSION = "1.0";
    public static final StaticFluid water = new StaticFluid("waterws");
    private static Logger logger;
	public static final DebuggerItem debugger = new DebuggerItem("debugger");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    	MinecraftForge.EVENT_BUS.register(new WaterEvents());
    	MinecraftForge.EVENT_BUS.register(WaterStaticEvents.class);
        logger = event.getModLog();
        GameRegistry.registerTileEntity(TileEntityWater.class, "0");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
        // some example code
    }
    
    
}

