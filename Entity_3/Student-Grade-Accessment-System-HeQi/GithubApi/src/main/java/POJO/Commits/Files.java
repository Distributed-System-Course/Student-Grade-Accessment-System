/**
  * Copyright 2021 zhaotool.com 
  */
package POJO.Commits;

/**
 * Auto-generated: 2021-11-05 21:3:11
 *
 * @site 找工具m (zhaotool.com)
 * @json2java http://www.zhaotool.com/json2java/
 */
public class Files {

    private String sha;
    private String filename;
    private String status;
    private int additions;
    private int deletions;
    private int changes;
    private String blob_url;
    private String raw_url;
    private String contents_url;
    private String patch;
    public void setSha(String sha) {
         this.sha = sha;
     }
     public String getSha() {
         return sha;
     }

    public void setFilename(String filename) {
         this.filename = filename;
     }
     public String getFilename() {
         return filename;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setAdditions(int additions) {
         this.additions = additions;
     }
     public int getAdditions() {
         return additions;
     }

    public void setDeletions(int deletions) {
         this.deletions = deletions;
     }
     public int getDeletions() {
         return deletions;
     }

    public void setChanges(int changes) {
         this.changes = changes;
     }
     public int getChanges() {
         return changes;
     }

    public void setBlob_url(String blob_url) {
         this.blob_url = blob_url;
     }
     public String getBlob_url() {
         return blob_url;
     }

    public void setRaw_url(String raw_url) {
         this.raw_url = raw_url;
     }
     public String getRaw_url() {
         return raw_url;
     }

    public void setContents_url(String contents_url) {
         this.contents_url = contents_url;
     }
     public String getContents_url() {
         return contents_url;
     }

    public void setPatch(String patch) {
         this.patch = patch;
     }
     public String getPatch() {
         return patch;
     }

}