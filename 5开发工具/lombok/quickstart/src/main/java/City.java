import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-12-04
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
}
