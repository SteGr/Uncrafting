package at.stegr.uncrafting.main;

import at.stegr.uncrafting.tile_entity.TileEntityUncrafter;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void registerRenderers() {
		// Nothing to Render on a Server
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityUncrafter.class, "uncrafting");
	}
}
