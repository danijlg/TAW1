package sampletawwebapp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sampletawwebapp.entity.MicroMarket;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-10T01:05:34")
@StaticMetamodel(Administrador.class)
public class Administrador_ { 

    public static volatile SingularAttribute<Administrador, String> password;
    public static volatile ListAttribute<Administrador, MicroMarket> microMarketList;
    public static volatile SingularAttribute<Administrador, Integer> adminId;
    public static volatile SingularAttribute<Administrador, String> email;

}