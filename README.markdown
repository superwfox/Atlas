# Atlas Plugin for Paper 1.21

 [让我们说中文！](https://github.com/superwfox/Atlas/blob/master/README_zh.markdown)

## Introduction
Atlas is a lightweight and efficient plugin designed for Paper 1.21 servers, providing seamless world hot-restore functionality. It allows administrators to save and load specific regions of a Minecraft world, with automatic backups and precise block management. Atlas is ideal for server administrators who need to preserve and restore specific areas of their world dynamically, such as for creative builds, event areas, or rollback purposes.

## Features
- **Automatic Backup**: Automatically saves all modified blocks in the main world to a backup world at 4 AM (Asia/Shanghai timezone) daily.
- **Manual Save and Load**: Operators can save or load a cubic region of blocks around their position using simple commands.
- **Efficient Block Tracking**: Tracks block placements and removals in real-time to ensure accurate restoration.
- **Void World Creation**: Creates a dedicated void world for storing backup data, ensuring minimal interference with the main world.
- **Optimized Performance**: Uses asynchronous tasks to handle block operations in batches, preventing server lag during large-scale restores.

## Installation
1. **Download the Plugin**:
   - Obtain the `Atlas.jar` file from the [releases page](#) (replace with your actual release link).
2. **Place in Plugins Folder**:
   - Copy `Atlas.jar` to the `plugins` folder of your Paper 1.21 server.
3. **Restart the Server**:
   - Start or restart your server to load the plugin. Atlas will automatically create a void world named `BEEF-Atlas` for backups.
4. **Verify Setup**:
   - Ensure the main world (`BEEF-DUNE`) exists. If not, update the `mainWorld` variable in `Atlas.java` to match your world's name.

## Usage
### Commands
The plugin provides a single command, `/atlas`, with two subcommands for operators:
- `/atlas save <range>`: Saves a cubic region of blocks around the player's position to the backup world (`BEEF-Atlas`). The `<range>` parameter defines the radius of the cube (e.g., `10` for a 21x21x21 block area).
- `/atlas load <range>`: Loads a cubic region of blocks from the backup world to the player's current position in the main world. The `<range>` parameter works similarly.

**Example**:
- `/atlas save 10`: Saves all blocks within a 21x21x21 area centered on the player.
- `/atlas load 10`: Restores the saved blocks to the same area in the main world.

### Permissions
- Only server operators (`op`) can use the `/atlas` command by default.
- No additional permissions are required, but you can extend this using a permissions plugin if desired.

### Automatic Backup
- The plugin automatically saves all modified blocks in the main world (`BEEF-DUNE`) to the backup world (`BEEF-Atlas`) daily at 4 AM (Asia/Shanghai).
- Only blocks tracked via `BlockPlaceEvent` and `BlockBreakEvent` are saved, ensuring efficient storage.

## Technical Details
### Architecture
- **Main Class (`Atlas.java`)**: Initializes the plugin, registers events, commands, and schedules the daily backup task.
- **Event Listener (`AtlasListener.java`)**: Tracks block placements and removals in the main world, maintaining a `HashSet` of modified block locations.
- **Block Management (`BlocksManager.java`)**: Handles saving and loading blocks between the main and backup worlds, with optimized batch processing for large areas.
- **Clock (`Clock.java`)**: Schedules the daily autosave using Bukkit's scheduler, triggered at 4 AM.
- **Command Handler (`CommandExecutor.java`)**: Processes the `/atlas` command for manual save and load operations.
- **World Creation (`CreateWorld.java`)**: Generates a void world for backups using a custom `VoidChunkGenerator`.

### Key Mechanisms
- **Block Tracking**: Uses a `HashSet<Location>` to store modified block locations, updated via `BlockPlaceEvent` and `BlockBreakEvent`.
- **Autosave**: Copies block states from the main world to the backup world at scheduled intervals.
- **Manual Save/Load**: Copies block states within a specified range, using `BlockState.copy()` to preserve block data (e.g., tile entities, block properties).
- **Performance Optimization**: The `load` function processes blocks in batches of 64 per tick to avoid server lag during large operations.

## Configuration
- **Main World**: Defined as `BEEF-DUNE` in `Atlas.java`. Update `mainWorld` to match your server's primary world name.
- **Backup World**: Defined as `BEEF-Atlas` in `Atlas.java`. This is a void world created automatically on plugin startup.
- **Autosave Schedule**: Set to 4 AM (Asia/Shanghai) in `Clock.java`. Modify the `ZonedDateTime` check to adjust the schedule or timezone.

## Limitations
- The plugin assumes the main world (`BEEF-DUNE`) exists. If the world name differs, update `Atlas.java` accordingly.
- Autosave only tracks blocks modified through `BlockPlaceEvent` and `BlockBreakEvent`. Other changes (e.g., explosions, piston movements) are not tracked.
- Large ranges in `/atlas save` or `/atlas load` may cause temporary server load. Use reasonable ranges (e.g., ≤50) for best performance.

## Future Improvements
- Add support for tracking additional block changes (e.g., explosions, redstone updates).
- Implement configurable autosave intervals and timezones.
- Add permissions for non-operator players to use specific commands.
- Support for multiple backup worlds or versioned backups.

## Contributing
Contributions are welcome! Please submit issues or pull requests to the [repository](#) (replace with your actual repository link). For major changes, open an issue first to discuss your ideas.

## License
This project is licensed under the MIT License. See the [LICENSE](#) file for details (replace with your actual license file).
