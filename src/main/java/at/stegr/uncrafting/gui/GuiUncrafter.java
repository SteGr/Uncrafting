package at.stegr.uncrafting.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import at.stegr.uncrafting.inventory.ContainerUncrafter;
import at.stegr.uncrafting.main.Uncrafting;
import at.stegr.uncrafting.tile_entity.TileEntityUncrafter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiUncrafter extends GuiContainer {
	
	private static final ResourceLocation UncrafterGuiTextures = new ResourceLocation(Uncrafting.MODID + ":textures/gui/DCGui.png");
	private TileEntityUncrafter tileUncrafter;

	public GuiUncrafter(EntityPlayer player, TileEntityUncrafter tile) {
		super(new ContainerUncrafter(player, tile));
		this.tileUncrafter = tile;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String string = this.tileUncrafter.hasCustomInventoryName() ? this.tileUncrafter.getInventoryName() : I18n.format(this.tileUncrafter.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory",  new Object[0]), 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(UncrafterGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
