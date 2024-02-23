package com.persistent.efficiencyunleashed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlackMessage {
    private String type;
    private String user;
    private String ts;
    private String text;
}
