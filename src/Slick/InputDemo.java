package Slick;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
public class InputDemo {
	
	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean somethingIsSelected = false;
	private volatile boolean randomColorCooldown = false;
	public InputDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Slick Programming");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		shapes.add(new Box(15,15));
		shapes.add(new Box(150,150));
		//init
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); 
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			
			while(Keyboard.next()){
				if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()){
					shapes.add(new Box(15,15));
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			
			
			for(Box box : shapes){
				if(Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(),Display.getHeight() - Mouse.getY())){
					somethingIsSelected = true;
					box.selected = true;
					System.out.println("You Clicked my!");
					}
				if(Mouse.isButtonDown(2) &box.inBounds(Mouse.getX(),Display.getHeight() - Mouse.getY())){
					box.randomizeColors();
					new Thread(new Runnable(){
						
						@Override
						public void run(){
							try{
								Thread.sleep(200);
							}catch(InterruptedException e){
								e.printStackTrace();
							}finally{
								randomColorCooldown = false;
							}
						}
					}).run();
				}
				if(!Mouse.isButtonDown(0)){
						
						box.selected = false;
						somethingIsSelected = false;
					}
				if(box.selected){
					box.Update(Mouse.getDX(), -Mouse.getDY());
				}
					box.draw();
			}
				
			
		
			Display.update();
			Display.sync(60);			
		}
		Display.destroy();
	}
	private static class Box{
		public int x,y;
		public boolean selected = false;
		private float colorRed, colorBlue, colorGreen;
		
		Box(int x, int y){
			this.x = x;
			this.y = y;
		
			Random randomGenerator = new Random();
			colorRed = randomGenerator.nextFloat();
			colorBlue = randomGenerator.nextFloat();
			colorGreen = randomGenerator.nextFloat();
		}
		
	void Update(int dx, int dy){
		x += dx;
		y += dy;
	}
	boolean inBounds(int mousex, int mousey){
		if(mousex > x && mousex < x + 50 && mousey > y && mousey < y + 50)
			return true;
		else
		    return false;
	}
		
	void randomizeColors(){
		Random randomGenerator = new Random();
		colorRed = randomGenerator.nextFloat();
		colorBlue = randomGenerator.nextFloat();
		colorGreen = randomGenerator.nextFloat();
	}
	void draw(){
		glColor3f(colorRed, colorBlue, colorGreen);
		glBegin(GL_QUADS);
			glVertex2i(x, y);
			glVertex2i(x + 50, y);
			glVertex2i(x + 50, y + 50);
			glVertex2i(x, y + 50);
		glEnd();
	}
	
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Just to see if it works
		new InputDemo();
	}
}
;