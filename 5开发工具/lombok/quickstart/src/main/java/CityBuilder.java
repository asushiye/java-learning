import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-12-04
 */

@Builder
@ToString
public class CityBuilder {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
}
