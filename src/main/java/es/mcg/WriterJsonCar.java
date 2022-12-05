package es.mcg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.mcg.WriterJsonCar.Coche.Motor;

public class WriterJsonCar {
    private static Coche createNewCar()
    {
        return new Coche("Volkswagen", "Polo", new Motor(1500, "diesel"), 5, 200000, new Date());
    }
    public static class Json {
        private static ObjectMapper MAPPER;

        public static ObjectMapper mapper()
        {
            if(Json.MAPPER == null)
            {
                Json.MAPPER = Json.createJson();
            }

            return Json.MAPPER;
        }

        public static ObjectMapper createJson()
        {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
            return mapper;
        }
    }
    public static class Coche {
        private String marca, modelo;
        private Motor motor;
        private Integer puertas, kilometros;
        private Date fechaCompra;
        public Coche(String marca, String modelo, Motor motor, Integer puertas, Integer kilometros, Date fechaCompra) {
            this.marca = marca;
            this.modelo = modelo;
            this.motor = motor;
            this.puertas = puertas;
            this.kilometros = kilometros;
            this.fechaCompra = fechaCompra;
        }
        public String getMarca() {
            return marca;
        }
        public void setMarca(String marca) {
            this.marca = marca;
        }
        public String getModelo() {
            return modelo;
        }
        public void setModelo(String modelo) {
            this.modelo = modelo;
        }
        public Motor getMotor() {
            return motor;
        }
        public void setMotor(Motor motor) {
            this.motor = motor;
        }
        public Integer getPuertas() {
            return puertas;
        }
        public void setPuertas(Integer puertas) {
            this.puertas = puertas;
        }
        public Integer getKilometros() {
            return kilometros;
        }
        public void setKilometros(Integer kilometros) {
            this.kilometros = kilometros;
        }
        public Date getFechaCompra() {
            return fechaCompra;
        }
        public void setFechaCompra(Date fechaCompra) {
            this.fechaCompra = fechaCompra;
        }
        public static class Motor {
            private Integer revoluciones;
            private String tipo;
            public Motor(Integer revoluciones, String tipo) {
                this.revoluciones = revoluciones;
                this.tipo = tipo;
            }
            public Integer getRevoluciones() {
                return revoluciones;
            }
            public void setRevoluciones(Integer revoluciones) {
                this.revoluciones = revoluciones;
            }
            public String getTipo() {
                return tipo;
            }
            public void setTipo(String tipo) {
                this.tipo = tipo;
            }
            @Override
            public String toString() {
                return "Motor [revoluciones=" + revoluciones + ", tipo=" + tipo + "]";
            }
        }
    }
    public static void main(String[] args) {
        try 
        {
            List<Coche> listaCoches = new ArrayList<Coche>();
            listaCoches.add(createNewCar());

            ObjectWriter writer = Json.mapper().writer(new DefaultPrettyPrinter());
            writer.writeValue(new File("cars-out.json"), listaCoches);
        } 
        catch (IOException jsonException) 
        {
            jsonException.printStackTrace();
        }
    }
}
