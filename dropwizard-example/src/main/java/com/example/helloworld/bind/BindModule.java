package com.example.helloworld.bind;

import com.example.helloworld.HelloWorldConfiguration;
import com.example.helloworld.core.Person;
import com.example.helloworld.core.Task;
import com.example.helloworld.core.UserTest;
import com.example.helloworld.db.TaskDAO;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.inject.Named;


public class BindModule extends AbstractModule {

    private static final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
        new HibernateBundle<HelloWorldConfiguration>(Person.class, UserTest.class, Task.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Provides
    @Named("TaskDAO")
    public TaskDAO providesTaskDAO(SessionFactory factory){
        return new TaskDAO(hibernateBundle.getSessionFactory());
    }

}
