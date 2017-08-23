package de.sloth.core.main;

import de.sloth.core.main.ConfigLoader;
import de.sloth.core.main.component.AnimationComp;
import de.sloth.core.main.component.BlockingComp;
import de.sloth.core.main.component.FocusComp;
import de.sloth.core.main.component.HealthComp;
import de.sloth.core.main.component.HitboxComp;
import de.sloth.core.main.component.MovableComp;
import de.sloth.core.main.component.Position3DComp;
import de.sloth.core.main.component.ScoreComp;
import de.sloth.core.main.component.SpriteComp;
import de.sloth.core.movement.event.Direction;
import de.sloth.core.neuralNetwork.component.NeuralNetworkComp;
import de.sloth.core.neuralNetwork.component.datatype.NeuralNetwork;
import de.sloth.core.spears.component.FlyingComp;
import de.sloth.core.spears.component.SpearBagComp;

/**
 * Generator for specific game entities
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 17.05.2017
 *
 */
public class EntityGenerator {
	private static EntityGenerator instance;
	private static final int CANVAS_WIDTH = 640;
	private static final int CANVAS_HEIGTH = 480;
	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 32;
	private static final double SCALING = 2.;
	
	private EntityGenerator() {}
	
	public static EntityGenerator getInstance() {
		if(instance == null) {
			instance = new EntityGenerator();
		}
		return instance;
	}
	
	/**
	 * Generate enemy in specific direction (left, middle, right)
	 * @param direction Direction of spawn
	 * @return Entity with components needed for an enemy
	 */
	public Entity generateEnemy(Direction direction) {
		Entity enemy = new Entity();
		enemy.setId(-1);
		enemy.setName("Enemy Viking");
		Position3DComp posComp = new Position3DComp();
		int enemySpeed = Integer.parseInt(ConfigLoader.getInstance().getConfig("enemySpeed", "16"));
		MovableComp mComp = new MovableComp(enemySpeed, Direction.RIGHT);
		HitboxComp hitcomp = new HitboxComp(32, 32);
		if(direction.equals(Direction.LEFT)) {
			posComp.setX((int) (SPRITE_WIDTH*SCALING));
		} else if(direction.equals(Direction.RIGHT)) {
			posComp.setX(CANVAS_WIDTH-((int) (SPRITE_WIDTH*SCALING)));
		} else {
			posComp.setX(CANVAS_WIDTH/2);
		}
		posComp.setY((int) (SPRITE_HEIGHT*SCALING));
		SpriteComp s2Comp = new SpriteComp("Viking_left.png");
		enemy.addComponent(posComp);
		enemy.addComponent(s2Comp);
		enemy.addComponent(new VikingEnemyComp());
		enemy.addComponent(mComp);
		enemy.addComponent(hitcomp);
		return enemy;
	}
	
	public Entity generateVikingShip() {
		Entity ship = new Entity();
		Position3DComp posComp = new Position3DComp();
		posComp.setX(0);
		posComp.setY(CANVAS_HEIGTH-1);
		posComp.setZ(0);
		ship.addComponent(posComp);
		SpriteComp sComp = new SpriteComp("Viking_Ship_smaller.png");
		ship.addComponent(sComp);
		return ship;
	}
	
	/**
	 * Generate a player entity
	 * @return Entity with specific components
	 */
	public Entity generatePlayer() {
		int playerLife = Integer.parseInt(ConfigLoader.getInstance().getConfig("playerLife", "5"));
		int playerSpears = Integer.parseInt(ConfigLoader.getInstance().getConfig("playerSpears", "0"));
		int playerSpeed = Integer.parseInt(ConfigLoader.getInstance().getConfig("playerSpeed", "8"));
		Entity playerEntity = new Entity();
		playerEntity.setId(-1);
		Position3DComp posComp = new Position3DComp();
		posComp.setX(300);
		MovableComp mComp = new MovableComp(playerSpeed, Direction.LEFT);
		HitboxComp hbox = new HitboxComp(32, 32);
		HealthComp heComp = new HealthComp(playerLife);
		SpearBagComp spComp = new SpearBagComp(playerSpears);
		ScoreComp scoreComp = new ScoreComp(0);
		AnimationComp aniComp = new AnimationComp("idle", 0, 15);
		posComp.setY(CANVAS_HEIGTH - (int) (SPRITE_HEIGHT*SCALING));
		FocusComp fComp = new FocusComp();
		SpriteComp sComp = new SpriteComp("Viking_sheet");
		playerEntity.addComponent(posComp);
		playerEntity.addComponent(fComp);
		playerEntity.addComponent(sComp);
		playerEntity.addComponent(hbox);
		playerEntity.addComponent(mComp);
		playerEntity.addComponent(heComp);
		playerEntity.addComponent(spComp);
		playerEntity.addComponent(scoreComp);
		playerEntity.addComponent(aniComp);
		return playerEntity;
	}
	
	public Entity generateBlockade(int xPos, int yPos, int width, int height) {
		Entity blockade = new Entity();
		Position3DComp posComp = new Position3DComp();
		HitboxComp hitComp = new HitboxComp(width, height);
		BlockingComp blockComp = new BlockingComp();
		posComp.setX(xPos);
		posComp.setY(yPos);
		blockade.addComponent(posComp);
		blockade.addComponent(hitComp);
		blockade.addComponent(blockComp);
		return blockade;
	}
	
	/**
	 * Generate a entity with spear components
	 * @param thrower Src of spear spawn
	 * @return Entity with spear components
	 */
	public Entity generateFlyingSpear(Entity thrower) {
		int spearSpeed = Integer.parseInt(ConfigLoader.getInstance().getConfig("spearSpeed", "4"));
		Entity spear = new Entity();
		spear.setId(-1);
		Position3DComp throwPosComp = (Position3DComp) thrower.getComponent(Position3DComp.class);
		Position3DComp spearPosComp = new Position3DComp();
		HitboxComp hitbox = new HitboxComp(14,32);
		spearPosComp.setX(throwPosComp.getX()+10);
		//AnimationComp aniComp = new AnimationComp("idle", 0, 20);
		Direction direct = Direction.TOP;
		SpriteComp spComp = new SpriteComp("spear.png");
		if(thrower.getComponent(FocusComp.class) == null) {
			spearPosComp.setY(throwPosComp.getY() + (int) (SPRITE_HEIGHT*SCALING));
		} else {
			spearPosComp.setY(throwPosComp.getY() - (int) (SPRITE_HEIGHT*SCALING));
			direct = Direction.BOTTOM;
			spComp = new SpriteComp("spear_backward.png");
		}
		FlyingComp flyComp = new FlyingComp(500, direct);
		MovableComp movComp = new MovableComp(spearSpeed, Direction.TOP);
		spear.addComponent(flyComp);
		spear.addComponent(movComp);
		spear.addComponent(spearPosComp);
		spear.addComponent(spComp);
		spear.addComponent(hitbox);
		return spear; 	
	}
	
	/**
	 * Generate neural network entity
	 * @return Entity with neutal network entities
	 */
	public Entity generateNNEntity() {
		Entity nnEntity = new Entity();
		nnEntity.setId(-1);
		int population = Integer.parseInt(ConfigLoader.getInstance().getConfig("nnMaxPop", "8")); //get from prop in future
		int generations = Integer.parseInt(ConfigLoader.getInstance().getConfig("nnGenerations", "5"));
		int sizeOfElite = Integer.parseInt(ConfigLoader.getInstance().getConfig("nnSizeOfElite", "6"));
		NeuralNetworkComp nnComp = new NeuralNetworkComp(generations, new NeuralNetwork(), population, sizeOfElite);
		nnEntity.addComponent(nnComp);
		return nnEntity;
	}
}