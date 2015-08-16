/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package app.kits;

import java.util.UUID;

/**
 * <p>
 * .
 * </p>
 *
 * @author walter yang
 * @version 1.0 2013-10-30 10:53 AM
 * @since JDK 1.5
 */
public class EncodeKit {

    public static String toUUID() {
        return UUID.randomUUID().toString();
    }

}
