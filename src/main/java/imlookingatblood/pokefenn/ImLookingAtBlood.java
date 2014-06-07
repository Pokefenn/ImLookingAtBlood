package imlookingatblood.pokefenn;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */

@Mod(name = "I'm Looking At Blood", modid = "ImLookingAtBlood", version = "1.0", dependencies = "after:Waila;")
public class ImLookingAtBlood
{

    @Mod.EventHandler
    public void fmlPreInitiation(FMLPreInitializationEvent fmlPreInitializationEvent)
    {

    }

    @Mod.EventHandler
    public void fmlInitiation(FMLInitializationEvent fmlInitializationEvent)
    {
        if(Loader.isModLoaded("Waila"))
        {
            FMLInterModComms.sendMessage("Waila", "register", "imlookingatblood.pokefenn.WailaAltarPlugin.callbackRegister");
        }
    }

    @Mod.EventHandler
    public void fmlPostInitiation(FMLPostInitializationEvent fmlPostInitializationEvent)
    {

    }

    //public static void wailaRegistration(IWailaRegistrar registrar)
    {

    }

}
