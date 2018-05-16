package mongodb.models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;


@Indexes({
        @Index(fields = @Field("customerId")),
        @Index(fields = @Field("order"))
})

@Entity(value = "vehicle", noClassnameStored = true)
public class Vehicle extends BaseEntity {

    private String customerId;
    private String carPlate;
    private String color;
    private String remark;

    private Integer order;
}
