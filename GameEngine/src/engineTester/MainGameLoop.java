package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();		
		Loader loader = new Loader();	
		
		RawModel model = OBJLoader.loadObjModel("plantpot", loader);
		
		TexturedModel plantModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("plantpotTexture")));

		Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		List<Entity> allPlants = new ArrayList<Entity>();
		Random random = new Random();
		
		for( int i = 0; i < 50000; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			allPlants.add(new Entity(plantModel, new Vector3f(x,y,z), random.nextFloat() * 180f, 
					random.nextFloat() * 180f, 0f, 1f));
		}
		
		MasterRenderer renderer = new MasterRenderer();
		
		// loop until exit, e.g. x
		while(!Display.isCloseRequested()) {		
			camera.move();
			for(Entity plant : allPlants) {
				renderer.processEntity(plant);
				plant.increasePosition(random.nextFloat() * 1f - 0.5f, random.nextFloat() * 1f - 0.5f, random.nextFloat() * 1f - 0.5f);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.CleanUp();
		DisplayManager.closeDisplay();

	}

}
