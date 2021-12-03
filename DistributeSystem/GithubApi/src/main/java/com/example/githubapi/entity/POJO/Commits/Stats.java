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
public class Stats {

    private int total;
    private int additions;
    private int deletions;
    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
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

}