package com.swan9854.stthomas.items;

import java.util.ArrayList;

import com.swan9854.stthomas.WaterSense;
import com.swan9854.stthomas.blocks.StaticFluid;
import com.swan9854.stthomas.math.WaterMath;
import com.swan9854.stthomas.utils.BlockMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebuggerItem extends Item {
	public DebuggerItem(String name)
	{
		this.setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.COMBAT);
	}
	
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	        ItemStack itemstack = playerIn.getHeldItem(handIn);
      
	        worldIn.playSound((EntityPlayer)playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

	        if (!worldIn.isRemote)
	        {
	        	RayTraceResult mop = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(200, 1.0F);
	        	if(mop != null)
	        	{
	        		BlockPos pos = new BlockPos(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);
	        		IBlockState ibs = worldIn.getBlockState(pos);
	                Block block = ibs.getBlock();
		        	playerIn.sendMessage(new TextComponentTranslation(block.getLocalizedName() + pos));
		        	
		        	if(worldIn.getBlockState(pos).getBlock().equals(WaterSense.water))
		        	{
		        		BlockMap map = WaterMath.scanwater(new BlockMap(pos), worldIn);
		        		WaterMath.RemoveWaterFromBody(map, 10000, worldIn);
		        	}
		        	
		        	
	        	}	
	        }

	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    }
 
}
