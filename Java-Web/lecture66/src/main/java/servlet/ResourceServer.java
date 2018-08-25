package servlet;

import accountServer.ResourceService;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Написать ResourceServer, который будет содержать ссылку на TestResource.
public class ResourceServer extends HttpServlet {

    private TestResource test;

    public ResourceServer(TestResource test) {
        this.test = test;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        try {
            ResourceService service = new ResourceService(test);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }
        test = (TestResource) ReadXMLFileSAX.readXML(path);
    }
}
