package Jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

//class to listen to mouse presses in game window
public class MouseListener {

    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean[] mouseButtonPressed = new boolean[3];
    private boolean isDragging;

    //constructor set to private to keep use limited to within the class
    //variables initiated to 0 to keep mouse location consistent upon creation of class instance
    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    //get method to use so one MouseListener instance can be used throughout gameplay
    public static MouseListener get() {
        if  (MouseListener.instance == null) {
            instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    //callback method with long window for C-style memory allocation, used to find
    // mouse position and if it is dragging in game window
    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    //callback method with long window for C-style memory allocation, used to find mouse button presses in game window
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            //check in case user mouse has more than 3 buttons
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            //check in case user mouse has more than 3 buttons
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    //callback method with long window for C-style memory allocation, used to find mouse scrolls in game window
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    //method to reset positions to zero
    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    //getters
    public static float getX() {
        return (float)get().xPos;
    }

    public static float getY() {
        return (float)get().yPos;
    }

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }

    //method returning boolean to check if mouse is dragging
    public static boolean isDragging() {
        return get().isDragging;
    }

    //method returning boolean to check if mouse button is held down
    public static boolean mouseButtonDown(int button) {
        //check in case user mouse has more than 3 buttons
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}
