package com.tvd12.ezyfox.core.testing.action;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.action.Action;
import com.tvd12.ezyfox.core.action.ActionChain;
import com.tvd12.ezyfox.core.command.Schedule;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.transport.Parameters;

import lombok.Setter;

public class ActionChainTest {

    private AppContext context;
    
    public ActionChainTest() {
        context = mock(AppContext.class);
        when(context.command(Schedule.class)).thenReturn(new ScheduleImpl1());
    }
    
    @Test
    public void testValidCase1() throws Exception {
        FinishAction finishAction = new FinishAction();
        BettingAction bettingAction = new BettingAction();
        ActionChain actionChain = new ActionChain.Builder()
                .context(context)
                .action(new ShakeAction())
                .action(finishAction)
                .actions(Lists.newArrayList(bettingAction, finishAction))
                .build();
        finishAction.setActionChain(actionChain);
        actionChain.loop(1000, 0);
        actionChain.stop();
    }
    
    public static void main(String[] args) throws Exception {
        new ActionChainTest().testValidCase1();
    }
    
    @Test
    public void testValidCase2() throws Exception {
        FinishAction finishAction = new FinishAction();
        ActionChain actionChain = new ActionChain.Builder()
                .context(context)
                .action(new ShakeAction())
                .action(new BettingAction())
                .action(finishAction)
                .build();
        assertFalse(actionChain.isStopped());
        finishAction.setActionChain(actionChain);
        assertEquals(actionChain.getActions().size(), 3);
        actionChain.forever(3);
        actionChain.stop();
    }
    
    @Test
    public void testValidCase3() throws Exception {
        Action shake = new ShakeAction();
        FinishAction finishAction = new FinishAction();
        ActionChain actionChain = new ActionChain.Builder()
                .context(context)
                .action(shake)
                .action(new BettingAction())
                .action(finishAction)
                .build();
        finishAction.setActionChain(actionChain);
        actionChain.removeAction(shake);
        actionChain.removeAction(1);
        actionChain.removeAction(100);
        actionChain.forever(3);
        actionChain.stop();
    }
    
    @Test
    public void testValidCase4() throws Exception {
        ActionChain actionChain = new ActionChain.Builder()
                .context(context)
                .build();
        actionChain.loop(3, 3);
//        actionChain.stop();
    }
    
    @Test
    public void testValidCase5() {
        ActionChain actionChain = new ActionChain.Builder()
                .context(context)
                .action(new ShakeAction())
                .build();
        actionChain.one(0);
//        actionChain.stop();
    }
    
    public static class ShakeAction implements Action {

        @Override
        public long delay() {
            return 3;
        }

        @Override
        public void execute(AppContext context, Parameters params) {
        }
        
    }
    
    public static class BettingAction implements Action {

        @Override
        public long delay() {
            return 3;
        }

        @Override
        public void execute(AppContext context, Parameters params) {
        }
        
    }
    
    public static class FinishAction implements Action {
        
        @Setter
        private ActionChain actionChain;
        
        @Override
        public long delay() {
            return 3;
        }

        @Override
        public void execute(AppContext context, Parameters params) {
            this.actionChain.stop();
            try {
                Field field = ReflectFieldUtil.getField("count", ActionChain.class);
                field.setAccessible(true);
                if(field.getLong(actionChain) > 100L) 
                    field.setLong(actionChain, 100L);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static class ScheduleImpl1 implements Schedule {
        
        protected long delayTime;
        protected boolean stopped;
        
        private Runnable runabble;
        
        @Override
        public ScheduleImpl1 delay(long time) {
            this.delayTime = time;
            return this;
        }

        @Override
        public ScheduleImpl1 oneTime(boolean value) {
            return this;
        }

        @Override
        public ScheduleImpl1 period(long value) {
            return this;
        }

        @Override
        public ScheduleImpl1 task(Runnable value) {
            this.runabble = value;
            return this;
        }

        @Override
        public void schedule() {
            runabble.run();
        }

        @Override
        public void stop() {
        }
        
        @Override
        public void stopNow() {
        }

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.command.Schedule#stopped()
         */
        @Override
        public boolean stopped() {
            return this.stopped;
        }
        
    }
    
    public static class ScheduleImpl2 implements Schedule {
        
        private long delayTime;
        
        private Runnable runabble;
        private ScheduledExecutorService executorService;
        
        public ScheduleImpl2() {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        
        @Override
        public ScheduleImpl2 delay(long time) {
            this.delayTime = time;
            return this;
        }

        @Override
        public ScheduleImpl2 oneTime(boolean value) {
            return this;
        }

        @Override
        public ScheduleImpl2 period(long value) {
            return this;
        }

        @Override
        public ScheduleImpl2 task(Runnable value) {
            this.runabble = value;
            return this;
        }

        @Override
        public void schedule() {
            executorService.schedule(runabble, delayTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public void stop() {
            executorService.shutdown();
        }
        
        @Override
        public void stopNow() {
            executorService.shutdownNow();
        }
        
        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.command.Schedule#stopped()
         */
        @Override
        public boolean stopped() {
            return false;
        }
        
    }
    
}
