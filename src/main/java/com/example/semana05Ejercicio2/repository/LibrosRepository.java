/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.semana05Ejercicio2.repository;

import com.example.semana05Ejercicio2.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jeanc
 */
public interface LibrosRepository extends JpaRepository<Libros, Long> {
}
