package com.finalsdproject.report;

import com.finalsdproject.animal.AnimalService;
import com.finalsdproject.animal.model.AnimalDTO;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.finalsdproject.report.ReportType.PDF;

@Service
@AllArgsConstructor
public class PdfReportService implements ReportService {

    private final AnimalService animalService;

    @Override
    public ByteArrayOutputStream export() {
        PDDocument document = new PDDocument();
        PDPage pdPage = new PDPage();
        document.addPage(pdPage);

       List<AnimalDTO> adoptedAnimals = animalService.adoptedAnimals();
       ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 13);
            contentStream.setLeading(16f);
            contentStream.newLineAtOffset(36, 660);
            contentStream.showText("ADOPTED ANIMALS: ");
            contentStream.newLine();
            contentStream.newLine();

            for(AnimalDTO animalDTO: adoptedAnimals) {
                contentStream.showText("ID: " + animalDTO.getId());
                contentStream.newLine();
                contentStream.showText("Species: " + animalDTO.getAnimal());
                contentStream.newLine();
                contentStream.showText("Name: " + animalDTO.getName());
                contentStream.newLine();
                contentStream.showText("Age: " + animalDTO.getAge());
                contentStream.newLine();
                contentStream.showText("Gender: " + animalDTO.getGender());
                contentStream.newLine();
                contentStream.showText("Size: " + animalDTO.getSize());
                contentStream.newLine();
                contentStream.showText("Description: " + animalDTO.getDescription());
                contentStream.newLine();
                contentStream.showText("----------------------------------------------");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save(output);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    @Override
    public ReportType getType() {
        return PDF;
    }

}

