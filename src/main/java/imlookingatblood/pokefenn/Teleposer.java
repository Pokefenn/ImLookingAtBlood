package imlookingatblood.pokefenn;

import WayofTime.alchemicalWizardry.common.block.BlockTeleposer;
import WayofTime.alchemicalWizardry.common.tileEntity.TETeleposer;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class Teleposer implements IWailaDataProvider
{
    public static void registerWaila(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider(new Teleposer(), BlockTeleposer.class);
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
        boolean isTeleposer = accessor.getTileEntity() instanceof TETeleposer;

        if(isTeleposer)
        {
            TETeleposer teleposer = (TETeleposer) accessor.getTileEntity();

            if(teleposer.getStackInSlot(0) != null)
            {
                currenttip.add(teleposer.getStackInSlot(0).getDisplayName());
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
