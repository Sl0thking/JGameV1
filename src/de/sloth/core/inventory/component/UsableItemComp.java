package de.sloth.core.inventory.component;

import de.sloth.core.main.component.Component;

public class UsableItemComp extends Component {
	
	private String name;
	private String useType;

	public UsableItemComp(String name, String uType) {
		super();
		this.name = name;
		this.useType = uType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((useType == null) ? 0 : useType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsableItemComp other = (UsableItemComp) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (useType == null) {
			if (other.useType != null)
				return false;
		} else if (!useType.equals(other.useType))
			return false;
		return true;
	}
}