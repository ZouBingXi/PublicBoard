package cn.szuer.publicboard.dto;

import lombok.Data;

import java.util.List;

@Data
public class TypeSendDto {

  /**
   * 帖子类型编号
   */
  private Integer typeid;

  /**
   * 帖子类型名
   */
  private String typename;

}
