package net.exmo.exhostility.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.content.traits.base.AttributeTrait;
import dev.xkmc.l2hostility.init.entries.LHRegistrate;
import dev.xkmc.l2hostility.init.registrate.LHBlocks;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.exmo.exhostility.Config;
import net.exmo.exhostility.Exhostility;
import net.exmo.exhostility.traits.mob.AdaptArmor;
import net.exmo.exhostility.traits.mob.BloodCovenant;
import net.exmo.exhostility.traits.mob.SelfDestruct;
import net.exmo.exmodifier.init.ExAttribute;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class RegTrait {

    public static final RegistryEntry<AdaptArmor> ADAPT_ARMOR;
   public static final RegistryEntry<AttributeTrait> DODGE, VAMPIRISM;
   public static final RegistryEntry<SelfDestruct> SELF_DESTRUCT;
   public static final RegistryEntry<BloodCovenant> BLOOD_COVENANT;
//   public static final L2Registrate.RegistryInstance<MobTrait> TRAITS = REGISTRATE.newRegistry("trait", MobTrait.class, RegistryBuilder::hasTags);
//    public static final ProviderType<RegistrateTagsProvider.IntrinsicImpl<MobTrait>> TRAIT_TAGS =
//            ProviderType.register("tags/trait", type -> (p, e) ->
//                    new RegistrateTagsProvider.IntrinsicImpl<>(p, type, "traits",
//                            e.getGenerator().getPackOutput(),
//                            TRAITS.key(),
//                            e.getLookupProvider(),
//                            reg -> ResourceKey.create(TRAITS.key(), reg.getRegistryName()),
//                            e.getExistingFileHelper()));
    static {
        Exhostility.REGISTRATE.defaultCreativeTab(LHBlocks.TAB.getKey());

        // no desc
        {
            BLOOD_COVENANT = Exhostility.REGISTRATE.regTrait("blood_covenant", () -> new BloodCovenant(
                    ChatFormatting.RED
            ), rl -> new TraitConfig(rl, 15, 100, 5, 0)).lang("blood_covenant").register();
            SELF_DESTRUCT = Exhostility.REGISTRATE.regTrait("self_destruct", () -> new SelfDestruct(
                    ChatFormatting.RED
            ), rl -> new TraitConfig(rl, 15, 100, 3, 0)).lang("self_destructy").register();
            VAMPIRISM = Exhostility.REGISTRATE.regTrait("vampirism", () -> new AttributeTrait(
                    ChatFormatting.RED,
                    new AttributeTrait.AttributeEntry("vampirism value", ExAttribute.PERCENT_HEAL::get,
                            Config.VAMPIRISM_VALUE::get, AttributeModifier.Operation.MULTIPLY_TOTAL)
            ), rl -> new TraitConfig(rl, 10, 100, 5, 0)).lang("dodgey").register();
            DODGE = Exhostility.REGISTRATE.regTrait("dodge", () -> new AttributeTrait(
                    ChatFormatting.GRAY,
                    new AttributeTrait.AttributeEntry("dodge value", ExAttribute.DODGE::get,
                            Config.DODGE_CHANCE::get, AttributeModifier.Operation.MULTIPLY_TOTAL)
            ), rl -> new TraitConfig(rl, 10, 100, 4, 0)).lang("dodgey").register();
            ADAPT_ARMOR = Exhostility.REGISTRATE.regTrait("adaptarmor", () -> new AdaptArmor(
                    ChatFormatting.GREEN
            ), rl -> new TraitConfig(rl, 20, 100, 5, 0)).lang("adaptarmory").register();

        }
    }
    public static void register() {


    }
    public static void onConfigGen(ConfigDataProvider.Collector collector) {
        ((LHRegistrate) Exhostility.REGISTRATE).CONFIGS.forEach(e -> e.accept(collector));
    }
}