package org.github.kaninhop;

import javax.jcr.SimpleCredentials;

public class Constants {

    /**
     * Credentials for admin access to repository
     */
    public static final SimpleCredentials ADMIN_CREDENTIALS = new SimpleCredentials("admin", "admin".toCharArray());

    /**
     * Default name for imported rootnode without defined workspace
     */
    public static final String DEFAULT_WORKSPACE = "default-workspace";

}