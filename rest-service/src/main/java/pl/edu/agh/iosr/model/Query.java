package pl.edu.agh.iosr.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Query {

    @Id
    private ObjectId queryId;
    private String ownerId;
    private String queryContent;
    private String processedContent;
    private int crawlingInterval;
    private double accuracyCap;
    private String status;

    @JsonProperty("query_id")
    public String getQueryId() {
        return queryId != null ? queryId.toString() : null;
    }

    public void setQueryId(ObjectId queryId) {
        this.queryId = queryId;
    }

    @JsonProperty("accuracy_cap")
    public double getAccuracyCap() {
        return accuracyCap;
    }

    public void setAccuracyCap(double accuracyCap) {
        this.accuracyCap = accuracyCap;
    }

    @JsonProperty("crawling_inverval")
    public int getCrawlingInterval() {
        return crawlingInterval;
    }

    public void setCrawlingInterval(int crawlingInterval) {
        this.crawlingInterval = crawlingInterval;
    }

    @JsonProperty("query_content")
    public String getQueryContent() {
        return queryContent;
    }

    public void setQueryContent(String queryContent) {
        this.queryContent = queryContent;
    }

    @JsonProperty("owner_id")
    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("processed_content")
    public String getProcessedContent() {
        return processedContent;
    }

    public void setProcessedContent(String processedContent) {
        this.processedContent = processedContent;
    }
}
