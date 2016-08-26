package pt.me.navigator.model.ui;

import pt.me.navigator.infrastructure.events.GameTickEvent;
import pt.me.navigator.model.AbstractModel;
import pt.me.navigator.model.events.SimpleEvent;

public class UIModel extends AbstractModel {
	// Esta classe deverá ter um objecto visual independente da camera sobre o mundo.
	// O UI deverá permanecer inalterado independentemente do zoom/ pan, etc.
	private static final String TAG = UIModel.class.getSimpleName();
	
	

	public UIModel() {
		
		// Sinaliza os subscritores de que a construção do modelo terminou.
		this.dispatchEvent(new SimpleEvent(EventType.ON_MODEL_INSTANTIATED));		
	}
	
	
	
	@Override
	public void handleGameTick(GameTickEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
