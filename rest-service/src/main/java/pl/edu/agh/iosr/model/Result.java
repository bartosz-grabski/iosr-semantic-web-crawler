package pl.edu.agh.iosr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class Result {

    @Id
    private ObjectId resultId;
    @Property("query_id")
    private String queryId;
    @Property("url")
    private String url;

    @JsonProperty("result_id")
    public String getResultId() {
        return resultId != null ? resultId.toString() : null;
    }

    public void setResultId(ObjectId resultId) {
        this.resultId = resultId;
    }

    @JsonProperty("query_id")
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
