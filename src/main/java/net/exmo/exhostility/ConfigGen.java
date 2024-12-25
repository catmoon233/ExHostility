package net.exmo.exhostility;

import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.exmo.exhostility.init.RegTrait;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class ConfigGen extends ConfigDataProvider {
    public ConfigGen(DataGenerator generator) {
        super(generator, "ExHostility Config");
    }

    @Override
    public void add(Collector collector) {
            RegTrait.onConfigGen(collector);

//        collector.add(Exhostility.EXAMPLE_TAB, new ResourceLocation(Exhostility.MODID, "pandora"),
//                new AttributeDisplayConfig()
//                        .add(EX, 20000)
//                        .add(CoPAttrs.REALITY.get(), 21000)
//                        .add(CoPAttrs.ABSORB.get(), 23000));
//    }
    }
}