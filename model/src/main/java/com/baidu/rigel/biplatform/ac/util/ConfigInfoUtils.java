/**
 * Copyright (c) 2014 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baidu.rigel.biplatform.ac.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器配置信息，暂时只有Tesseract服务器地址配置
 * 
 * @author xiaoming.chen
 *
 */
public class ConfigInfoUtils {

    /**
     * LOG
     */
    private static Logger LOG = LoggerFactory.getLogger(ConfigInfoUtils.class);

    private static final String DEFAULT_SERVER_ADDRESS = "http://127.0.0.1:8080";

    private static Properties properties;
    /**
     * serverAddress
     */
    private static String SERVERADDRESS = null;

    static {
        FileInputStream inStream = null;
        try {
             String answerCoreConfFile = System
                    .getProperty("ac.config.location");
            properties = new Properties();
            inStream = new FileInputStream(answerCoreConfFile);
            properties.load(inStream);
            // Properties properties =
            // PropertiesLoaderUtils.loadAllProperties(answerCoreConfFile);
            SERVERADDRESS = properties.getProperty("server.tesseract.address",
                    DEFAULT_SERVER_ADDRESS);
            LOG.info("load serveraddress from properties:{}", SERVERADDRESS);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("不能获取ac配置文件");
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 设置服务器地址
     * 
     * @param serverAddress
     *            Tesseract服务器地址
     */
    public static void setServerAddress(String serverAddress) {
        ConfigInfoUtils.SERVERADDRESS = serverAddress;
    }
    
    /**
     * get ServerAddressByProperty
     * 
     * @return the sERVERADDRESS
     */
    public static String getServerAddressByProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * get sERVERADDRESS
     * 
     * @return the sERVERADDRESS
     */
    public static String getServerAddress() {
        return SERVERADDRESS;
    }

}
