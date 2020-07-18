package com.htp.service;

import com.htp.domain.Tool;

import java.util.List;
import java.util.Optional;

public interface ToolService {

    List<Tool> findAll();

    List<Tool> findAllAvailable();

//    List<Tool> search(String searchParam);

    Optional<Tool> findById(Long toolsId);

    Tool findOne(Long toolsId);

    Tool save(Tool tool);

    Tool update(Tool tool);

    void hardDelete(Long toolId);

}
