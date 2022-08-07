package com.ezen.project.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"board","member"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private PictureBoard board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade;

    private String text;

    public void changeGrade(int grade){
        this.grade = grade;
    }

    public void changeText(String text){
        this.text = text;
    }

}
