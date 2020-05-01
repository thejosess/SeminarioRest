package es.ugr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class Servicio {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Plain";
    }

    @GET
    @Path("hello2")
    public String sayPlainTextHello2() {
        return "jolines";
    }

    @GET
    @Path("/helloId/{oid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloWithId(@PathParam("oid") int id) {
        return "Hola amigui " + id;
    }

    @GET
    @Path("/helloQuery")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloWithQuery(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        return "Hello " + name + " " + surname;
    }

    @GET
    @Path("/dateJSON")
    @Produces({"application/json"})
    public MyDate getDate_JSON() {
        MyDate oneDate = new MyDate();
        oneDate.setDay(25);
        oneDate.setMonth(12);
        oneDate.setYear(2015);
        return oneDate;
    }

    @POST
    @Path("/name")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String HelloName(String myName) {
        return "Hello " + myName;
    }

    @POST
    @Path("/myDate2015")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyDate dateTo2015(MyDate myDate) {
        myDate.setYear(2015);
        return myDate;
    }

    @POST
    @Path("/myDateForm")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public MyDate dateFromFromToJSON(@FormParam("day") int myDay, @FormParam("month") int myMonth,
            @FormParam("year") int myYear) {
        MyDate myDate = new MyDate();
        myDate.setDay(myDay);
        myDate.setMonth(myMonth);
        myDate.setYear(myYear);
        return myDate;
    }

    private static Map<String, MyDate> myMap = new HashMap<>();

    static {

        myMap.put("Navidad", new MyDate(25,12,2020));
        myMap.put("Nochebuena", new MyDate(24,12,2020));
        myMap.put("AnioNuevo", new MyDate(1,1,2020));
        myMap.put("Reyes", new MyDate(6,1,2020));
    }

    @PUT
    @Path("/modifyDate/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyImportantDate(@PathParam("date") String key, MyDate myDate) {
        myMap.put(key, myDate);
        return "Fecha modificada";
    }

    @GET
    @Path("/getAllDates")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MyDate> getAllDates() {
        ArrayList<MyDate> fechas = new ArrayList<MyDate>();
        fechas.addAll(myMap.values());
        return fechas;

    }

}
