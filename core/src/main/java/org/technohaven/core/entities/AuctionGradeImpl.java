package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.*;

@Entity
@Table(name = "AUCTION_GRADE")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "AuctionGradeImpl_AuctionGrade")
public class AuctionGradeImpl implements AuctionGrade{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(AuctionGradeImpl.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator= "AuctionGradeId")
//    @GenericGenerator(
//            name="AuctionGradeId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="AuctionGradeImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.AuctionGradeImpl")
//            }
//    )
    @Column(name = "AUCTION_GRADE_ID", nullable = false)
    protected Long id;

    @Column(name = "AUCTION_GRADE_GRADE", nullable = false)
    @AdminPresentation(friendlyName = "AuctionGradeImpl_AuctionGrade_Grade", order = 1, prominent = true, gridOrder = 1)
    protected String grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
