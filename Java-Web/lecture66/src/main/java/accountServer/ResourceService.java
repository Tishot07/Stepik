package accountServer;

import resources.TestResource;

import javax.management.*;
import java.lang.management.ManagementFactory;

/*
Вывести ResourceServer в JMX с именем:
Admin:type=ResourceServerController
Сделать переменные "name" и "age" доступными для чтения из jmx клиента.
 */
public class ResourceService {

    private TestResource test;

    public ResourceService(TestResource test) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        this.test = test;
        ResourceServerController serverStatistics = new ResourceServerController(test);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(serverStatistics, name);
    }




}
