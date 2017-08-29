package de.sloth.rpg.inventory.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.sloth.rpg.inventory.behavior.BAddItem;
import de.sloth.rpg.inventory.behavior.BRemoveItem;
import de.sloth.rpg.inventory.behavior.BUseItem;
import de.sloth.rpg.inventory.component.FullInventoryException;
import de.sloth.rpg.inventory.component.InventoryComp;
import de.sloth.rpg.inventory.component.UsableItemComp;
import de.sloth.rpg.inventory.datatype.InventorySlot;
import de.sloth.rpg.inventory.event.InventoryEvent;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultEntityManager;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

public class InventorySystemTest {

	private static GameCore core;
	private static IEntityManagement entityManager;
	private static List<GameEvent> eventQueue;
	private static Entity testEntityStackingInv;
	private static Entity testEntityNotStackingInv;
	private static Entity invItem;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		core = new GameCore();
		entityManager = new DefaultEntityManager();
		testEntityStackingInv = new Entity();
		testEntityNotStackingInv = new Entity();
		invItem = new Entity();
		InventoryComp invNotStackingComp = new InventoryComp(5, 1);
		InventoryComp invStackingComp = new InventoryComp(5, 2);
		UsableItemComp uiComp = new UsableItemComp("HealPot", "healing");
		testEntityStackingInv.addComponent(invStackingComp);
		testEntityNotStackingInv.addComponent(invNotStackingComp);
		invItem.addComponent(uiComp);
		entityManager.addEntity(testEntityStackingInv);
		entityManager.addEntity(testEntityNotStackingInv);
		eventQueue = new LinkedList<GameEvent>();
		GameSystem inventorySystem = new GameSystem("invSystem", InventoryEvent.class, entityManager, eventQueue);
		inventorySystem.registerBehavior("addItem", new BAddItem());
		inventorySystem.registerBehavior("delItem", new BRemoveItem());
		inventorySystem.registerBehavior("useItem", new BUseItem());
		core.registerSystem(inventorySystem);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {
		InventoryComp iComp = (InventoryComp) testEntityStackingInv.getComponent(InventoryComp.class);
		iComp.clearInventory();
		iComp = (InventoryComp) testEntityNotStackingInv.getComponent(InventoryComp.class);
		iComp.clearInventory();
		eventQueue.clear();
	}

	@Test
	public void addOneItemToInventoryWithoutStacking() throws Exception {
		InventoryEvent event = new InventoryEvent("addItem", testEntityNotStackingInv, invItem);
		eventQueue.add(event);
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityNotStackingInv.getComponent(InventoryComp.class);
		assertSame(invItem, iComp.getItem(0));
	}
	
	@Test
	public void addMultiplyItemsToInventoryWithoutStacking() throws Exception {
		InventoryEvent event = new InventoryEvent("addItem", testEntityNotStackingInv, invItem);
		InventoryEvent event2 = new InventoryEvent("addItem", testEntityNotStackingInv, invItem);
		eventQueue.add(event);
		eventQueue.add(event2);
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityNotStackingInv.getComponent(InventoryComp.class);
		assertSame(invItem, iComp.getItem(0));
		assertSame(invItem, iComp.getItem(1));
	}
	
	@Test(expected = FullInventoryException.class)
	public void fillInventoryWithoutStacking() throws Exception {
		for(int i = 0; i <= 5; i++) {
			InventoryEvent event = new InventoryEvent("addItem", testEntityNotStackingInv, invItem);
			eventQueue.add(event);
		}
		core.doGameLogic();
	}
	
	@Test()
	public void removeItemOfSingleAmount() throws Exception {
		InventoryEvent event = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		InventoryEvent event2 = new InventoryEvent("delItem", testEntityStackingInv, 0, 1);
		eventQueue.add(event);
		eventQueue.add(event2);
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityStackingInv.getComponent(InventoryComp.class);
		assertNull(iComp.getItem(0));
	}
	
	@Test()
	public void removeItemOfMultipleAmount() throws Exception {
		InventoryEvent event = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		InventoryEvent event2 = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		InventoryEvent event3 = new InventoryEvent("delItem", testEntityStackingInv, 0, 2);
		eventQueue.add(event);
		eventQueue.add(event2);
		eventQueue.add(event3);
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityStackingInv.getComponent(InventoryComp.class);
		assertNull(iComp.getItem(0));
	}
	
	@Test()
	public void addMultiplyItemToInventoryWithStacking() throws Exception {
		InventoryEvent event = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		InventoryEvent event2 = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		eventQueue.add(event);
		eventQueue.add(event2);
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityStackingInv.getComponent(InventoryComp.class);
		assertSame(invItem, iComp.getItem(0));
		assertEquals(2, iComp.getItemAmount(0));
	}
	
	@Test(expected = FullInventoryException.class)
	public void fillInventoryWithStacking() throws Exception {
		for(int i = 0; i <= 9; i++) {
			InventoryEvent event = new InventoryEvent("addItem", testEntityStackingInv, invItem);
			eventQueue.add(event);
		}
		core.doGameLogic();
		InventoryComp iComp = (InventoryComp) testEntityStackingInv.getComponent(InventoryComp.class);
		for(InventorySlot is : iComp.getInventory()) {
			assertNotNull(is);
		}
		//overfill inv
		InventoryEvent event = new InventoryEvent("addItem", testEntityStackingInv, invItem);
		eventQueue.add(event);
		core.doGameLogic();
	}
}