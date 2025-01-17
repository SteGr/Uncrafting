package at.stegr.uncrafting.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import at.stegr.uncrafting.main.Uncrafting;
import at.stegr.uncrafting.tile_entity.TileEntityUncrafter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUncrafter extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon top;
	
	private Random random; 
	
	protected BlockUncrafter() {
		super(Material.wood);
		random = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
		this.blockIcon = iconregister.registerIcon("iron_block");
		this.top = iconregister.registerIcon(Uncrafting.MODID + ":Decon_Top");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return top;
		} else {
			return this.blockIcon;
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(Uncrafting.INSTANCE, 0, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntityUncrafter tileentity = (TileEntityUncrafter)world.getTileEntity(x, y, z);
		
		if(tileentity != null) {
			for(int i = 0; i < tileentity.getSizeInventory(); i++) {
				ItemStack itemstack = tileentity.getStackInSlot(1);
				
				if(itemstack != null)
				{
					float f  = this.random.nextFloat() * 0.6F + 0.1F;
					float f1 = this.random.nextFloat() * 0.6F + 0.1F;
					float f2 = this.random.nextFloat() * 0.6F + 0.1F;
					
					while(itemstack.stackSize > 0) {
						int j = this.random.nextInt(21) + 10;
						
						if(j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}
						
						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
						
						if(itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
						
						float f3 = 0.025F;
						entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 1);
						entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3 + 1);
						
						world.spawnEntityInWorld(entityitem);
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityUncrafter();
	}

}
