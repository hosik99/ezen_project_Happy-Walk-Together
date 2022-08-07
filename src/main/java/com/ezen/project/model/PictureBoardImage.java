package com.ezen.project.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "board") //연관 관계시 항상 주의
public class PictureBoardImage  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY) //무조건 lazy로
    private PictureBoard board;


}
