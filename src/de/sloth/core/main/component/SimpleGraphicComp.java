package de.sloth.core.main.component;

/**
 * Data for a 3 dimensional position
 * [SlothCore]
 * @author Slothking
 *
 */
public class SimpleGraphicComp extends Component {
	
	private String simpleIdent;
	
	public SimpleGraphicComp(String ident) {
		this.simpleIdent = ident;
	}

	public String getSimpleIdent() {
		return simpleIdent;
	}

	public void setSimpleIdent(String simpleIdent) {
		this.simpleIdent = simpleIdent;
	}
}