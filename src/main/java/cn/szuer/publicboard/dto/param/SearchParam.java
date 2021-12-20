package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam
{
    /**
     * 关键词
     * @mock 饭堂
     */
    private String key;

    /**
     * 排序方式
     * 1=按时间排序
     * 2=按浏览量排序
     * 3=按点赞量排序
     * @mock 1
     */
    private Integer sort;

    /**
     * 查找的帖子类型
     * @mock 校园卡丢失
     */
    private String type;

    /**
     * 每页的记录数
     * @mock 8
     */
    private Integer pageSize;

    /**
     * 当前页数
     * @mock 1
     */
    private Integer pageNum;
}
