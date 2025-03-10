package com.app.controller.carController;

import com.app.entity.cars.Model;
import com.app.service.carService.ModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/models")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public List<Model> getAllModels(){
        return modelService.getAllModels();
    }

    @GetMapping("/paginated")
    public Page<Model> getModels(Pageable pageable) {
        return modelService.getModels(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id){
        Optional<Model> model = modelService.getModelById(id);
        return model.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Model createModel(@RequestBody Model model){
        return modelService.saveModel(model);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @RequestBody Model modelDetails) {
        Optional<Model> model = modelService.getModelById(id);
        if (model.isPresent()) {
            Model updatedModel = model.get();
            updatedModel.setName(modelDetails.getName());
            return ResponseEntity.ok(modelService.saveModel(updatedModel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
