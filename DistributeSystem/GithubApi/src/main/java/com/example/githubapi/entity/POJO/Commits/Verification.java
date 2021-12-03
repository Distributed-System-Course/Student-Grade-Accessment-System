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
public class Verification {

    private boolean verified;
    private String reason;
    private String signature;
    private String payload;
    public void setVerified(boolean verified) {
         this.verified = verified;
     }
     public boolean getVerified() {
         return verified;
     }

    public void setReason(String reason) {
         this.reason = reason;
     }
     public String getReason() {
         return reason;
     }

    public void setSignature(String signature) {
         this.signature = signature;
     }
     public String getSignature() {
         return signature;
     }

    public void setPayload(String payload) {
         this.payload = payload;
     }
     public String getPayload() {
         return payload;
     }

}