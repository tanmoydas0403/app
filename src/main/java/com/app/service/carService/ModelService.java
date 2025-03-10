package com.app.service.carService;

import com.app.entity.cars.Model;
import com.app.repository.ModelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAllModels(){
        return modelRepository.findAll();
    }

    public Page<Model> getModels(Pageable pageable){
        return modelRepository.findAll(pageable);
    }

    public Optional<Model> getModelById(Long id){
        return modelRepository.findById(id);
    }

    public Model saveModel(Model model){
        return modelRepository.save(model);
    }

    public void deleteModel(Long id){
        modelRepository.deleteById(id);
    }
}
