package pt.me.navigator;

import pt.me.navigator.controller.MyGestureListener;
import pt.me.navigator.controller.MyInputProcessor;
import pt.me.navigator.infrastructure.GAME_CONSTANTS;
import pt.me.navigator.infrastructure.GameTickGenerator;
import pt.me.navigator.infrastructure.ScreenTickManager;
import pt.me.navigator.model.base.CameraModel;
import pt.me.navigator.model.base.WorldModel;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.RemoteInput;
import com.badlogic.gdx.math.Vector2;

public class Navigator implements ApplicationListener {
	public static final boolean ISDEV = true; // "pre-compiler" equivalent for branching development-only code
	private static final String TAG = Navigator.class.getSimpleName();
	
	// CONTROLLER RELATED
	private MyGestureListener myGestureListener;
	private MyInputProcessor myInputProcessor;

	// MODEL RELATED
	private WorldModel worldModel;
	private CameraModel cameraModel;
	
	// VIEW RELATED
	// Todas as views são instanciadas por "reflection"


	@Override
	public void create() {		
		// MODELS ///////////////////////////////////////////////////////////////
		cameraModel = new CameraModel();
		worldModel = WorldModel.getSingletonInstance();
		
		// VIEWS  ///////////////////////////////////////////////////////////////
		// Todas as views são instanciadas por "reflection"

		// CONTROLLERS - The GLUE ///////////////////////////////////////////////////////////////
		// Lança o controller dos ticks temporais : x second tick
		GameTickGenerator.getInstance(); //responsavel pela actualizacao dos modelos
		ScreenTickManager.getInstance(); //responsavel pela actualizacao das views

//		//FIXME: for development purposes only
//		RemoteInput receiver = new RemoteInput(7777);
//		Gdx.input = receiver;
		
		
		// Cria o controller dos gestos e regista-o --> este pode actuar quer ao nivel do modelo quer ao nivel da view
		myGestureListener = new MyGestureListener(cameraModel, worldModel);
		myInputProcessor = new MyInputProcessor(cameraModel, worldModel);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GestureDetector(1, 1.0f, 1.0f, 1.0f, myGestureListener));
		multiplexer.addProcessor(myInputProcessor);
		Gdx.input.setInputProcessor(multiplexer);		
		//Gdx.input.setCatchBackKey(true);
		//Gdx.input.setCatchMenuKey(true);
		
		

		
	}
	
	@Override
	public void dispose() {
		//## ASSETS UNLOAD
		
		GAME_CONSTANTS.DisposeAllObjects();
		
		GameTickGenerator.getInstance().dispose();
		ScreenTickManager.getInstance().dispose();
		
	}
	
	
	@Override
	// the main loop - maximum fps possible (Update rate para a View)
	public void render() {
		long elapsedNanoTime = (long)(Gdx.graphics.getDeltaTime()*GAME_CONSTANTS.ONE_SECOND_TO_NANO);
	
		// Clean do gl context
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ScreenTickManager.getInstance().fireEvent(cameraModel, elapsedNanoTime);		
	}
	

	@Override
	public void resize(int width, int height) {
		cameraModel.Resize();
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}


}
