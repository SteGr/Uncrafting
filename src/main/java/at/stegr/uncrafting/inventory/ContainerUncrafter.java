package at.stegr.uncrafting.inventory;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotMerchantResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import at.stegr.uncrafting.tile_entity.TileEntityUncrafter;

public class ContainerUncrafter extends Container {
	
	private TileEntityUncrafter tileUncrafter;
	
	public ContainerUncrafter(EntityPlayer player, TileEntityUncrafter tile) {
		this.tileUncrafter = tile;
		
		this.addSlotToContainer(new Slot(tile, 0,  43, 35));
		
		this.addSlotToContainer(new UncrafterSlot(tile, 1,  97, 17));
		this.addSlotToContainer(new UncrafterSlot(tile, 2, 115, 17));
		this.addSlotToContainer(new UncrafterSlot(tile, 3, 133, 17));
		this.addSlotToContainer(new UncrafterSlot(tile, 4,  97, 35));
		this.addSlotToContainer(new UncrafterSlot(tile, 5, 115, 35));
		this.addSlotToContainer(new UncrafterSlot(tile, 6, 133, 35));
		this.addSlotToContainer(new UncrafterSlot(tile, 7,  97, 53));
		this.addSlotToContainer(new UncrafterSlot(tile, 8, 115, 53));
		this.addSlotToContainer(new UncrafterSlot(tile, 9, 133, 53));
		int i;
		
		for(i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileUncrafter.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack itemstack = null;
		
		Slot slot = (Slot)this.inventorySlots.get(par2);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(par2 == 1 || par2 == 2 || par2 == 3 || par2 == 4 || par2 == 5 || par2 == 6 || par2 == 7 || par2 == 8 || par2 == 9) {
				if(!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if(par2 != 0) {
				if(slot.getStack() == null) {
					if(!this.mergeItemStack(itemstack1, 0, 1, true)) {
						return null;
					}
				} else if(par2 >= 10 && par2 < 37) {
					if(!this.mergeItemStack(itemstack1, 37, 46, false)) {
						return null;
					}
				} else if(par2 >= 37 && par2 < 46){
					if(!this.mergeItemStack(itemstack1, 10, 37, false)) {
						return null;
					}
				}
			} else if(!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return null;
			}
		
		
			if(itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		
			if(itemstack1.stackSize == itemstack1.stackSize) {
				return null;
			}
		
			slot.onPickupFromSlot(player, itemstack);
		
		}
		
		return itemstack;
		
		this.
	}

}
