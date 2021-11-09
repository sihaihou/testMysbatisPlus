package com.reyco.testMybatisPlus.core.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

/** 
 * @author  reyco
 * @date    2021.09.08
 * @version v1.0.1 
 */
public abstract class AbstractReycoEventMulticaster implements ReycoEventMulticaster,BeanFactoryAware,ApplicationContextAware{
	
	private final ListenerRetriever defaultRetriever = new ListenerRetriever(false);
	
	final Map<String, ListenerRetriever> retrieverCache = new ConcurrentHashMap<>(64);
	
	private BeanFactory beanFactory;
	
	private ApplicationContext applicationContext;
	
	private Object retrievalMutex = this.defaultRetriever;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		Map<String, ReycoListener> reycoListenerMap = applicationContext.getBeansOfType(ReycoListener.class);
		for (Entry<String, ReycoListener> entry: reycoListenerMap.entrySet()) {
			String key = entry.getKey();
			ReycoListener<?> value = entry.getValue();
			defaultRetriever.reycoListeners.add(value);
			defaultRetriever.reycoListenerBeans.add(key);
		}
	}
	@Override
	public void addReycoListener(ReycoListener<?> listener) {
		synchronized (this.retrievalMutex) {
			this.defaultRetriever.reycoListeners.remove(listener);
			this.defaultRetriever.reycoListeners.add(listener);
			this.retrieverCache.clear();
		}
	}
	@Override
	public void addReycoListenerBean(String listenerBeanName) {
		synchronized (this.retrievalMutex) {
			this.defaultRetriever.reycoListenerBeans.add(listenerBeanName);
			this.retrieverCache.clear();
		}		
	}
	@Override
	public void removeReycoListener(ReycoListener<?> listener) {
		synchronized (this.retrievalMutex) {
			this.defaultRetriever.reycoListeners.remove(listener);
			this.retrieverCache.clear();
		}
	}
	@Override
	public void removeReycoListenerBean(String listenerBeanName) {
		synchronized (this.retrievalMutex) {
			this.defaultRetriever.reycoListenerBeans.remove(listenerBeanName);
			this.retrieverCache.clear();
		}
	}
	@Override
	public void removeAllListeners() {
		synchronized (this.retrievalMutex) {
			this.defaultRetriever.reycoListeners.clear();
			this.defaultRetriever.reycoListenerBeans.clear();
			this.retrieverCache.clear();
		}
	}
	@SuppressWarnings("all")
	protected Collection<ReycoListener<?>> getReycoListeners(ReycoEvent event) {
		synchronized (this.retrievalMutex) {
			ListenerRetriever retriever = new ListenerRetriever(true);
			try {
				for (ReycoListener reycoListener : this.defaultRetriever.getReycoListeners()) {
					//事件原类型
					Class<?> sourceType = event.getSource().getClass();
					//事件类型
					Class<? extends ReycoEvent> eventType = event.getClass();
					
					
					Method method = reycoListener.getClass().getDeclaredMethod("onReycoEvent",ReycoEvent.class);
					Class parameterTypes = method.getParameterTypes()[0];
					if(parameterTypes.isAssignableFrom(eventType)) {
						retriever.reycoListeners.add(reycoListener);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return retriever.getReycoListeners();
		}
	}
	private class ListenerRetriever {

		public final Set<ReycoListener<?>> reycoListeners = new LinkedHashSet<>();

		public final Set<String> reycoListenerBeans = new LinkedHashSet<>();
		
		private final boolean preFiltered;
		
		public ListenerRetriever(boolean preFiltered) {
			this.preFiltered = preFiltered;
		}

		public Collection<ReycoListener<?>> getReycoListeners() {
			List<ReycoListener<?>> allListeners = new ArrayList<>(
					this.reycoListeners.size() + this.reycoListenerBeans.size());
			allListeners.addAll(this.reycoListeners);
			if (!this.reycoListenerBeans.isEmpty()) {
				BeanFactory beanFactory = getBeanFactory();
				for (String listenerBeanName : this.reycoListenerBeans) {
					try {
						ReycoListener<?> listener = beanFactory.getBean(listenerBeanName, ReycoListener.class);
						if (this.preFiltered || !allListeners.contains(listener)) {
							allListeners.add(listener);
						}
					}
					catch (NoSuchBeanDefinitionException ex) {
						// Singleton listener instance (without backing bean definition) disappeared -
						// probably in the middle of the destruction phase
					}
				}
			}
			if (!this.preFiltered || !this.reycoListenerBeans.isEmpty()) {
				AnnotationAwareOrderComparator.sort(allListeners);
			}
			return allListeners;
		}
	}
	
}
