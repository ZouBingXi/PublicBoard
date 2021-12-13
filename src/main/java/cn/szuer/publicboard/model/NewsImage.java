package cn.szuer.publicboard.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NewsImage {
    private String imageuuid;

    private Integer newsid;

    public String getImageuuid() {
        return imageuuid;
    }

    public void setImageuuid(String imageuuid) {
        this.imageuuid = imageuuid;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }
}