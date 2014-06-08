package imlookingatblood.pokefenn;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */

@Mod(name = "I'm Looking At Blood", modid = "ImLookingAtBlood", version = "1.1", dependencies = "required-after:Waila;")
public class ImLookingAtBlood
{
    public static boolean doNeedDiviniation;

    @Mod.EventHandler
    public void fmlPreInitiation(FMLPreInitializationEvent fmlPreInitializationEvent)
    {
        Configuration configuration;
        File configFile = new File(fmlPreInitializationEvent.getModConfigurationDirectory(), "imlookingatblood.cfg");

        configuration = new Configuration(configFile);

        try
        {
            configuration.load();
            doNeedDiviniation = configuration.get("general", "doesNeedDiviniationSigilInHandForAltar", true).getBoolean(true);
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally
        {
            configuration.save();
        }
    }

    @Mod.EventHandler
    public void fmlInitiation(FMLInitializationEvent fmlInitializationEvent)
    {
        FMLInterModComms.sendMessage("Waila", "register", "imlookingatblood.pokefenn.WailaBMPlugin.registerWaila");
        FMLInterModComms.sendMessage("Waila", "register", "imlookingatblood.pokefenn.ChemistrySet.registerWaila");
        FMLInterModComms.sendMessage("Waila", "register", "imlookingatblood.pokefenn.RitualStone.registerWaila");
        FMLInterModComms.sendMessage("Waila", "register", "imlookingatblood.pokefenn.Teleposer.registerWaila");
    }

    @Mod.EventHandler
    public void fmlPostInitiation(FMLPostInitializationEvent fmlPostInitializationEvent)
    {

    }
}
