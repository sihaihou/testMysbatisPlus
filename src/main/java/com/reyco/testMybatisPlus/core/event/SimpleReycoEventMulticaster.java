/**
 * 
 */
package com.reyco.testMybatisPlus.core.event;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author reyco
 * @date 2021年7月30日
 */
@Component
public class SimpleReycoEventMulticaster extends AbstractReycoEventMulticaster implements ReycoEventPublisher {

	protected static Logger logger = LoggerFactory.getLogger(SimpleReycoEventMulticaster.class);
	
	private Executor taskExecutor;
	
	public SimpleReycoEventMulticaster() {
	}

	public SimpleReycoEventMulticaster(BeanFactory beanFactory) {
		setBeanFactory(beanFactory);
	}

	public Executor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(Executor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	@Override
	public void publishEvent(Object event) {
		ReycoEvent reycoEvent;
		if(event instanceof ReycoEvent) {
			reycoEvent = (ReycoEvent)event;
		}else {
			reycoEvent = new SimpleReycoEvent(event);
		}
		multicastEvent(reycoEvent);
	}
	@Override
	public void multicastEvent(ReycoEvent event) {
		for (ReycoListener<?> listener : getReycoListeners(event)) {
			Executor executor = getTaskExecutor();
			if (executor != null) {
				executor.execute(() -> invokeListener(listener, event));
			} else {
				invokeListener(listener, event);
			}
		}
	}

	protected void invokeListener(ReycoListener<?> listener, ReycoEvent event) {
		doInvokeListener(listener, event);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void doInvokeListener(ReycoListener listener, ReycoEvent event) {
		listener.onReycoEvent(event);
	}

}
