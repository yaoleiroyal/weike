/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 jfinal app. jfapp Group.
 */

package app.events;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-08-07 10:40
 * @since JDK 1.6
 */
public class EventBus {

    public static final int ASYNC_THREAD_SIZE = 80;
    private final AsyncEventBus asyncEventBus;

    EventBus() {
        asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(ASYNC_THREAD_SIZE));
        asyncEventBus.register(new BusListener());
    }


}
