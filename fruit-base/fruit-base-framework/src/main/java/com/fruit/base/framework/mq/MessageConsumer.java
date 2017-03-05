package com.fruit.base.framework.mq;

/**
 *
 * @param <T>
 */
public interface MessageConsumer<T> {

	void onMessage(T message);
}
