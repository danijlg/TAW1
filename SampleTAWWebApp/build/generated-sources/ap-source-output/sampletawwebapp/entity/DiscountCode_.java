package sampletawwebapp.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sampletawwebapp.entity.Customer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-10T01:05:34")
@StaticMetamodel(DiscountCode.class)
public class DiscountCode_ { 

    public static volatile SingularAttribute<DiscountCode, String> discountCode;
    public static volatile SingularAttribute<DiscountCode, BigDecimal> rate;
    public static volatile ListAttribute<DiscountCode, Customer> customerList;

}