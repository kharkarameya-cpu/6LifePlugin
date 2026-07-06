# Third Life SMP Plugin

A Bukkit/Spigot plugin that implements a Third Life-style game mode for Minecraft survival multiplayer servers. Players have limited lives and must manage their resources strategically.

## Features

### Life System
- **Three Life Stages**: GREEN, YELLOW, RED
  - Players start at GREEN with configurable default lives (default: 6)
  - Each time a player dies, they lose a life and change color
  - When they reach RED and die again, they are eliminated from the server
  
### Custom Items
The plugin includes 20+ custom craftable items divided into categories:

#### Basic Items (All Players)
- **Revive Token**: Cyan totem that can revive eliminated players
- **Blood Lust Sword**: Red netherite sword (only RED players can craft)
- **Life Orb**: Green emerald that grants regeneration

#### Green Items (Only GREEN players can craft)
- **Backpack**: Adds 9 inventory slots
- **Lumber Axe**: Cuts entire trees at once
- **Farmer's Charm**: Crops grow faster and auto-replant
- **Wind Boots**: Reduces fall damage with speed boost
- **Emergency Totem**: Once per hour, prevents death

#### Yellow Items (Only YELLOW players can craft)
- **Tracking Compass**: Tracks nearest GREEN within 500 blocks
- **Smoke Bomb**: Throws for blindness and darkness effect
- **Snare Trap**: Hidden trap applying slowness and weakness
- **Hunter Cloak**: Makes mobs ignore you
- **Scout Bow**: Arrows apply glowing and weakness

#### Red Items (Only RED players can craft)
- **Blood Sword**: Heals 3 hearts on player kill
- **Frenzy Pendant**: Grants Speed I below 30% HP
- **Last Stand Shield**: Grants Resistance I when blocking below 20% HP
- **Chaos Pearl**: Teleports and blinds nearby players

#### Legendary Items (All players can craft)
- **Soul Crystal**: Revives an eliminated player (expensive to craft)
- **Beacon Compass**: Tracks world spawn
- **Recall Scroll**: Teleports home after 10 seconds

### Color System
Messages support multiple color formats:
- **Hex Colors**: `<#FF0000>` for red, `<#00FF00>` for green, `<#0000FF>` for blue
- **RGB Colors**: `<rgb(255,0,0)>` for red, `<rgb(0,255,0)>` for green
- **Named Colors**: `<red>`, `<green>`, `<blue>`, `<gold>`, `<gray>`, etc.

### Player Color Display
Players' life status colors are automatically displayed in:
- **Tab List (Player List)**: Shows `[STATUS]` prefix with color indicator next to player name
  - `[GREEN]` in green for green-status players
  - `[YELLOW]` in yellow for yellow-status players
  - `[RED]` in red for red-status players
- **Chat Messages**: Shows the same color prefix before each player's message in chat

## Configuration

### config.yml

#### Storage Settings
```yaml
storage:
  type: yaml  # or mysql
  mysql:
    host: localhost
    port: 3306
    database: minecraft
    username: root
    password: ''
    table: xlifesmp_players
```

#### Game Settings
```yaml
default-lives: 6  # Starting lives for new players
```

#### Item Enable/Disable
```yaml
items:
  revive-token: true
  blood-lust-sword: true
  life-orb: true
  
  green:
    backpack: true
    lumber-axe: true
    farmer-charm: true
    wind-boots: true
    emergency-totem: true
  
  yellow:
    tracking-compass: true
    smoke-bomb: true
    snare-trap: true
    hunter-cloak: true
    scout-bow: true
  
  red:
    blood-sword: true
    frenzy-pendant: true
    last-stand-shield: true
    chaos-pearl: true
  
  legendary:
    soul-crystal: true
    beacon-compass: true
    recall-scroll: true
```

### messages.yml

All messages support hex, RGB, and named colors:

```yaml
# Hex color example
eliminated: "<#FF0000>%player% has been eliminated!</>"

# RGB color example
lost-life: "<rgb(255,0,0)>You lost a life! <gray>Lives remaining: <rgb(255,255,0)>%lives%</>"

# Named color example
gained-life: "<green>You gained a life! <gray>Lives: <yellow>%lives%</>"

status:
  green: "<#00FF00>You are now GREEN.</>"
  yellow: "<#FFFF00>You are now YELLOW.</>"
  red: "<#FF0000>You are now RED.</>"

join: "<gray>You currently have <#FFFF00>%lives% <gray>lives.</>"
```

## Commands

### Player Commands
- `/life` - Check your current life count and status
- `/setlives <player> <amount>` - Set a player's lives (admin only)
- `/revive <player>` - Revive an eliminated player (admin only)

## Storage Backends

### YAML Storage (Default)
- Stores player data in YAML files
- No external dependencies
- Good for small to medium servers

### MySQL Storage
- Stores player data in a MySQL database
- Better for large servers
- Requires MySQL connection details in config.yml

## Installation

1. Build the plugin using Maven: `mvn clean package`
2. Copy the JAR file from `target/xlifesmp-1.0-SNAPSHOT-shaded.jar` to your server's `plugins` folder
3. Restart your server
4. Edit `plugins/xLifeSMP/config.yml` and `plugins/xLifeSMP/messages.yml` to customize settings
5. Restart the server again for changes to take effect

## Dependencies

- Bukkit/Spigot API
- Kyori Adventure (for text components and colors)
- Java 11+

## Building from Source

```bash
mvn clean package
```

The compiled JAR will be in the `target` folder.

## File Structure

```
src/
├── main/
│   ├── java/org/succeptivitydev/xlifesmp/
│   │   ├── ThirdLifePlugin.java           # Main plugin class
│   │   ├── commands/
│   │   │   └── LifeCommand.java           # Life commands
│   │   ├── CustomStuff/
│   │   │   ├── RecipeManager.java         # Custom recipe registration
│   │   │   └── Items/
│   │   │       └── Items.java             # Custom item definitions
│   │   ├── Listeners/
│   │   │   ├── MainListener.java          # Main game events
│   │   │   ├── LoginListener.java         # Player join events
│   │   │   └── CraftingListener.java      # Crafting restrictions
│   │   ├── manager/
│   │   │   ├── LifeManager.java           # Game logic
│   │   │   ├── Storage.java               # Storage interface
│   │   │   ├── YamlStorage.java           # YAML implementation
│   │   │   └── MySQLStorage.java          # MySQL implementation
│   │   ├── model/
│   │   │   ├── LifeState.java             # Enum (GREEN, YELLOW, RED)
│   │   │   └── PlayerData.java            # Player data model
│   │   └── util/
│   │       └── ColorParser.java           # Color parsing utility
│   └── resources/
│       ├── config.yml                     # Configuration file
│       ├── messages.yml                   # Message strings
│       └── plugin.yml                     # Plugin metadata
```

## Customization

### Adding Custom Items
1. Add the item creation method in `Items.java`
2. Add the recipe in `RecipeManager.java`
3. Update the crafting restrictions in `CraftingListener.java` if needed
4. Add the enable/disable setting in `config.yml`

### Changing Colors
- Edit `messages.yml` to use different hex, RGB, or named colors
- Use the format: `<#RRGGBB>text</>` for hex or `<rgb(r,g,b)>text</>` for RGB

### Adding Custom Messages
1. Add the message key to `messages.yml`
2. Use the `plugin.getMessage(path, replacements...)` method in your code

## Troubleshooting

### Items not appearing
- Check that the item is enabled in `config.yml`
- Verify recipes are registered (check server logs)

### Colors not working
- Ensure you're using Paper/Spigot 1.16+ (for full color support)
- Check that messages.yml has valid hex or RGB format

### Player data not saving
- Check storage type in `config.yml`
- For MySQL, verify database connection details
- Check server logs for SQL errors

## License

This project is provided as-is for use on Minecraft servers.

## Support

For issues, questions, or suggestions, please check the server logs for error messages.

