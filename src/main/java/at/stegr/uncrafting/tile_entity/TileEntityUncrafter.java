package at.stegr.uncrafting.tile_entity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityUncrafter extends TileEntity implements IInventory {
	
	private ItemStack[] UncrafterItemStacks = new ItemStack[10];
	private String UncrafterName = null;
	
	public TileEntityUncrafter() {
		super();
	}

	public int getSizeInventory() {
		return this.UncrafterItemStacks.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return this.UncrafterItemStacks[slot];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if(this.UncrafterItemStacks[par1] != null) {
			ItemStack itemstack;
			if(this.UncrafterItemStacks[par1].stackSize <= par2) {
				itemstack = this.UncrafterItemStacks[par1];
				this.UncrafterItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.UncrafterItemStacks[par1].splitStack(par2);
				
				if(this.UncrafterItemStacks[par1].stackSize == 0) {
					this.UncrafterItemStacks[par1] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int slot) {
		if(this.UncrafterItemStacks[slot] != null){
			ItemStack itemstack = this.UncrafterItemStacks[slot];
			this.UncrafterItemStacks[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.UncrafterItemStacks[slot] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.UncrafterName : "Uncrafter";
	}

	public boolean hasCustomInventoryName() {
		return this.UncrafterName != null && this.UncrafterName.length() > 0;
	}

	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Items", 10);
		
		this.UncrafterItemStacks = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound1 = tagList.getCompoundTagAt(i);
			byte byte0 = tagCompound1.getByte("Slot");
			
			if(byte0 >= 0 && byte0 < this.UncrafterItemStacks.length) {
				this.UncrafterItemStacks[byte0] = ItemStack.loadItemStackFromNBT(tagCompound1);
			}
		}
		
		if(tagCompound.hasKey("CustomName", 8)) {
			this.UncrafterName = tagCompound.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		
		NBTTagList tagList = new NBTTagList();
		
		for(int i = 0; i < this.UncrafterItemStacks.length; ++i) {
			if(this.UncrafterItemStacks[i] != null) {
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setByte("Slot", (byte) i);
				this.UncrafterItemStacks[i].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			}
		}
		
		tagCompound.setTag("Items", tagList);
		
		if(this.hasCustomInventoryName()) {
			tagCompound.setString("costumName", this.UncrafterName);
		}
	}
	
	public void updateEntity() {
		
	}

	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}
	

}
