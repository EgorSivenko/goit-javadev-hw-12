package org.example.service;

import org.example.dao.impl.PlanetDAO;
import org.example.entity.Planet;

import java.util.List;
import java.util.Objects;

public class PlanetService {

    private final PlanetDAO planetDAO = new PlanetDAO();

    public Planet getById(String id) {
        return planetDAO.findById(id);
    }

    public List<Planet> getAll() {
        return planetDAO.findAll();
    }

    public void add(Planet planet) {
        Planet planedById = planetDAO.findById(planet.getId());
        if (planedById != null) {
            throw new IllegalStateException("Planet with id '" + planet.getId() + "' already exists");
        }

        Planet planetByName = planetDAO.findByName(planet.getName());
        if (planetByName != null) {
            throw new IllegalStateException("Planet with name '" + planet.getName() + "' already exists");
        }
        planetDAO.save(planet);
    }

    public void update(Planet planet) {
        Planet planetById = planetDAO.findById(planet.getId());

        if (planetById != null && !Objects.equals(planetById.getName(), planet.getName())) {
            Planet planetByName = planetDAO.findByName(planet.getName());

            if (planetByName != null) {
                throw new IllegalStateException("Planet with name '" + planet.getName() + "' already exists");
            }
            planetDAO.update(planet);
        }
    }

    public void delete(Planet planet) {
        planetDAO.delete(planet);
    }

    public Planet deleteById(String id) {
        return planetDAO.deleteById(id);
    }
}
