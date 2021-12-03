/**
  * Copyright 2021 zhaotool.com 
  */
package com.example.githubapi.entity.POJO.Commits;

/**
 * Auto-generated: 2021-11-05 21:3:11
 *
 * @site 找工具m (zhaotool.com)
 * @json2java http://www.zhaotool.com/json2java/
 */
public class Commit {

    private Author author;
    private Committer committer;
    private String message;
    private Tree tree;
    private String url;
    private int comment_count;
    private Verification verification;
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

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setTree(Tree tree) {
         this.tree = tree;
     }
     public Tree getTree() {
         return tree;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setComment_count(int comment_count) {
         this.comment_count = comment_count;
     }
     public int getComment_count() {
         return comment_count;
     }

    public void setVerification(Verification verification) {
         this.verification = verification;
     }
     public Verification getVerification() {
         return verification;
     }

}