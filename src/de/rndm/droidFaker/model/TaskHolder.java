package de.rndm.droidFaker.model;

import android.content.Context;
import de.rndm.droidFaker.model.tasks.Task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:45
 */

/**
 * Class that holds a list of {@link de.rndm.droidFaker.model.tasks.Task}
 */
public class TaskHolder {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Calls the run method on each Task in the list
     * @param context application context
     */
    public void exec(Context context){
        for(Task task: tasks){
            task.run(context);
        }
    }

    /**
     * Initializes all Task classes from a given list of HashMaps and stores them in the tasks field
     * @param tasks List that represents the "tasks" field from the config file
     */
    public TaskHolder(ArrayList<HashMap<String, Object>> tasks) {

        for(HashMap<String, Object> taskMap: tasks){

            String type = (String) taskMap.get("type");

            // load class by name
            try {
                // prepare string, change to first char to uppercase
                String className = type.toLowerCase();
                className = className.substring(0, 1).toUpperCase() + className.substring(1);

                Class<?> usedClass = Class.forName("de.rndm.droidFaker.model.tasks." + className + "Task");
                Object wantedTask = usedClass.newInstance();
                Method m = wantedTask.getClass().getDeclaredMethod("init", HashMap.class);
                m.invoke(wantedTask, taskMap);
                this.tasks.add((Task)wantedTask);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }  catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
