package org.technohaven.api.services;

import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.core.dao.ShowroomDao;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import java.util.List;

@Service("blShowroomService")
public class ShowroomServiceImpl implements ShowroomService{

    @Resource(name="blShowroomDao")
    protected ShowroomDao showroomDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public Showroom save(Showroom showroom) {
        try {
            if (showroom.getId() == null) {
                showroom.setId(findNextShowroomId());
            }
            return showroomDao.save(showroom);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<Showroom> getShowrooms() {
        return showroomDao.getShowrooms();
    }

    @Override
    public Showroom findShowroomById(Long showroomId) {
        return showroomDao.readShowroomById(showroomId);
    }

    @Override
    public Showroom createShowroomFromId(Long showroomId) {
        Showroom showroom = showroomId != null ? findShowroomById(showroomId) : null;
        if (showroom == null) {
            showroom = showroomDao.create();
            if (showroomId != null) {
                showroom.setId(showroomId);
            } else {
                showroom.setId(findNextShowroomId());
            }
        }
        return showroom;
    }

    @Override
    public Long findNextShowroomId() {
        return idGenerationService.findNextId(Showroom.class.getCanonicalName());
    }

    @Override
    @Transactional("blTransactionManager")
    public void removeShowroom(Showroom showroom) {
        showroomDao.deleteShowroom(showroom);
    }

}
