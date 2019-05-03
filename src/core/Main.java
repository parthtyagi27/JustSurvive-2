package core;

import engine.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import world.Level;

public class Main
{
    //Window/Game Loop related objects
    public static final int WIDTH = 500, HEIGHT = 700;
    private static Window window;
    private static final double fpsCap = 1.0 / 60.0;
    private static double time, unprocessedTime = 0;

    //Game objects
    public static Camera camera;

    private static Level level;

    public static void main(String[] args)
    {
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        //Init and render the window object
        window.setCallBack();
        window = new Window(WIDTH, HEIGHT, "Just Survive 2.0", false);
//        window.setIcon("/res/icon.png");
        window.render();

        GLFW.glfwMakeContextCurrent(window.getWindowID());
        GL.createCapabilities();
        //Init OpenAL
        loadAudio();
        System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        //Init GL code
        initGL();
        //Init game objects
        init();

        //GAME LOOP

        time = getTime();
        double frameTime = 0;
        unprocessedTime = 0;
        int frames = 0;

        while (!window.isClosed())
        {
            boolean canRender = false;
            double currentTime = getTime();
            double delta = currentTime - time;
            unprocessedTime += delta;
            frameTime += delta;
            time = currentTime;

            while (unprocessedTime >= fpsCap)
            {
                unprocessedTime -= fpsCap;
                canRender = true;
                window.update();
                update();

                if (frameTime >= 1.0)
                {
                    //Reset FPS counter (This part gets executed every second)
                    frameTime = 0;
                    System.out.println("FPS = " + frames);
                    frames = 0;
                }
            }

            if (canRender)
            {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                render();
                window.swapBuffers();
                frames++;
            }
        }
        //Dispose of resources
        flush();
    }

    private static void update()
    {
        level.update();
    }

    private static void render()
    {
        level.render();
    }

    private static void init()
    {
        camera = new Camera(WIDTH, HEIGHT);
        level = new Level();
    }

    private static void initGL()
    {
        GLFW.glfwSetKeyCallback(window.getWindowID(), new Input());
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Shader.loadShaders();
        Handler.loadHandler(window);
        TextureAtlas.loadTextureAtlas("/resources/textureAtlas.png");
        TextureAtlas.texture.bind();
    }

    private static void loadAudio()
    {
        AudioManager.init();
    }

    private static void flush()
    {
        Shader.deleteAll();
        window.flush();
        AudioManager.flush();
        GL.destroy();
        GLFW.glfwTerminate();
        System.out.println("Disposed Resources");
        System.exit(0);
    }

    private static double getTime()
    {
        return (double) System.nanoTime() / (double) 1000000000L;
    }

}
