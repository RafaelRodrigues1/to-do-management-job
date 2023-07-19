package com.personalproject.todomanagementjob.mapper

import com.personalproject.todomanagementjob.model.User
import com.personalproject.todomanagementjob.model.UserStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.util.UUID

@Mapper
interface UserMapper {

    fun findAllUsersByStatus(@Param("status") status: UserStatus): List<User>

    fun updateStatusUser( @Param("id") id: String, @Param("status") status: UserStatus)
}
