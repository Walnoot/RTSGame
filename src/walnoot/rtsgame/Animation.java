package walnoot.rtsgame;

import java.util.ArrayList;
import java.awt.Image;

public class Animation {
	/**
	 * the Animation class from  
	 * TheNewBoston!!
	 */
    private ArrayList scenes;
    private int sceneIndex;
    private long movieTime;
    private long totalTime;

    //Constructor
    public Animation() {
        scenes = new ArrayList();
        totalTime = 0;
        start();
    }

    //add scenes to the arraylist and set time for each scene
    public synchronized void addScene(Image i, long t) {
        totalTime += t;
        scenes.add(new Onescene(i, totalTime));
    }

    //start animation from beginning
    public synchronized void start() {
        movieTime = 0;
        sceneIndex = 0;
    }

    //change scenes
    public synchronized void update(long timePassed) {

        if(scenes.size() > 1 ) {
            movieTime += timePassed;

            if(movieTime >= totalTime) {
                movieTime = 0;
                sceneIndex = 0;
            }
            while(movieTime > getScene(sceneIndex).endTime) {
                sceneIndex++;
            }
        }
    }

    //get animations current scene
    public synchronized Image getImage() {

        if(scenes.size() == 0) {
            return null;
        }

        else {
            return getScene(sceneIndex).pic;
        }
    }

    //get scene
    private Onescene getScene(int x) {
        return (Onescene)scenes.get(x);
    }

    /////PRIVAT INNER CLASS/////
    private class Onescene{
        Image pic;
        long endTime;

        public Onescene(Image pic, long endTime) {
            this.pic = pic;
            this.endTime = endTime;
        }
    }

}