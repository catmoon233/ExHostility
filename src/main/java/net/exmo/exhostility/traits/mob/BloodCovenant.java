package net.exmo.exhostility.traits.mob;

import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import net.exmo.exhostility.Exhostility;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Comparator;
import java.util.List;

public class BloodCovenant extends MobTrait {

    public BloodCovenant(ChatFormatting format) {
        super(format);
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

    @Override
    public void onHurtByOthers(int level, LivingEntity entity, LivingHurtEvent event) {
        event.setAmount((float) Math.min(event.getAmount(), (0.11-level*0.02)*entity.getAttributeValue(Attributes.MAX_HEALTH)));
    }
}
