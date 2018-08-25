package accountServer;

import resources.TestResource;

public class ResourceServerController  {

    private final TestResource test;

    public ResourceServerController(TestResource test) {
        this.test = test;
    }

    public String getName() {
        return test.getName();
    }

    public int getAge() {
        return test.getAge();
    }
}
