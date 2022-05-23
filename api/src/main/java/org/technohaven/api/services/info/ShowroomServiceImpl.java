package org.technohaven.api.services.info;

import org.springframework.stereotype.Service;
import org.technohaven.core.dao.info.ProfileDao;
import org.technohaven.core.dao.info.ShowroomDao;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import java.util.List;

@Service("blShowroomService")
public class ShowroomServiceImpl implements ShowroomService{

    @Resource(name="blShowroomDao")
    protected ShowroomDao showroomDao;

    @Override
    public List<Showroom> getShowrooms() {
        return showroomDao.getShowrooms();
    }

    @Override
    public Showroom findShowroomById(Long showroomId) {
        return showroomDao.readShowroomById(showroomId);
    }
}
