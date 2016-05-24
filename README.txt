Garden Hell
by Andrew Zhou, Heidi Dong, Leo Yao
9 May 2016
-----------------------------------------------------------
Description
This game takes a special twist from the generic bullet hell game. Instead of fighting computers, you take on your friends and challenge them in a 1v1 duel. After a minute, the player will encounter challenges such as moving walls that will now damage the player, as well as true bullet hell, where turrets will spawn on top of each wall and continuously shoot at the players for a certain amount of time. After this corruption ends, the barriers go back to normal.
There are three classes: Tank, Damager, and Builder.
The tank class has a large amount of health points. It fires a large bullet as its special, and has a forcefield as its ultimate.
The damager doesn't have as much health, but does a lot more damage. The damager's special is an eight way shot, and its ultimate is a frozen shot where the opponent is frozen for five seconds.
The builder has average statistics. However, the builder's special is that it can build a breakable barrier. Additionally, the builder's ultimate traps the enemy in nine barriers.
Each player is required to shoot the other player, as well as survive the bullet hell. The last surviving player wins the match.

-----------------------------------------------------------
Instructions
Each player can choose a character to play, and it will show which character each player chooses.
Player 1 uses WASD to control the character, [SHIFT] to shoot. [F] is pressed to activate the player's special ability, and [C] is used to activate the player's ultimate.
Player 2 uses Arrow Keys to control the character, [SPACE] to shoot. [ALT] to use special, [COMMA] to use ultimate.
Click anywhere when both players have finished selecting their characters.
Game will continue until one player loses all their lives.

-----------------------------------------------------------
Features List
Must have
[x] Corruption with turrets - 3 types
[x] 3 characters (Tank, Damager, Builder)
[x] Character selection screen
[x] 1 map
[x] Well done animations for each object

Want to have
[x] Special skills per class
[x] Ultimate skills per class
[x] Main splash screen
[x] Replay and reset system
[ ] Play against computer AI
[ ] Map selection screen

Stretch goals
[ ] Co-op/Singleplayer mode
[ ] Networking
[ ] 3 maps
[ ] 5 characters
[ ] Powerups/Items
[ ] Multiple AI difficulties

-----------------------------------------------------------
Class List
     Must Have
public class Main extends JPanel;
public class CharacterSelectionScreen extends JPanel implements MouseListener;
public class GamePanel extends JPanel implements MouseListener,KeyListener;
public interface Drawable;
public abstract class Actor implements Drawable;
public class HitBox;
public class Animation;
public class Barrier extends Actor;
public class BreakableBarrier extends Barrier;
public class CorruptedBarrier extends Barrier;
public class CorruptableBarrier extends Barrier;
public class Player extends Actor;
public class Tank extends Player;
public class Damager extends Player;
public class Builder extends Player;
public class Projectile extends Actor;
public class StandardBullet extends Projectile;
public class FlowerBullet extends Projectile;
public class PowerOrbBullet extends Projectile;
public class TankBullet extends Projectile;
public class DamagerBullet extends Projectile;
public class BuilderBullet extends Projectile;
public class Turret extends Actor implements Drawable;
public class StandardTurret extends Actor implements Turret;
public class FlowerTurret extends Actor implements Turret;
public class PowerOrbTurret extends Actor implements Turret;
public class Map;

     Want-To-Have
public class MainScreen extends JPanel implements MouseListener;
public class TankBulletSpecial extends Projectile;
public class DamagerFreezeBullet extends Projectile;
public class TankForcefield extends Projectile;
public class EasySound implements Runnable;

     Stretch Goals
public class ComputerPlayer extends Player;
public class Levels;
public class [Level Name] extends Levels;
public class FileIO;
public class Server;
public class Map2 extends Map;
public class Map3 extends Map;
public class LevelLoader;
public class FastInvisibleDude extends Player;
public class Sniper extends Player;
public class Powerup implements Drawable;
public class [Powerup Name] extend Powerup;
public class MapSelectionScreen;
-----------------------------------------------------------
Responsibility List
     Andrew Zhou
- Artwork
- README
- Turrets
- Player ultimate and special attacks
- Character selection screen
- Map
     Heidi Dong
- Animation
- Barrier functionality and corruption
- Bullets and projectile
- Game loop
- UML
- Actor
     Leo Yao
- Main class
- Key input and player movement
- Collision
- Javadocs
-----------------------------------------------------------