package com.tvd12.ezyfox.core.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ConcurrentParameters;

import lombok.Getter;

/**
 * Support for run list of actions in concurrent enviroment
 * 
 * @author tavandung12
 *
 */
public class ActionChain {

    // application context
    private AppContext context;
    
    // schedule command
    private Schedule schedule;
    
    // list of actions
    @Getter
    private List<Action> actions;
    
    // list of removed actions
    private List<Action> removedActions
            = new ArrayList<>();
    
    // parameters object to transport data between actions
    private Parameters parameters 
            = new ConcurrentParameters();
    
    // loop count
    private long count = 1;
    
    // stop or not
    @Getter
    private boolean stopped = false;
    
    // convenient key
    public static final String NAME = "___atc___";
    
    /**
     * construct with builder
     * 
     * @param builder a builder object
     */
    private ActionChain(Builder builder) {
        this.actions = builder.actions;
        this.context = builder.context;
    }
    
    /**
     * remove action from list
     * 
     * @param action action to remove
     */
    public void removeAction(Action action) {
        removedActions.add(action);
    }
    
    /**
     * remove action from list at index
     * 
     * @param index index of action to remove
     */
    public void removeAction(int index) {
        if(index >= actions.size())
            return;
        removedActions.add(actions.get(index));
    }
    
    /**
     * remove all unused actions 
     * 
     */
    protected void removeUnusedActions() {
        actions.removeAll(removedActions);
        removedActions.clear();
    }
    
    /**
     * stop all actions
     */
    public synchronized void stop() {
        stopped = true;
        schedule.stop();
    }
    
    /**
     * execute action chain one time 
     * 
     * @param delay delay time
     */
    public void one(long delay) {
        loop(1, delay);
    }
    
    /**
     * execute action chain forever
     * 
     * @param delay delay time
     */
    public void forever(long delay) {
        loop(Long.MAX_VALUE, delay);
    }
    
    /**
     * execute action chain with specific count
     * 
     * @param count number of loop count
     * @param delay delay time
     */
    public void loop(long count, long delay) {
        this.count = count;
        if(delay == 0) {
            start();    return;
        }
        schedule = context.command(Schedule.class);
        schedule.oneTime(true)
            .delay(delay)
            .task(new Runnable() {
                @Override
                public void run() { start();    }
            }).schedule();
    }
    
    /**
     * start execution action chain
     */
    private void start() {
        if(0 >= count --)
            return;
        removeUnusedActions();
        if(actions.size() == 0)
            return;
        Iterator<Action> iterator = actions.iterator();
        start(iterator.next(), iterator);
    }
    
    /**
     * start execution action
     * 
     * @param action action to execute
     * @param iterator rest of action chain
     */
    private void start(final Action action, final Iterator<Action> iterator) {
        schedule = context.command(Schedule.class);
        schedule.oneTime(true)
            .delay(action.delay())
            .task(new Runnable() {
                @Override
                public void run() {
                    action.execute(context, parameters);
                    if(!iterator.hasNext()) start();
                    else start(iterator.next(), iterator);
                }
            }).schedule();
    }
    
    /**
     * Support to build an action chain
     * 
     * @author tavandung12
     *
     */
    public static class Builder {
        
        // application context
        private AppContext context;
        
        // list of actions
        private List<Action> actions
                = new ArrayList<>();
        
        /**
         * set application context
         * 
         * @param context application context
         * @return this pointer
         */
        public Builder context(AppContext context) {
            this.context = context;
            return this;
        }
        
        /**
         * add action to list
         * 
         * @param action action to add
         * @return this pointer
         */
        public Builder action(Action action) {
            actions.add(action);
            return this;
        }
        
        /**
         * add list of actions to list
         * 
         * @param actions list of action
         * @return this pointer
         */
        public Builder actions(Collection<Action> actions) {
            actions.addAll(actions);
            return this;
        }
        
        /**
         * build an action chain object
         * 
         * @return action chain object
         */
        public ActionChain build() {
            return new ActionChain(this);
        }
    }
    
}
