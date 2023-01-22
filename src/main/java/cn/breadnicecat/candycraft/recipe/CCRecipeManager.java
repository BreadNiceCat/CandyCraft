package cn.breadnicecat.candycraft.recipe;

import cn.breadnicecat.candycraft.CandyCraft;
import cn.breadnicecat.candycraft.utils.Setter;
import cn.breadnicecat.candycraft.utils.UndimodifiableObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;


/**
 * @author <a href="https://gitee.com/Bread_NiceCat">Bread_NiceCat</a>
 * @date 2023/1/22 13:01
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCRecipeManager {
    @SuppressWarnings("rawtypes")
    private static final HashMap<ResourceLocation, Setter> TYPES = new HashMap<>();
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = CandyCraft.createRegister(ForgeRegistries.RECIPE_SERIALIZERS);
    /*
     * 正确注册配方的方法:
     * 1.新建配方类，继承这个包里合适的Recipe
     * 2.注册RecipeType
     * 3.写序列化类，并注册
     * 4.在data.recipe包里写配方Builder和FinishedRecipe
     * 注册结束
     * 在misc.CCRecipes写配方然后runData
     * */
    //
    public static final ResourceLocation caramel_portal_recipe_id = CandyCraft.prefix("caramel_portal");
    public static final UndimodifiableObject<CCRecipeType<Recipe<Container>>> caramel_portal_type = registerType(caramel_portal_recipe_id);
    public static final RegistryObject<CCRecipeSerializerBase<? extends Recipe<?>>> caramel_portal_recipe_serializer = registerSerializer(caramel_portal_recipe_id, CaramelPortalRecipe.Serializer::new);
    //
    public static final ResourceLocation sugar_factory_recipe_id = CandyCraft.prefix("sugar_factory");
    public static final UndimodifiableObject<CCRecipeType<Recipe<Container>>> sugar_factory_recipe_type = registerType(sugar_factory_recipe_id);
    public static final RegistryObject<CCRecipeSerializerBase<? extends Recipe<?>>> sugar_factory_recipe_serializer = registerSerializer(sugar_factory_recipe_id, SugarFactoryRecipe.Serializer::new);


    public static <T extends Recipe<?>> UndimodifiableObject<CCRecipeType<T>> registerType(ResourceLocation id) {
        UndimodifiableObject<CCRecipeType<T>> object = new UndimodifiableObject<>();
        TYPES.put(id, object);
        return object;
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegister(RegistryEvent.Register<RecipeSerializer<?>> event) {
        TYPES.forEach((id, obj) -> obj.set(Registry.register(Registry.RECIPE_TYPE, id, new CCRecipeType<>(id))));
    }

    public static <S extends CCRecipeSerializerBase<? extends Recipe<?>>> RegistryObject<S> registerSerializer(ResourceLocation id, Supplier<S> s) {
        return SERIALIZER_REGISTER.register(id.getPath(), s);
    }

    static {
        CandyCraft.clockIn();
    }

    public static void init() {
    }
}
