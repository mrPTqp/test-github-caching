package com.github.mrptqp.githubapiwrapper.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GitRepository {
    @Id
    private long id;

    @JsonIgnoreProperties
    private LocalDateTime timestamp;

    @JsonIgnoreProperties
    private String queryName;

    @JsonIgnoreProperties
    private String queryLanguage;

    private String name;

    private String ownerName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    private String language;

    private String fullName;

    private String description;

    @JsonProperty("private")
    private String isPrivate;

    @JsonProperty("fork")
    private String isFork;

    private String url;

    private String htmlUrl;

    private String gitUrl;

    private Long forksCount;

    private Long stargazersCount;

    private Long watchersCount;

    @JsonProperty("owner")
    private void unpackOwner(Map<String, Object> response) {
        ownerName = (String) response.get("login");
    }
}
