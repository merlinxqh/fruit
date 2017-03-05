package com.fruit.base.framework.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @param <T>
 */
public abstract class AbstractMessageConsumer<T> implements MessageConsumer<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
}
