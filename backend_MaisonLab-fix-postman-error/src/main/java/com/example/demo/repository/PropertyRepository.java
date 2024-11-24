package com.example.demo.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByType(String type); // Filtrer par achat ou location

    List<Property> findByPriceBetween(Double minPrice, Double maxPrice); // Filtrer par intervalle de prix
    
    List<Property> findByBedrooms(int bedrooms); // Filtrer par nombre de chambres

    List<Property> findByTitleContainingOrDescriptionContaining(String keyword, String keyword2); // Recherche par mot-clé
}
