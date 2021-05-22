package com.anvesh.recepieapp.bootstrap;

import com.anvesh.recepieapp.domain.Categorie;
import com.anvesh.recepieapp.domain.UnitOfMeasurment;
import com.anvesh.recepieapp.repositories.CategorierRepo;
import com.anvesh.recepieapp.repositories.UnitOfMeasureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile({"dev", "prod"})
public class BootstrapMySql implements ApplicationListener<ContextRefreshedEvent> {
    private final CategorierRepo CategorieRepository;
    private final UnitOfMeasureRepo UnitOfMeasurmentRepository;

    public BootstrapMySql(CategorierRepo CategorieRepository, UnitOfMeasureRepo UnitOfMeasurmentRepository) {
        this.CategorieRepository = CategorieRepository;
        this.UnitOfMeasurmentRepository = UnitOfMeasurmentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (CategorieRepository.count() == 0L) {
            log.debug("Loading Categories");
            loadCategories();
        }

        if (UnitOfMeasurmentRepository.count() == 0L) {
            log.debug("Loading UOMs");
            loadUom();
        }
    }

    private void loadCategories() {
        Categorie cat1 = new Categorie();
        cat1.setDescription("American");
        CategorieRepository.save(cat1);

        Categorie cat2 = new Categorie();
        cat2.setDescription("Italian");
        CategorieRepository.save(cat2);

        Categorie cat3 = new Categorie();
        cat3.setDescription("Mexican");
        CategorieRepository.save(cat3);

        Categorie cat4 = new Categorie();
        cat4.setDescription("Fast Food");
        CategorieRepository.save(cat4);
    }

    private void loadUom() {
        UnitOfMeasurment uom1 = new UnitOfMeasurment();
        uom1.setUom("Teaspoon");
        UnitOfMeasurmentRepository.save(uom1);

        UnitOfMeasurment uom2 = new UnitOfMeasurment();
        uom2.setUom("Tablespoon");
        UnitOfMeasurmentRepository.save(uom2);

        UnitOfMeasurment uom3 = new UnitOfMeasurment();
        uom3.setUom("Cup");
        UnitOfMeasurmentRepository.save(uom3);

        UnitOfMeasurment uom4 = new UnitOfMeasurment();
        uom4.setUom("Pinch");
        UnitOfMeasurmentRepository.save(uom4);

        UnitOfMeasurment uom5 = new UnitOfMeasurment();
        uom5.setUom("Ounce");
        UnitOfMeasurmentRepository.save(uom5);

        UnitOfMeasurment uom6 = new UnitOfMeasurment();
        uom6.setUom("Each");
        UnitOfMeasurmentRepository.save(uom6);

        UnitOfMeasurment uom7 = new UnitOfMeasurment();
        uom7.setUom("Pint");
        UnitOfMeasurmentRepository.save(uom7);

        UnitOfMeasurment uom8 = new UnitOfMeasurment();
        uom8.setUom("Dash");
        UnitOfMeasurmentRepository.save(uom8);
    }
}
