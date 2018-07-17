package war_ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import war_ioc.account.AccountDBService;
import war_ioc.account.AccountDBServiceImpl;
import war_ioc.db.DBService;
import war_ioc.db.DBServiceCacheImpl;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    DBService getDbService() {
        return new DBServiceCacheImpl();
    }

    @Bean
    AccountDBService getAccountDbService() {
        return new AccountDBServiceImpl();
    }

    @Bean
    DataGenerator getDataGenerator(@Qualifier("getDbService") DBService dbService) {
        return new DataGenerator(dbService);
    }
}
