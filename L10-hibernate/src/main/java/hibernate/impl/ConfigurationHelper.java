package hibernate.impl;

import hibernate.models.AddressDataSet;
import hibernate.models.PhoneDataSet;
import hibernate.models.UserDataSet;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ConfigurationHelper {

    static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration
                .addAnnotatedClass(UserDataSet.class)
                .addAnnotatedClass(AddressDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class);
        return configuration;
    }
}
