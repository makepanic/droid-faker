package de.rndm.droidFaker.model;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 23:00
 */
public class TaskHolderSingleton {
    private static TaskHolder ourInstance = null;

    private TaskHolderSingleton(TaskHolder taskHolder) {
        ourInstance = taskHolder;
    }

    public static TaskHolder getInstance() {
        return ourInstance;
    }

    public static TaskHolder init(TaskHolder taskHolder){
        ourInstance = taskHolder;
        return ourInstance;
    }
}
