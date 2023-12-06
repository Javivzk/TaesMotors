package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Extra;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.repository.CarRepository;
import com.svalero.taesmotors.repository.ExtraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class ExtraServiceImpl implements ExtraService {

    @Autowired
    private ExtraRepository extraRepository;

    private final Logger logger = LoggerFactory.getLogger(ExtraServiceImpl.class);

    @Override
    public List<Extra> findAll() {
        return extraRepository.findAll();
    }

    @Override
    public Extra addExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    @Override
    public void deleteExtra(long extraId) {
        extraRepository.deleteById(extraId);
    }

    @Override
    public Extra findById(long extraId) throws ExtraNotFoundException {
        logger.info("Extra id: " + extraId);
        return extraRepository.findById(extraId)
                .orElseThrow(ExtraNotFoundException::new);
    }

    @Override
    public Extra modifyExtra(long extraId, Extra newExtra) throws ExtraNotFoundException {
        Extra existingExtra = extraRepository.findById(extraId)
                .orElseThrow(ExtraNotFoundException::new);
        logger.info("Extra to modify: " + existingExtra);
        existingExtra.setName(newExtra.getName());
        existingExtra.setDescription(newExtra.getDescription());
        existingExtra.setPrice(newExtra.getPrice());
        existingExtra.setStock(newExtra.getStock());

        logger.info("Extra modified: " + newExtra);
        return extraRepository.save(existingExtra);
    }

    @Override
    public List<Extra> findByName(String name) {
        return extraRepository.findByName(name);
    }

    public Extra patchExtra(long extraId, Map<String, Object> updates) throws ExtraNotFoundException {
        Extra existingExtra = findById(extraId);

        if (updates.containsKey("name")) {
            existingExtra.setName((String) updates.get("name"));
        }
        if (updates.containsKey("description")) {
            existingExtra.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            existingExtra.setPrice((double) updates.get("price"));
        }
        if (updates.containsKey("stock")) {
            existingExtra.setStock((Boolean) updates.get("stock"));
        }
        return modifyExtra(extraId, existingExtra);
    }
}
