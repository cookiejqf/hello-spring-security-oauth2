package tk.mybatis.mapper;

import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends tk.mybatis.mapper.common.Mapper<T>, MySqlMapper<T> {
}
