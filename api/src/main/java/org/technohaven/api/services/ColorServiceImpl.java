package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.ColorDao;
import org.technohaven.core.dao.PortDao;
import org.technohaven.core.entities.Color;

import javax.annotation.Resource;
import java.util.List;

@Service("blColorService")
public class ColorServiceImpl implements ColorService{

    protected static final Log LOG = LogFactory.getLog(ColorServiceImpl.class);

    @Resource(name="blColorDao")
    protected ColorDao colorDao;

    @Override
    public List<Color> getAllColor() {
        return colorDao.readAllColor();
    }

    @Override
    public List<Color> findColors(int limit, int offset) {
        return colorDao.readColors(limit, offset);
    }

    @Override
    public List<Color> findColorsByName(String colorName, int limit, int offset) {
        return colorDao.readColorsByName(colorName, limit, offset);
    }
}
