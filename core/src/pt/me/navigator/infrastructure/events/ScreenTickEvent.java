package pt.me.navigator.infrastructure.events;

import pt.me.navigator.model.base.CameraModel;

import java.util.EventObject;

public class ScreenTickEvent extends EventObject {
	private static final String TAG = ScreenTickEvent.class.getSimpleName();
	
	private static final long serialVersionUID = 1L;

	private long elapsedNanoTime;
	private CameraModel camModel; 
	
	public ScreenTickEvent(Object source) {
		super(source);
	}

	/**/
	public long getElapsedNanoTime() {
		return elapsedNanoTime;
	}
	public void setElapsedNanoTime(long elapsedNanoTime) {
		this.elapsedNanoTime = elapsedNanoTime;
	}
	
	/**/
	public CameraModel getCamera() {
		return camModel;
	}
	public void setCamera(CameraModel camModel) {
		this.camModel = camModel;
	}
		
}
