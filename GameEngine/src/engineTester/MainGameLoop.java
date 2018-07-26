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
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();		
		Loader loader = new Loader();	
		
		ModelData data = OBJFileLoader.loadOBJ("cactus");
		RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), 
				data.getNormals(), data.getIndices());		
		TexturedModel cactusModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("cactus")));

		data = OBJFileLoader.loadOBJ("agave");
		RawModel model2 = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), 
				data.getNormals(), data.getIndices());		
		TexturedModel agaveModel = new TexturedModel(model2,new ModelTexture(loader.loadTexture("agave")));

		data = OBJFileLoader.loadOBJ("grass");
		RawModel model3 = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), 
				data.getNormals(), data.getIndices());		
		TexturedModel grassModel = new TexturedModel(model3,new ModelTexture(loader.loadTexture("grass")));
		grassModel.getTexture().setHasTransparency(true);
		grassModel.getTexture().setUseFakeLighting(true);
		
//		Entity entity = new Entity(plantModel, new Vector3f(0,0,-25),0,0,0,3);
		Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1,1,1));
		
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("sand")));
//		Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("sand")));
//		Terrain terrain3 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("sand")));
		List<Terrain> terrain = new ArrayList<Terrain>();
		for( int i = 0; i < 4; i++) {
			terrain.add(new Terrain(i-1,-1,loader,new ModelTexture(loader.loadTexture("sand"))));
		}
		
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		
		List<Entity> plants = new ArrayList<Entity>();
		Random random = new Random();		
		for( int i = 0; i < 500; i++) {
			plants.add(new Entity(cactusModel, 
					new Vector3f(random.nextFloat()*800 - 400,0f,random.nextFloat() * -600),
					0,random.nextFloat()*360,0,2));
		}
		for( int i = 0; i < 500; i++) {
			plants.add(new Entity(agaveModel, 
					new Vector3f(random.nextFloat()*800 - 400,0f,random.nextFloat() * -600),
					0,random.nextFloat()*360,0,2));
		}		
		for( int i = 0; i < 500; i++) {
			plants.add(new Entity(grassModel, 
					new Vector3f(random.nextFloat()*800 - 400,0.5f,random.nextFloat() * -600),
					0,random.nextFloat()*360,0,2));
		}			
		
		
		// loop until exit, e.g. x
		while(!Display.isCloseRequested()) {		
			camera.move();
			for(Entity plant : plants) {
				renderer.processEntity(plant);
			}
//			renderer.processTerrain(terrain);
//			renderer.processTerrain(terrain2);
//			renderer.processTerrain(terrain3);
			for(Terrain tera : terrain) {
				renderer.processTerrain(tera);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.CleanUp();
		DisplayManager.closeDisplay();

	}

}
