package at.stegr.uncrafting.item;

import at.stegr.uncrafting.block.UCBlocks;
import at.stegr.uncrafting.main.Uncrafting;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemUncraftingUpgrade extends Item {

	public ItemUncraftingUpgrade() {
		
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return this.itemIcon;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister) {
		this.itemIcon = iconregister.registerIcon(Uncrafting.MODID + ":Decon_Upgrade");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) return false;
		
		if(player.isSneaking())
		{
			ItemStack item = player.getHeldItem();
			
			if(item != null)
			{
				System.out.println(item.getUnlocalizedName());
				System.out.println(world.getBlock(x, y, z).getUnlocalizedName());
				if(item.getItem().getUnlocalizedName().equals("item.uncrafting_upgrade") && world.getBlock(x, y, z).getUnlocalizedName().equals("tile.workbench"))
				{
					world.setBlock(x, y, z, Blocks.air);
					
					world.setBlock(x, y, z, UCBlocks.BlockUncrafter);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}
		}
		
		return true;
	}

}
