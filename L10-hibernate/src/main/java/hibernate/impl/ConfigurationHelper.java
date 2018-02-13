package hibernate.impl;

import hibernate.models.UserDataSet;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ConfigurationHelper {

    static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addProperties(getProperties());
        return configuration;
    }

    static Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream("L10-hibernate/src/main/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
