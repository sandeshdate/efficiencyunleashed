package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    private String id;
    private String key;
    private String value;
}
