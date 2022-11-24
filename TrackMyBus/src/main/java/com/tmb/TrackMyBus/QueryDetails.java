package com.tmb.TrackMyBus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Queries")
public class QueryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String QueryTopic;
    private String concern;
    private boolean member;

    public QueryDetails() {
    } 

    public QueryDetails(int id, String name, String email, String queryTopic, String concern, boolean member) {
        this.id = id;
        this.name = name;
        this.email = email;
        QueryTopic = queryTopic;
        this.concern = concern;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQueryTopic() {
        return QueryTopic;
    }

    public void setQueryTopic(String queryTopic) {
        QueryTopic = queryTopic;
    }

    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "QueryDetails [id=" + id + ", name=" + name + ", email=" + email + ", QueryTopic=" + QueryTopic
                + ", concern=" + concern + ", member=" + member + "]";
    }
    
    

 
}
