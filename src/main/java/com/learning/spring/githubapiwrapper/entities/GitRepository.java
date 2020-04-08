package com.learning.spring.githubapiwrapper.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@Entity
public class GitRepository {
    @Id
    long id;

    @JsonIgnoreProperties
    Date timestamp;

    @JsonIgnoreProperties
    String queryName;

    String name;

    String ownerName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime createdAt;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime updatedAt;

    String language;

    String fullName;

    String description;

    @JsonProperty("private")
    String isPrivate;

    @JsonProperty("fork")
    String isFork;

    String url;

    String htmlUrl;

    String gitUrl;

    Long forksCount;

    Long stargazersCount;

    Long watchersCount;

    @JsonProperty("owner")
    private void unpackOwner(Map<String, Object> response) {
        ownerName = (String) response.get("login");
    }

}
