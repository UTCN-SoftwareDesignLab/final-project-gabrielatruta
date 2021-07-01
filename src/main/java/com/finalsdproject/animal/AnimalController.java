package com.finalsdproject.animal;

import com.finalsdproject.animal.model.AnimalDTO;
import com.finalsdproject.email.EmailRequestDTO;
import com.finalsdproject.email.EmailServiceFactory;
import com.finalsdproject.report.ReportServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.finalsdproject.URLMapping.*;
import static com.finalsdproject.report.ReportType.PDF;

@RestController
@RequestMapping(ANIMAL)
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ReportServiceFactory reportServiceFactory;
    private final EmailServiceFactory emailServiceFactory;

    @PostMapping(EMAIL)
    public void sendAdoptionRequest(@RequestBody EmailRequestDTO emailRequestDTO){
        emailServiceFactory.sendMail(emailRequestDTO);
    }

    @GetMapping
    public List<AnimalDTO> allAnimals() {
        return animalService.allAnimals();
    }

    @GetMapping(ADOPTED_ANIMALS)
    public ResponseEntity<?> adoptedAnimals() {
        ByteArrayOutputStream bodyOutput = reportServiceFactory.getReportService(PDF).export();
        ByteArrayResource byteArrayResource = new ByteArrayResource(bodyOutput.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Adopted_Animals.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);
    }

    @PostMapping
    public AnimalDTO create(@RequestBody AnimalDTO animalDTO) {
        return animalService.create(animalDTO);
    }

    @PutMapping(ENTITY)
    public AnimalDTO edit(@RequestBody AnimalDTO animalDTO, @PathVariable Long id) {
        return animalService.edit(id, animalDTO);
    }

//    @PutMapping(ADOPT + ENTITY)
//    public AnimalDTO adoptAnimal(@RequestBody AnimalDTO animalDTO, @PathVariable Long id) {
//        return animalService.adoptAnimal(animalDTO, id);
//    }

    @GetMapping(ENTITY)
    public AnimalDTO getAnimal(@PathVariable Long id) {
        return animalService.get(id);
    }

    @DeleteMapping(ENTITY)
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteById(id);
    }



}
