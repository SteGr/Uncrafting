package at.stegr.uncrafting.block;

import java.util.Iterator;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.init.Blocks;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.creativetab.CreativeTabs;

public class UCBlocks {
	
	public static void mainRegistry() {
		initializeBlocks();
		registerBlocks();
	}
	
	public static Block BlockUncrafter;
	
	public static void initializeBlocks() {
		BlockUncrafter = new BlockUncrafter().setBlockName("uncrafter").setCreativeTab(CreativeTabs.tabRedstone);
		
		Blocks.command_block.setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(BlockUncrafter, "uncrafter");
	}

}
