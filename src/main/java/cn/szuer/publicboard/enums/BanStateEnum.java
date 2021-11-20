package cn.szuer.publicboard.enums;


public enum BanStateEnum {
    Banned("封禁状态"),
    Well("正常状态");
    String des;
    BanStateEnum(String des)
    {
        this.des=des;
    }
    public String getDes() {
        return des;
    } 
}
