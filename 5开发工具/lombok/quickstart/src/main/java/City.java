import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-12-04
 */

@Data
public class City {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdDate;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
