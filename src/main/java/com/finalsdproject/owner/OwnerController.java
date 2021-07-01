package com.finalsdproject.owner;

import com.finalsdproject.owner.model.OwnerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finalsdproject.URLMapping.ENTITY;
import static com.finalsdproject.URLMapping.OWNER;

@RestController
@RequestMapping(OWNER)
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDTO> allOwners() {
        return ownerService.findAll();
    }

    @PostMapping
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.create(ownerDTO, ownerDTO.getAnimalID());
    }

    @PutMapping(ENTITY)
    public OwnerDTO edit(@RequestBody OwnerDTO ownerDTO, @PathVariable Long id) {
        return ownerService.edit(id, ownerDTO);
    }

    @DeleteMapping(ENTITY)
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteById(id);
    }

    @GetMapping(ENTITY)
    public OwnerDTO getOwner(@PathVariable Long id) {
        return ownerService.get(id);
    }

}
