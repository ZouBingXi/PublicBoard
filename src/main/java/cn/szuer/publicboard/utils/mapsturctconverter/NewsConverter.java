package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.model.NewsInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

//交给spring容器管理，并且引入格式转换类
@Mapper(componentModel = "spring",uses = {NewsFormat.class})
public interface NewsConverter{

    NewsConverter INSTANCE = Mappers.getMapper(NewsConverter.class);
    @Mapping(source = "newstypeid",target = "newstypename")
        //newsInfo转换成newsSendDto
    NewsSendDto NewsInfo2NewsSendDto(NewsInfo newsInfo);
    List<NewsSendDto> NewsInfos2NewsSendDtos(List<NewsInfo> newsInfos);

    //AddNewsParam转换成NewsSendDto
    NewsSendDto AddNewsParam2NewsSendDto(AddNewsParam addNewsParam);
    List<NewsSendDto> AddNewsParams2NewsSendDtos(List<AddNewsParam> addNewsParams);


}
