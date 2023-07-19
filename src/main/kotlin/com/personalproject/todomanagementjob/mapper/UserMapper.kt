package com.personalproject.todomanagementjob.mapper

import com.personalproject.todomanagementjob.model.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    fun selectAllUsers(): List<User>
}
