package com.personalproject.todomanagementjob.mapper.typehandler

import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedTypes
import org.apache.ibatis.type.TypeHandler
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types
import java.util.UUID


@MappedTypes(UUID::class)
class UuidTypeHandler : TypeHandler<UUID?> {
    @Throws(SQLException::class)
    override fun setParameter(ps: PreparedStatement, i: Int, parameter: UUID?, jdbcType: JdbcType) {
        if (parameter == null) {
            ps.setObject(i, null, Types.OTHER)
        } else {
            ps.setObject(i, parameter.toString(), Types.OTHER)
        }
    }

    @Throws(SQLException::class)
    override fun getResult(rs: ResultSet, columnName: String): UUID? {
        return toUUID(rs.getString(columnName))
    }

    @Throws(SQLException::class)
    override fun getResult(rs: ResultSet, columnIndex: Int): UUID? {
        return toUUID(rs.getString(columnIndex))
    }

    @Throws(SQLException::class)
    override fun getResult(cs: CallableStatement, columnIndex: Int): UUID? {
        return toUUID(cs.getString(columnIndex))
    }

    companion object {
        private fun toUUID(valor: String): UUID? {
            if (!valor.isNullOrBlank()) {
                try {
                    return UUID.fromString(valor)
                } catch (e: IllegalArgumentException) {
                    println("Bad UUID found: $valor")
                }
            }
            return null
        }
    }
}
