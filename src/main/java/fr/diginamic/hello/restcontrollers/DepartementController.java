package fr.diginamic.hello.restcontrollers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.exceptions.DepartementNotFoundException;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.mapper.DepartementMapper;
import fr.diginamic.hello.model.City;
import fr.diginamic.hello.model.Departement;
import fr.diginamic.hello.services.DepartementService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/departement")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @Autowired
    private DepartementMapper departementMapper;

    @GetMapping
    public List<DepartementDto> getDepartements() {
        List<Departement> depts = this.departementService.getAll();
        return depts.stream().map(dept -> departementMapper.toDto(dept)).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementDto> getDepartement(@PathVariable int id) {
        try {
            Departement existingCity = this.departementService.findById(id);
            return new ResponseEntity<>(this.departementMapper.toDto(existingCity), HttpStatus.OK);
        }catch (DepartementNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Departement create(@Valid @RequestBody Departement city) {
        try {
            return this.departementService.create(city);
        } catch (FunctionalException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public Departement update(
            @Valid @RequestBody Departement city) {
        return this.departementService.update(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Departement existingCity = this.departementService.findById(id);
        if (existingCity == null) {
            return ResponseEntity.badRequest().body("Ressource not found");
        }
        return this.departementService.delete(existingCity);
    }

    @GetMapping("/{id}/largest-cities")
    public List<City> getLargestCities(
            @PathVariable int id,
            @NotNull @RequestParam int limit) {
        return this.departementService.getLargestCities(id, limit);
    }

    @GetMapping("/{id}/population-between-limit")
    public List<City> getCitiesPopulationBetweenLimit(
            @PathVariable int id,
            @NotNull @RequestParam int min,
            @NotNull @RequestParam int max) {
        return this.departementService.getCitiesPopulationBetweenLimit(id, min, max);
    }

    @GetMapping("/department-pdf/{id}")
    public void getDepartementPDF(@PathVariable long id, HttpServletResponse response) throws IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=\"departement.pdf\"");
        Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        Departement dept = this.departementService.findById(id);
        List<City> cities = dept.getCities();

        document.open();
        document.addTitle("Department");
        document.newPage();
        BaseFont baseFont = BaseFont.createFont();
        Phrase title = new Phrase(dept.getName() + "\n", new Font(baseFont, 32.0f, 1));
        document.add(title);

        Phrase code = new Phrase("Department code: " + dept.getCode() + "\n", new Font(baseFont, 12.0f, 1));
        document.add(code);

        Phrase title2 = new Phrase("Cities list: \n", new Font(baseFont, 12.0f, 1));
        document.add(title2);

        for (City city : cities) {
            Phrase cityLine = new Phrase("- " + city.getName() + "\n", new Font(baseFont, 12.0f, 1));
            document.add(cityLine);
        }

        document.close();

        response.flushBuffer();
    }
}
