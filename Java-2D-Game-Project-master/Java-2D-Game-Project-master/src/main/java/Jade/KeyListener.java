package Jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

//class to listen for key presses in game window
public class KeyListener {
    private static KeyListener instance;
    private boolean[] keyPressed = new boolean[350];

    //empty constructor
    private KeyListener() {

    }

    //get method to use so one KeyListener instance can be used throughout gameplay
    public static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }

    //callback method with long window for C-style memory allocation to get button press
    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    //method to return if a key is pressed
    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
}
