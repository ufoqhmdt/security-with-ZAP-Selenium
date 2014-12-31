/*******************************************************************************
 *    BDD-Security, application security testing framework
 *
 * Copyright (C) `2014 Stephen de Vries`
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see `<http://www.gnu.org/licenses/>`.
 ******************************************************************************/
package org.securitytests.cisecurity;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    protected XMLConfiguration xml;

 

    private synchronized static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private Config() {
        PropertyConfigurator.configure("log4j.properties");
        loadConfig("config.xml");
    }

    protected static Config config;

    public static String getClassName() {
        return validateAndGetString("class");
    }

    public static String getBaseUrl() {
        return validateAndGetString("baseUrl");
    }

    public static String getProxyApi() {
        return validateAndGetString("proxy.api");
    }

    public static String getSecureBaseUrl() {
        return validateAndGetString("secureBaseUrl");
    }

    public static String getDefaultDriver() {
        return validateAndGetString("defaultDriver");
    }

    public static String getDefaultDriverPath() {
        return validateAndGetString("defaultDriver[@path]");
    }

    public static String getProxyDriver() {
        return validateAndGetString("proxyDriver");
    }

    public static String getProxyDriverPath() {
        return validateAndGetString("proxyDriver[@path]");
    }

    private static String validateAndGetString(String value) {
        String ret = getXml().getString(value);
        if (ret == null) throw new RuntimeException(value+" not defined in config.xml");
        return ret;
    }

    public static String getProxyHost() {
        return validateAndGetString("proxy.host");
    }

    public static boolean displayStackTrace() {
        return getXml().getBoolean("displayStackTrace");
    }

    public static String getBaseSecureUrl() {
        return getXml().getString("baseSecureUrl");
    }
    public static String getBaseUrlForMicroSite() {
        return getXml().getString("baseUrlForMicroSite");
    }
    
    public static String getBaseUrlForEshop() {
        return getXml().getString("baseUrlForEshop");
    }
    public static String getIncorrectUsername() {
        return validateAndGetString("incorrectUsername");
    }

    public static String getIncorrectPassword() {
        return validateAndGetString("incorrectPassword");
    }

    public static int getProxyPort() {
        return getXml().getInt("proxy.port");
    }

    public static String getLatestReportsDir() {
        return validateAndGetString("latestReportsDir");
    }

    public static String getReportsDir() {
        return validateAndGetString("reportsDir");
    }

    public static List<String> getSessionIDs() {
        List<String> ids = new ArrayList<String>();
        for (Object o : getXml().getList("sessionIds.name")) {
            ids.add((String) o);
        }
        return ids;
    }


    public static void setXml(XMLConfiguration xml) {
        getInstance().xml = xml;
    }


    public static String getStoryDir() {
        return System.getProperty("user.dir")
                + File.separator
                + getXml().getString("storyDir");
    }

    public static String getStoryUrl() {
        return "file:///" + getStoryDir();
    }

    public synchronized static XMLConfiguration getXml() {
        return getInstance().xml;
    }

    public void loadConfig(String file) {
        try {
            xml = new XMLConfiguration();
            xml.load(file);

        } catch (ConfigurationException cex) {
            cex.printStackTrace();
            System.exit(1);
        }
    }





    public static synchronized void writeTable(String file,
                                               List<List<String>> table) {
        PrintStream writer = null;
        System.out.println("Writing to table file: " + file);
        try {
            writer = new PrintStream(new FileOutputStream(file, false));
            for (List<String> row : table) {
                StringBuilder sb = new StringBuilder();
                for (String col : row) {
                    sb.append("|").append(col).append("\t\t");
                }
                sb.append("|");
                writer.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }
}
