package imlookingatblood.pokefenn;

import WayofTime.alchemicalWizardry.common.block.BlockWritingTable;
import WayofTime.alchemicalWizardry.common.tileEntity.TEWritingTable;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class ChemistrySet implements IWailaDataProvider
{
    public static void registerWaila(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider(new ChemistrySet(), BlockWritingTable.class);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        boolean isChemistrySet = accessor.getTileEntity() instanceof TEWritingTable;

        if(isChemistrySet)
        {

            try
            {
                Field f = TEWritingTable.class.getDeclaredField("progress");
                f.setAccessible(true);
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:chemistrySetProgress") + f.get(accessor.getTileEntity()));
            } catch(Exception e)
            {
                e.printStackTrace();
            }

            TEWritingTable chemistrySet = (TEWritingTable) accessor.getTileEntity();

            if(chemistrySet.getResultingItemStack() != null)
            {
                currenttip.add(chemistrySet.getResultingItemStack().getDisplayName());
            }

        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }
}
