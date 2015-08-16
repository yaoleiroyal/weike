/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package app.controllers.api;

import com.github.sog.controller.BasicController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-08-25 14:21
 * @since JDK 1.6
 */
public class IndexController extends BasicController {
    /**
     * The Controller Logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * The index route.
     */
    public void index() {
        render("index.ftl");
    }
}