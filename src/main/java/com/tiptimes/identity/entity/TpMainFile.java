package com.tiptimes.identity.entity;

import lombok.Data;
import javax.persistence.Id;
import java.util.Date;

@Data
public class TpMainFile {

    @Id
    private String fileId;

    private String fileName;

    private String fileType;

    private Date fileTime;

    private String fileStorageName;

    private String fileMd5;
}