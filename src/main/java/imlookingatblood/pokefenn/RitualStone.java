package imlookingatblood.pokefenn;

import WayofTime.alchemicalWizardry.api.rituals.Rituals;
import WayofTime.alchemicalWizardry.common.block.BlockMasterStone;
import WayofTime.alchemicalWizardry.common.tileEntity.TEMasterStone;
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
public class RitualStone implements IWailaDataProvider
{
    public static void registerWaila(IWailaRegistrar registrar)
    {
        registrar.registerBodyProvider(new RitualStone(), BlockMasterStone.class);
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
        boolean isMasterRitualStone = accessor.getTileEntity() instanceof TEMasterStone;

        if(isMasterRitualStone)
        {
            TEMasterStone ritualStone = (TEMasterStone) accessor.getTileEntity();

            if(!ritualStone.getOwner().equals(""))
                currenttip.add(StatCollector.translateToLocal("imlookingatblood:owner") + ritualStone.getOwner());

            try
            {
                Field f = TEMasterStone.class.getDeclaredField("currentRitualString");
                f.setAccessible(true);
                String ritualName = (String) f.get(accessor.getTileEntity());
                if(!ritualName.equals(""))
                    currenttip.add(Rituals.getNameOfRitual(ritualName));
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
