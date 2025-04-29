package com.example.resortmanagement.dao;

import com.example.resortmanagement.model.Resort;
import java.util.List;

public interface ResortDao {
    void save(Resort resort);
    Resort findById(Long id);
    List<Resort> findAll();
    void update(Resort resort);
    void delete(Long id);
}
