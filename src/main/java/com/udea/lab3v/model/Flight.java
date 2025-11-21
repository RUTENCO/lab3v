package com.udea.lab3v.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idflight")
    private Long idflight;

    @Column(name = "nombreavion", nullable = false, length = 80)
    private @NonNull String nombreAvion;

    @Column(name = "numerovuelo", nullable = false, length = 80)
    private @NonNull String numeroVuelo;

    @Column(name = "origen", nullable = false, length = 80)
    private @NonNull String origen;

    @Column(name = "destino", nullable = false, length = 80)
    private @NonNull String destino;

    @Column(name = "capacidad", nullable = false, length = 80)
    private int capacidad;

    @Column(name = "rating", nullable = false, length = 80)
    @Min(value = 1, message = "id shoud be more or than equals 1")
    @Max(value = 5, message = "id shoud be less or than equals 5" )
    private int rating;

    @Column(name = "planvuelo", nullable = false, length = 80)
    private long planVuelo;

    private Boolean cumplido;

    public Long getIdflight() {
        return idflight;
    }

    public void setIdflight(Long idflight) {
        this.idflight = idflight;
    }

    public @NonNull String getNombreAvion() {
        return nombreAvion;
    }

    public void setNombreAvion(@NonNull String nombreAvion) {
        this.nombreAvion = nombreAvion;
    }

    public @NonNull String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(@NonNull String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public @NonNull String getOrigen() {
        return origen;
    }

    public void setOrigen(@NonNull String origen) {
        this.origen = origen;
    }

    public @NonNull String getDestino() {
        return destino;
    }

    public void setDestino(@NonNull String destino) {
        this.destino = destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Min(value = 1, message = "id shoud be more or than equals 1")
    @Max(value = 5, message = "id shoud be less or than equals 5")
    public int getRating() {
        return rating;
    }

    public void setRating(@Min(value = 1, message = "id shoud be more or than equals 1") @Max(value = 5, message = "id shoud be less or than equals 5") int rating) {
        this.rating = rating;
    }

    public long getPlanVuelo() {
        return planVuelo;
    }

    public void setPlanVuelo(long planVuelo) {
        this.planVuelo = planVuelo;
    }

    public Boolean getCumplido() {
        return cumplido;
    }

    public void setCumplido(Boolean cumplido) {
        this.cumplido = cumplido;
    }
}
