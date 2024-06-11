package com.sparta.PetApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();


    static {
        try (InputStream inputStream = AppConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("api_url");
    }
    public static String getToken() {
        return properties.getProperty("api_token");
    }
    public static String getPetPath() {
        return properties.getProperty("repo_path");
    }
    public static String getPetByStatPath() {
        return properties.getProperty("petByStat_path");
    }

    public static String getPetByTagPath() {
        return properties.getProperty("petByTag_path");
    }

    public static String getPetByIdPath() {
        return properties.getProperty("petById_path");
    }

    public static String getPetImgPath() {
        return properties.getProperty("petIMG");
    }

    public static String getStoreInvPath() {
        return properties.getProperty("storeInv_path");
    }

    public static String getStoreOrderPath() {
        return properties.getProperty("storeOrder_path");
    }

    public static String getStoreByOrderIdPath() {
        return properties.getProperty("storeByOrderId_path");
    }

    public static String getUserPath() {
        return properties.getProperty("user_path");
    }

    public static String getUserWithListPath() {
        return properties.getProperty("userWithList_path");
    }

    public static String getUserLoginPath() {
        return properties.getProperty("userLogin_path");
    }

    public static String getUserLogoutPath() {
        return properties.getProperty("userLogout_path");
    }

    public static String getUserByUsernamePath() {
        return properties.getProperty("userByUsername");
    }

}
