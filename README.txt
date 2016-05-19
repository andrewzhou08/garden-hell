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
Player 1 uses WASD to control the character, [space] to shoot.
Player 2 uses Arrow Keys to control the character, [shift] to shoot.
Press “GO” when both players have finished selecting their characters.
Game will continue until one player loses all their health.

-----------------------------------------------------------
Features List
Must have
-Corruption with turrets - 3 types
✓3 characters (Tank, Damager, Builder)
✓Character selection screen
✓1 map
✓Well done animations for each object

Want to have
-Main splash screen
✓Replay and reset system
-Play against computer AI
-Special skills per class
-Map selection screen

Stretch goals
-Co-op/Singleplayer mode
-Networking
-3 maps
-5 characters
-Powerups/Items
-Multiple AI difficulties

-----------------------------------------------------------
Class List
     Must Have
public class Main;
public class CharacterSelectionScreen;
public class GamePanel;
public interface Drawable;
public abstract class Actor implements Drawable;
public class Animation;
public class Barrier extends Actor;
public class BreakableBarrier extends Barrier;
public class CorruptBarrier extends Barrier;
public class GravityBarrier extends Barrier;
public class Player extends Actor;
public class Tank extends Player;
public class Damager extends Player;
public class Builder extends Player;
public class Projectile extends Actor;
public class StandardBullet extends Projectile;
public class FlowerBullet extends Projectile;
public class PowerOrbBullet extends Projectile;
public class LaserBullet extends Projectile;
public interface Turret;
public class StandardTurret extends Actor implements Turret;
public class FlowerTurret extends Actor implements Turret;
public class PowerOrbTurret extends Actor implements Turret;
public class LaserTurret extends Actor implements Turret;
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
-Everything that extends Bullet
-README
     Heidi Dong
-Everything that implements Turret
-CharacterSelectionScreen
-GamePanel
-UML
     Leo Yao
-Everything that extends Characters
-Drawable Interface
-Main class
-----------------------------------------------------------