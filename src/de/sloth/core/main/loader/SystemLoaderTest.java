package de.sloth.core.main.loader;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultEntityManager;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

public class SystemLoaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		IEntityManagement iManager = new DefaultEntityManager();
		List<GameEvent> eventQueue = new LinkedList<GameEvent>();
		List<GameSystem> systems = SystemLoader.loadSystems(iManager, eventQueue);
		GameCore core = new GameCore();
		for(GameSystem sys : systems) {
			core.registerSystem(sys);
		}
		System.out.println(core);
	}

}
