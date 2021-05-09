package sampletawwebapp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sampletawwebapp.entity.Administrador;
import sampletawwebapp.entity.Customer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-10T01:05:34")
@StaticMetamodel(MicroMarket.class)
public class MicroMarket_ { 

    public static volatile SingularAttribute<MicroMarket, Double> areaLength;
    public static volatile SingularAttribute<MicroMarket, String> zipCode;
    public static volatile SingularAttribute<MicroMarket, Double> areaWidth;
    public static volatile SingularAttribute<MicroMarket, Administrador> adminId;
    public static volatile ListAttribute<MicroMarket, Customer> customerList;
    public static volatile SingularAttribute<MicroMarket, Double> radius;

}