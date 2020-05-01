package es.ugr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyDate {

    private int day;
    private int month;
    private int year;
    
    public MyDate(){}; //Hay que declarar un constructor vacío
    
    public MyDate(int dia, int mes, int anio){
        this.day = dia;
        this.month = mes;
        this.year = anio;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
