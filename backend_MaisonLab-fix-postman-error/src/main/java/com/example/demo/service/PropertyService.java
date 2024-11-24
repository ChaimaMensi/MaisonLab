package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.PropertyRepository;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Property;

@Service
public class PropertyService {
    
    @Autowired
    private PropertyRepository propertyRepository;

    // Lecture de toutes les propriétés
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Filtrage par type
    public List<Property> filterByType(String type) {
        return propertyRepository.findByType(type);
    }

    // Filtrage par gamme de prix
    public List<Property> filterByPriceRange(Double minPrice, Double maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Filtrage par nombre de chambres
    public List<Property> filterByBedrooms(int bedrooms) {
        return propertyRepository.findByBedrooms(bedrooms);
    }

    // Recherche par mot-clé dans le titre ou la description
    public List<Property> searchByKeyword(String keyword) {
        return propertyRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    // Création d'une nouvelle propriété
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    // Lecture d'une propriété par son ID
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    // Mise à jour d'une propriété existante
    public Property updateProperty(Long id, Property propertyDetails) {
        Optional<Property> existingProperty = propertyRepository.findById(id);
        
        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setTitle(propertyDetails.getTitle());
            property.setDescription(propertyDetails.getDescription());
            property.setLocation(propertyDetails.getLocation());
            property.setPrice(propertyDetails.getPrice());
            property.setBedrooms(propertyDetails.getBedrooms());
            property.setType(propertyDetails.getType());
            property.setStatus(propertyDetails.getStatus());
            property.setImageUrl(propertyDetails.getImageUrl());
            return propertyRepository.save(property);
        } else {
            throw new RuntimeException("Property not found with id: " + id);
        }
    }

    // Suppression d'une propriété par son ID
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
