package com.zeng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScoreOne {

    //分数
    private String score;
    //本段人数
    private Integer num;
    //累计人数
    private Integer total;
}
