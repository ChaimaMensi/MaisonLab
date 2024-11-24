package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PropertyService;
import com.example.demo.entity.Property;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Récupérer toutes les propriétés
    @GetMapping("/all")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Le contrôleur fonctionne !");
    }
    // Récupérer une propriété par ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return ResponseEntity.ok(createdProperty);
    }

    // Mettre à jour une propriété existante
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        try {
            Property updatedProperty = propertyService.updateProperty(id, propertyDetails);
            return ResponseEntity.ok(updatedProperty);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une propriété par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    // Filtrer les propriétés par type
    @GetMapping("/filter/type")
    public List<Property> filterByType(@RequestParam String type) {
        return propertyService.filterByType(type);
    }

    // Filtrer les propriétés par gamme de prix
    @GetMapping("/filter/price")
    public List<Property> filterByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return propertyService.filterByPriceRange(minPrice, maxPrice);
    }

    // Filtrer les propriétés par nombre de chambres
    @GetMapping("/filter/bedrooms")
    public List<Property> filterByBedrooms(@RequestParam int bedrooms) {
        return propertyService.filterByBedrooms(bedrooms);
    }

    // Rechercher des propriétés par mot-clé dans le titre ou la description
    @GetMapping("/search")
    public List<Property> searchByKeyword(@RequestParam String keyword) {
        return propertyService.searchByKeyword(keyword);
    }
}
