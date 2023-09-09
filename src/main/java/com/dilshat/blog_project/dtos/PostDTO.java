package com.dilshat.blog_project.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDTO {
    private String title;
    private String content;
}
