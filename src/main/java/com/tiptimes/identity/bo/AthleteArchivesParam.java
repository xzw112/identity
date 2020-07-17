package com.tiptimes.identity.bo;

import lombok.Data;

@Data
public class AthleteArchivesParam extends BaseParam{

    private String athleteId;

    private String regYear;

    private Integer timesNumber;
}
