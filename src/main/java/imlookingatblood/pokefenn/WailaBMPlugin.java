package imlookingatblood.pokefenn;

import WayofTime.alchemicalWizardry.ModItems;
import WayofTime.alchemicalWizardry.common.block.BlockAltar;
import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;
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

public class WailaBMPlugin implements IWailaDataProvider
{
    public static void registerWaila(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider(new WailaBMPlugin(), BlockAltar.class);
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
        boolean isAltar = accessor.getTileEntity() instanceof TEAltar;

        if(isAltar)
        {
            TEAltar altar = (TEAltar) accessor.getTileEntity();
            if(!ImLookingAtBlood.doNeedDiviniation)
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:currentFluid") + altar.getFluidAmount());
            else if(accessor.getPlayer().getHeldItem() != null && accessor.getPlayer().getHeldItem().getItem() == ModItems.divinationSigil)
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:currentFluid") + altar.getFluidAmount());

            if(!ImLookingAtBlood.doNeedDiviniation)
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:capacity") + altar.getCapacity());
            else if(accessor.getPlayer().getHeldItem() != null && accessor.getPlayer().getHeldItem().getItem() == ModItems.divinationSigil)
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:capacity") + altar.getCapacity());

            try
            {
                Field f = TEAltar.class.getDeclaredField("upgradeLevel");
                f.setAccessible(true);
                if(!ImLookingAtBlood.doNeedDiviniation)
                    currenttip.add(StatCollector.translateToLocal("imlookingatblood:upgrade") + f.get(accessor.getTileEntity()));
                else if(accessor.getPlayer().getHeldItem() != null && accessor.getPlayer().getHeldItem().getItem() == ModItems.divinationSigil)
                    currenttip.add(StatCollector.translateToLocal("imlookingatblood:upgrade") + f.get(accessor.getTileEntity()));
            } catch(Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                Field f = TEAltar.class.getDeclaredField("progress");
                f.setAccessible(true);
                int progress = (Integer) f.get(accessor.getTileEntity());
                if(accessor.getPlayer().getHeldItem() != null && accessor.getPlayer().getHeldItem().getItem() == ModItems.itemSeerSigil)
                {
                    currenttip.add(StatCollector.translateToLocal("imlookingatblood:time") + f.get(accessor.getTileEntity()));
                }
            } catch(Exception e)
            {
                e.printStackTrace();
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
