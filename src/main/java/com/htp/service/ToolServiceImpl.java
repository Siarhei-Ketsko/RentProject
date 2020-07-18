package com.htp.service;

import com.htp.dao.ToolRepository;
import com.htp.domain.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolServiceImpl implements ToolService {

    ToolRepository toolRepository;

    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }


    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public List<Tool> findAllAvailable() {
        return toolRepository.findAllByAvailabilityTrue();
    }

//    @Override
//    public List<Tool> search(String searchParam) {
//        return toolDao.search(searchParam);
//    }

    @Override
    public Optional<Tool> findById(Long toolsId) {
        return toolRepository.findById(toolsId);
    }

    @Override
    public Tool findOne(Long toolsId) {
        return toolRepository.findById(toolsId).get();
    }

    @Override
    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }

    @Override
    public Tool update(Tool tool) {
        return toolRepository.save(tool);
    }

    @Override
    public void hardDelete(Long toolId) {
        toolRepository.deleteById(toolId);
    }
}
