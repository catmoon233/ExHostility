package net.exmo.exhostility.traits.mob;

import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import net.exmo.exhostility.Exhostility;
import net.exmo.exmodifier.util.EntityAttrUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class SelfDestruct extends MobTrait {

    public SelfDestruct(ChatFormatting format) {
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
    public void onDeath(int level, LivingEntity entity, LivingDeathEvent event) {
        super.onDeath(level, entity, event);
            MobTraitCap traitCap = entity.getCapability(MobTraitCap.CAPABILITY).orElse(null);
            if (entity.level() instanceof ServerLevel serverLevel) {
                Vec3 v = new Vec3(entity.getX(), entity.getY(), entity.getZ());
                {
                    Exhostility.queueServerWork(20, () -> {

                        final Vec3 _center = v;
                    List<Entity> _entfound = serverLevel.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), a -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
                    for (Entity entityiterator : _entfound) {
                        if (entityiterator instanceof Player) {
                            entityiterator.hurt(new DamageSource(serverLevel.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.EXPLOSION)), level * 0.1f * traitCap.lv);
                        }
                    }
                    serverLevel.sendParticles(ParticleTypes.EXPLOSION, v.x, v.y+1, v.z, 15, 1.5, 1.5, 1.5, 0.75);
                    });


                }

            }
    }
}
