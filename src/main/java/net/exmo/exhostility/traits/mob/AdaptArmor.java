package net.exmo.exhostility.traits.mob;

import dev.xkmc.l2hostility.content.capability.mob.CapStorageData;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.AttributeTrait;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import net.exmo.exhostility.Exhostility;
import net.exmo.exhostility.init.RegTrait;
import net.exmo.exmodifier.util.EntityAttrUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AdaptArmor extends MobTrait {

    public AdaptArmor(ChatFormatting format) {
        super(format);
    }
  private static final UUID armorRemove = UUID.fromString("e8d8a7e6-f8f7-4f09-b2b8-b8b8b8b8b8b8");

    @Override
    public void postInit(LivingEntity le, int level) {
        super.postInit(le, level);
       // Exhostility.LOGGER.debug("Adapt");
        if (le.getAttribute(Attributes.ARMOR)!=null) {
            EntityAttrUtil.entityAddAttrTF(Attributes.ARMOR,new AttributeModifier(armorRemove,"aparemove",le.getAttributeValue(Attributes.ARMOR)*-1, AttributeModifier.Operation.ADDITION),le, EntityAttrUtil.WearOrTake.WEAR);
        }
    }


    @Override
    public void onHurtByOthers(int level,  LivingEntity entity,  LivingHurtEvent event) {
        LivingEntity entity1 = event.getEntity();

        if (entity1.getAttributes().getInstance(Attributes.ARMOR)!=null) {
            if (entity1.getAttributeBaseValue(Attributes.ARMOR) < 500) {
              //  Exhostility.LOGGER.debug("AdaptArmor");
                entity1.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(Math.min(entity1.getAttributeBaseValue(Attributes.ARMOR) +-1+2*level,90*(1+2*level)));
            }
        }
    }
    @Override
    public void addDetail(List<Component> list) {
        list.add(Component.translatable(getDescriptionId() + ".desc",
                        Component.literal((int) Math.round(100 * (1 - LHConfig.COMMON.adaptFactor.get())) + "")
                                .withStyle(ChatFormatting.AQUA),
                        mapLevel(i -> Component.literal("" + i)
                                .withStyle(ChatFormatting.AQUA)))
                .withStyle(ChatFormatting.GRAY));
    }

}
