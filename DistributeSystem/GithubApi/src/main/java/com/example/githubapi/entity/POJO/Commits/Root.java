/**
  * Copyright 2021 zhaotool.com 
  */
package com.example.githubapi.entity.POJO.Commits;

import java.util.List;

/**
 * Auto-generated: 2021-11-05 21:3:11
 *
 * @site 找工具m (zhaotool.com)
 * @json2java http://www.zhaotool.com/json2java/
 */
public class Root {

    private String sha;
    private String node_id;
    private Commit commit;
    private String url;
    private String html_url;
    private String comments_url;
    private Author author;
    private Committer committer;
    private List<Parents> parents;
    private Stats stats;
    private List<Files> files;
    public void setSha(String sha) {
         this.sha = sha;
     }
     public String getSha() {
         return sha;
     }

    public void setNode_id(String node_id) {
         this.node_id = node_id;
     }
     public String getNode_id() {
         return node_id;
     }

    public void setCommit(Commit commit) {
         this.commit = commit;
     }
     public Commit getCommit() {
         return commit;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setHtml_url(String html_url) {
         this.html_url = html_url;
     }
     public String getHtml_url() {
         return html_url;
     }

    public void setComments_url(String comments_url) {
         this.comments_url = comments_url;
     }
     public String getComments_url() {
         return comments_url;
     }

    public void setAuthor(Author author) {
         this.author = author;
     }
     public Author getAuthor() {
         return author;
     }

    public void setCommitter(Committer committer) {
         this.committer = committer;
     }
     public Committer getCommitter() {
         return committer;
     }

    public void setParents(List<Parents> parents) {
         this.parents = parents;
     }
     public List<Parents> getParents() {
         return parents;
     }

    public void setStats(Stats stats) {
         this.stats = stats;
     }
     public Stats getStats() {
         return stats;
     }

    public void setFiles(List<Files> files) {
         this.files = files;
     }
     public List<Files> getFiles() {
         return files;
     }

}