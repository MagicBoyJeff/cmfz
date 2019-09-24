package com.hujie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {

    private String title;
    private List<City> cities;

}
