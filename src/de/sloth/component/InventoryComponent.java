package de.sloth.component;

import de.sloth.entity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryComponent extends Component {
	
	ObservableList<Entity> inventory;
	
	public InventoryComponent() {
		inventory = FXCollections.observableArrayList();
	}
	
	public ObservableList<Entity> getInventoryList() {
		return inventory;
	}
	
}
