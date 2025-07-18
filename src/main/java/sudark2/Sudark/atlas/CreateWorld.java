package sudark2.Sudark.atlas;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CreateWorld {
    public static World createVoidWorld(String worldName) {
        // 创建世界配置
        WorldCreator creator = new WorldCreator(worldName);

        // 设置世界类型为FLAT（我们会用自定义生成器覆盖）
        creator.environment(World.Environment.NORMAL)
                .type(WorldType.FLAT)
                .generateStructures(false) // 禁用结构生成
                .generator(new VoidChunkGenerator()); // 使用自定义虚空生成器

        // 创建并返回世界
        return creator.createWorld();
    }

    // 自定义虚空生成器
    private static class VoidChunkGenerator extends ChunkGenerator {
        @Override
        public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            return createChunkData(world);
        }
    }
}
