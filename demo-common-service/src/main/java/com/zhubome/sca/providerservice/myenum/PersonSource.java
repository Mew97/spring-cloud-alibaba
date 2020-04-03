package com.zhubome.sca.providerservice.myenum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonSource {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}


