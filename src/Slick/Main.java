package Slick;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
public class Main {

	public Main(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Slick Programming");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		//init
		
		while(!Display.isCloseRequested()){
			//Render
			Display.update();
			Display.sync(60);			
		}
		Display.destroy();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Just to see if it works
		new Main();
	}
}
