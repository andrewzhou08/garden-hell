Garden Hell
by Andrew Zhou, Heidi Dong, Leo Yao
9 May 2016
-----------------------------------------------------------
Description
This game takes a special twist from the generic bullet hell game. Instead of fighting computers, you take on your friends and challenge them in a 1v1 duel. After a minute, the player will encounter challenges such as moving walls that will now damage the player, as well as true bullet hell, where turrets will spawn on top of each wall and continuously shoot at the players for a certain amount of time. After this corruption ends, turrets remain in the arena, but corruption and movement of barriers end.
Each player is required to shoot the other player, as well as survive the bullet hell. The last surviving player wins the match.

-----------------------------------------------------------
Instructions
Each player can choose a character to play, and it will show which character each player chooses.
Player 1 uses WASD to control the character, [g] to shoot, [h] to use special ability.
Player 2 uses Arrow Keys to control the character, [n](number pad) to shoot, [m](number pad) to use special ability.
Press “GO” when both players have finished selecting their characters.
Game will continue until one player loses all their health.

-----------------------------------------------------------
Features List
Must have
-Corruption with turrets all 4 types
-3 characters (Tank, Damager, Builder)
-Character selection screen
-1 map

Want to have
-Main splash screen
-Replay and reset system
-Play against computer AI
-Special skills per class
-Map selection screen

Stretch goals
-Co-op/Singleplayer mode
-Networking
-3 maps
-5 characters
-Powerups/Items
-Multiple AI difficulities

-----------------------------------------------------------
Class List
     Must Have
public class Main;
public abstract class Player implements Drawable;
public interface Drawable;
public class Tank extends Player;
public class Damager extends Player;
public class Builder extends Player;
public class Barrier implements Drawable;
public class BreakableBarrier extends Barrier;
public class CorruptedBarrier extends Barrier;
public class GravityBarrier extends Barrier;
public interface Turret;
public class StandardTurret implements Turret;
public class FlowerTurret implements Turret;
public class LaserTurret implements Turret;
public class PowerOrbTurret implements Turret;
public class CharacterSelectionScreen;
public class Projectile implements Drawable;
public class Bullet extends Projectile
public class Map;
public class Map1 extends Map;

     Want-To-Have
public class ComputerPlayer extends Player;
public class MainScreen;
public class MapSelectionScreen;

     Stretch Goals
public class FastInvisibleDude extends Player;
public class Sniper extends Player;
public class Map2 extends Map;
public class Map3 extends Map;
public class LevelLoader
public class Levels;
public class [Level Name] extends Levels;
public class FileIO;
public class Powerup implements Drawable;
public class [Powerup Name] extend Powerup;
*all the network stuff*
-----------------------------------------------------------
Responsibility List
     Andrew Zhou
-Artwork
-Everything that extends Barrier
     Heidi Dong
-Artwork
-Everything that implements Turret
-CharacterSelectionScreen
-UML
     Leo Yao
-Everything that extends Characters
-Drawable Interface
-Main class
-----------------------------------------------------------