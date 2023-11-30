package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Extra;
import com.svalero.taesmotors.exception.ExtraNotFoundException;

import java.util.List;
import java.util.Map;

public interface ExtraService {

    List<Extra> findAll();
    Extra addExtra(Extra extra);
    void deleteExtra(long extraId);
    Extra findById(long extraId) throws ExtraNotFoundException;
    Extra patchExtra(long extraId, Map<String, Object> updates) throws ExtraNotFoundException;
    Extra modifyExtra(long extraId, Extra newExtra) throws ExtraNotFoundException;
    List<Extra> findByName(String name);
}
