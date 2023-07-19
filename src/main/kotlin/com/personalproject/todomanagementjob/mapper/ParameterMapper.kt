package com.personalproject.todomanagementjob.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface ParameterMapper {

    fun findParameterValueByName(@Param("name") name: String): String
}
