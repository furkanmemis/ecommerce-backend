package com.authentication.models;

import java.util.Date;
import java.util.UUID;


public class Log {

    private UUID uuid;
    private String logType;
    private Date createdDate;
    private String serviceName;
    private String summary;
    
    public Log(String logType, String serviceName, String summary){
        this.logType = logType;
        this.createdDate = new Date();
        this.serviceName = serviceName;
        this.summary = summary;
        this.uuid = UUID.randomUUID();
        
    }

    public String getLogType (){
        return this.logType;
    }

    public Date getCreatedAt(){
        return this.createdDate;
    }

    public String getServiceName(){
        return this.serviceName;
    }

    public String getSummary(){
        return this.summary;
    }

    public UUID getUuid(){
        return this.uuid;
    }

    
}
