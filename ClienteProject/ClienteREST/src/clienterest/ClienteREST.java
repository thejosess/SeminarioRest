
package clienterest;

import static clienterest.ClienteREST.getFechaAsObjeto;
import static clienterest.ClienteREST.getFechaAsString;
import static clienterest.ClienteREST.getTodasFechas;
import static clienterest.ClienteREST.postFechaTo2015;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 *
 * @author pgarcia
 */
public class ClienteREST {
    
    public static final String MI_URL = "http://localhost:8080/SeminarioRest/demo/hello/";

    
    public static String getFechaAsString(String path) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(MI_URL).path(path);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.get();
        String fecha = response.readEntity(String.class);
        //hay en el pdf una forma más facil de hacerlo

        return fecha;
    }
    public static MiFecha getFechaAsObjeto(String path) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(MI_URL).path(path);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.get();
        MiFecha fecha = response.readEntity(MiFecha.class);

        return fecha;
    }

    public static ArrayList<MiFecha> getTodasFechas(String path) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(MI_URL).path(path);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.get();
        ArrayList<MiFecha> fechas = response.readEntity(new GenericType<ArrayList<MiFecha>>(){});

        return fechas;
    }
    public static MiFecha postFechaTo2015(String path, MiFecha fechaOriginal){
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(MI_URL).path(path);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.post(Entity.entity(fechaOriginal, MediaType.APPLICATION_JSON));
        MiFecha fecha15 = response.readEntity(MiFecha.class);

        return fecha15;
    }
    
    public static MiFecha postFechaTo2015facil(String path, MiFecha fechaOriginal){
        MiFecha fecha15 = ClientBuilder.newClient()
                .target(MI_URL)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(fechaOriginal, MediaType.APPLICATION_JSON))
                .readEntity(MiFecha.class);

        return fecha15;
    }
    public static void main(String[] args) {

        String fs = getFechaAsString("dateJSON");
        MiFecha f = getFechaAsObjeto("dateJSON");
        ArrayList<MiFecha> fechas = getTodasFechas("getAllDates");
        MiFecha fecha83 = new MiFecha(24,4,1983);
        MiFecha fecha15 = postFechaTo2015("myDate2015", fecha83);
        System.out.println(fecha15);

    }

}
