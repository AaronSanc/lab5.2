/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.semana05Ejercicio2.controller;

import com.example.semana05Ejercicio2.model.Libros;
import com.example.semana05Ejercicio2.repository.LibrosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jeanc
 */
@Controller
public class LibrosController {

    @Autowired
    private LibrosRepository librosRepository;

    @GetMapping("/listarLibros")
    public String listarLibros(Model model) {
        List<Libros> libros = librosRepository.findAll();
        model.addAttribute("libros", libros);
        model.addAttribute("libro", new Libros()); // Objeto libro vacío para el formulario modal
        return "listar_libros";
    }

    @PostMapping("/submitLibro")
    public String procesarLibro(@ModelAttribute Libros libro, Model model) {
        librosRepository.save(libro);
        model.addAttribute("libro", libro);
        return "redirect:/listarLibros";
    }

    @GetMapping("/submitLibro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libro", new Libros());
        return "formulario_libros"; // Nombre correcto del archivo HTML
    }

    @GetMapping("/editarLibro/{id}")
    public String editarLibro(@PathVariable("id") Long id, Model model) {
        Libros libro = librosRepository.findById(id).orElse(null);
        if (libro != null) {
            model.addAttribute("libro", libro);
            return "formulario_libros";
        }
        return "redirect:/listarLibros";
    }

    @PostMapping("/submitEdicionLibro")
    public String submitEdicionLibro(@ModelAttribute Libros libro, Model model) {
        if (libro.getId() != null) {
            Libros libroExistente = librosRepository.findById(libro.getId()).orElse(null);
            if (libroExistente != null) {
                libroExistente.setNombreLibro(libro.getNombreLibro());
                libroExistente.setAnioPublicacion(libro.getAnioPublicacion());
                libroExistente.setAutor(libro.getAutor());
                librosRepository.save(libroExistente);
                model.addAttribute("libro", libroExistente);
            }
        }
        return "redirect:/listarLibros";
    }

    @GetMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable("id") Long id) {
        librosRepository.deleteById(id);
        return "redirect:/listarLibros";
    }
}
