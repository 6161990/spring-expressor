package kim.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    @NotBlank
    private String title;

    private String content;

    @NotBlank
    private String writer;

    private LocalDate createdAt;

    @Nullable
    private LocalDateTime modifiedAt;

    @Nullable
    private Integer likeCount;

    @Nullable
    private Integer viewCount;


    private Integer user_id;

    private Integer category_id;


}
