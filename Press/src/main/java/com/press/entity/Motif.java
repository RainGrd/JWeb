package com.press.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-26  11:26
 * @Description: TODO
 * @Version: 1.0
 */
public class Motif implements Serializable {
    /*序列号*/
    private static final long serialVersionUID = 2220327758076721474L;

    private Integer motifId;
    private String motifName;
    private List<Press> pressList;

    public Integer getMotifId() {
        return motifId;
    }

    public void setMotifId(Integer motifId) {
        this.motifId = motifId;
    }

    public String getMotifName() {
        return motifName;
    }

    public void setMotifName(String motifName) {
        this.motifName = motifName;
    }

    public List<Press> getPressList() {
        return pressList;
    }

    public void setPressList(List<Press> pressList) {
        this.pressList = pressList;
    }

    @Override
    public String toString() {
        return "Motif{" +
                "motifId=" + motifId +
                ", motifName='" + motifName + '\'' +
                ", pressList=" + pressList +
                '}';
    }
}
