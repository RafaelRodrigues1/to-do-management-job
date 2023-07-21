package com.personalproject.todomanagementjob.mapper

import com.personalproject.todomanagementjob.model.User
import com.personalproject.todomanagementjob.model.UserStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMapper {

    fun findOneUserByStatus(@Param("status") status: UserStatus): User?

    fun updateStatusUser( @Param("id") id: String, @Param("status") status: UserStatus)
}
