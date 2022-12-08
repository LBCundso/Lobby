# Lobby-Plugin

Hi! Das hier ist mein Lobby-Plugin, welches ich im Rahmen meiner (recht inaktiven) Serie auf TikTok, in welcher ich versucht habe, eine eigenes Minecraftplugin zu erstellen. (https://tiktok.com/@LBCundso)


# Infos
- Spigot-Plugin für die 1.19.2
- Benötigt wird ein Rechteverwaltungsplugin wie LuckPerms.

- Wenn man im Kreativmodus ist und die Permission "lobby.build" hat, kann die Welt, in der die Lobby gesetzt ist, bearbeitet werden.
- Join/Quit-Nachricht, die nur in der Lobby zu sehen ist.
- Navigator auf Slot 5 beim Joinen.
- Kein Playerschaden und -hunger in der Lobby.
- Keine Wetteränderung und Zeit permanent auf 1000.

## Commands ohne Permissions

/spawn - Teleportiere dich zum Spawn.

## Commands mit Permissions

**Command - Permission - Erklärung**

/holo [delete] - lobby.holo - Erstelle ein Hologram.

/maptp [MAPNAME] - lobby.maptp - Teleportiere dich in eine geladene Welt.

/navedit [add/remove] [ZAHL] [NAME] - lobby.editnav - Erstelle Teleportationspunkte, die über den Kompass ansteuerbar sind.

/setspawn - lobby.setspawn - Setze den Spawn.

/villager [NAME] - lobby.villager - Erstelle einen Villager ohne AI.
