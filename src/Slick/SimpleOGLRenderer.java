package Slick;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
public class SimpleOGLRenderer {

	public SimpleOGLRenderer(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Slick Programming");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		//init
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			//Render
			glBegin(GL_QUADS);
				glVertex2i(400, 400);//Upper-Left
				glVertex2i(450, 400);//Upper-Right
				glVertex2i(450, 450);//Bottom-Right
				glVertex2i(400, 450);//Bottom-Left
			glEnd();
			
			glBegin(GL_LINES);
				glVertex2i(100,100);
				glVertex2i(200,200);
			glEnd();
			
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
		new SimpleOGLRenderer();
	}
}
