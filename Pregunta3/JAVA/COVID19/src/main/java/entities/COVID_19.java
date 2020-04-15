package entities;

import java.util.ArrayList;

/**
 *
 * @author kei98
 */

public class COVID_19 {
    private int id;
    private String fecha;
    private int casos_confirmados;
    private int recuperados;
    private int descartados;
    private int casos_criticos;
    private int fallecidos;
    private int cantones_afectados;
    private int total_mujeres;
    private int total_hombres;
    private static int strOcupado;
    private static ArrayList<Integer> data;
    private static int idB = 7;
    
    public COVID_19() {
        id = ++idB; 
        fecha = "Sin definir";
        casos_confirmados = 0;
        recuperados = 0;
        descartados = 0;
        casos_criticos = 0;
        fallecidos = 0;
        cantones_afectados = 0;
        total_mujeres = 0;
        total_hombres = 0;
        data = new ArrayList<>();
        strOcupado = 0;
    }
    
    public COVID_19(String fecha, int casos_confirmados, int recuperados, 
            int descartados, int casos_criticos, int fallecidos, int cantones_afectados,
            int total_mujeres, int total_hombres) {
        this.id = 0;
        this.fecha = fecha;
        this.casos_confirmados = casos_confirmados;
        this.recuperados = recuperados;
        this.descartados = descartados;
        this.casos_criticos = casos_criticos;
        this.fallecidos = fallecidos;
        this.cantones_afectados = cantones_afectados;
        this.total_mujeres = total_mujeres;
        this.total_hombres = total_hombres;
        data = new ArrayList<>();
        strOcupado = 0;
    }

    public COVID_19(int id, String fecha, int casos_confirmados, int recuperados, 
            int descartados, int casos_criticos, int fallecidos, int cantones_afectados,
            int total_mujeres, int total_hombres) {
        this.id = ++idB;
        this.fecha = fecha;
        this.casos_confirmados = casos_confirmados;
        this.recuperados = recuperados;
        this.descartados = descartados;
        this.casos_criticos = casos_criticos;
        this.fallecidos = fallecidos;
        this.cantones_afectados = cantones_afectados;
        this.total_mujeres = total_mujeres;
        this.total_hombres = total_hombres;
        data = new ArrayList<>();
        strOcupado = 0;
    }
    
    public int getId() {
        return id;
    }
/**
 * 
 * @param id 
 */
    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCasos_confirmados() {
        return casos_confirmados;
    }

    public void setCasos_confirmados(int casos_confirmados) {
        this.casos_confirmados = casos_confirmados;
    }

    public int getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(int recuperados) {
        this.recuperados = recuperados;
    }

    public int getDescartados() {
        return descartados;
    }

    public void setDescartados(int descartados) {
        this.descartados = descartados;
    }

    public int getCasos_criticos() {
        return casos_criticos;
    }

    public void setCasos_criticos(int casos_criticos) {
        this.casos_criticos = casos_criticos;
    }

    public int getFallecidos() {
        return fallecidos;
    }

    public void setFallecidos(int fallecidos) {
        this.fallecidos = fallecidos;
    }

    public int getCantones_afectados() {
        return cantones_afectados;
    }

    public void setCantones_afectados(int cantones_afectados) {
        this.cantones_afectados = cantones_afectados;
    }

    public int getTotal_mujeres() {
        return total_mujeres;
    }

    public void setTotal_mujeres(int total_mujeres) {
        this.total_mujeres = total_mujeres;
    }

    public int getTotal_hombres() {
        return total_hombres;
    }

    public void setTotal_hombres(int total_hombres) {
        this.total_hombres = total_hombres;
    }
    
    @Override
    public String toString(){
        String s = "ID: " + this.id;
        s += "\nFecha: " + this.fecha + "\nCasos confirmados: " + this.casos_confirmados;
        s += "\nRecuperados: " + this.recuperados + "\nDescartados: " + this.descartados;
        s += "\nCasos críticos: " + this.casos_criticos + "\nFallecidos: " + this.fallecidos;
        s += "\nCantones afectados: " + this.cantones_afectados;
        s+= "\nTotal mujeres: " + this.total_mujeres + "\nTotal hombres: " + this.total_hombres;
        return s;
    }
    
    public ArrayList<String>  noNulos(){
        ArrayList<String> s = new ArrayList<>();
        data.clear();
        if(this.id != 0){
            data.add(this.getId());
        }
        if(!this.fecha.equals("Sin definir") && !this.fecha.equals("")){
            s.add("fecha");
            strOcupado = 1;
        }
        if(this.casos_confirmados != 0){
            s.add("casos_confirmados");
            data.add(this.getCasos_confirmados());
        }
        if(this.recuperados != 0){
            s.add("recuperados");
            data.add(this.getRecuperados());
        }
        if(this.descartados != 0){
            s.add("descartados");
            data.add(this.getDescartados());
        }
        if(this.casos_criticos != 0){
            s.add("casos_criticos");
            data.add(this.getCasos_criticos());
        }
        if(this.fallecidos != 0){
            s.add("fallecidos");
            data.add(this.getFallecidos());
        }
        if(this.cantones_afectados != 0){
            s.add("cantones_afectados");
            data.add(this.getCantones_afectados());
        }
        if(this.total_mujeres != 0){
            s.add("total_mujeres");
            data.add(this.getTotal_mujeres());
        }
        if(this.total_hombres != 0){
            s.add("total_hombres");
            data.add(this.getTotal_hombres());
        }
        return s;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getStrOcupado() {
        return strOcupado;
    }
    
}
