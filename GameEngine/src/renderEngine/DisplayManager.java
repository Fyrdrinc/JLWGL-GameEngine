package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;
	
	// open the display at the beginning
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
										.withForwardCompatible(true)
										.withProfileCore(true);
		
		try {
			// determine size of the display
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			// create the display
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Our First Display!");
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		// tell opengl where to render the display, (bottom left, top right)
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	// update display, every frame
	public static void updateDisplay() {
		
		// sync the game to run at a steady fps
		Display.sync(FPS_CAP);
		Display.update();
		
	}
	
	// exit and close the display
	public static void closeDisplay() {
		
		Display.destroy();
	}
	
	
}
