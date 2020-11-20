package com.example.demo.type;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

/**
 * description:
 *
 * @author wqh
 * @date 2020/11/20
 */
@Slf4j
public class MyUserType implements UserType {

    /**
     * Return the SQL type codes for the columns mapped by this type. The
     * codes are defined on <tt>java.sql.Types</tt>.
     *
     * @return int[] the typecodes
     * @see Types
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.BIGINT};
    }

    /**
     * The class returned by <tt>nullSafeGet()</tt>.
     *
     * @return Class
     */
    @Override
    public Class returnedClass() {
        return User.class;
    }

    /**
     * Compare two instances of the class mapped by this type for persistence "equality".
     * Equality of the persistent state.
     *
     * @param x
     * @param y
     * @return boolean
     */
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) return true;

        User userX = null;
        User userY = null;
        if (x instanceof User) {
            userX = (User) x;
        }
        if (y instanceof User) {
             userY = (User) y;
        }
//        if (userX==null && userY==null) return true;
        if (userX ==null || userY ==null) return  false;
        return userX.equals(userY);
    }

    /**
     * Get a hashcode for the instance, consistent with persistence "equality"
     *
     * @param x
     */
    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hash(x);
    }

    /**
     * Retrieve an instance of the mapped class from a JDBC resultset. Implementors
     * should handle possibility of null values.
     *
     * @param rs      a JDBC result set
     * @param names   the column names
     * @param session
     * @param owner   the containing entity  @return Object
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        log.info("");
        long value = rs.getLong(names[0]);
        User user = new User();
        user.setNum(value);
        return user;
    }

    /**
     * Write an instance of the mapped class to a prepared statement. Implementors
     * should handle possibility of null values. A multi-column type should be written
     * to parameters starting from <tt>index</tt>.
     *
     * @param st      a JDBC prepared statement
     * @param value   the object to write
     * @param index   statement parameter index
     * @param session
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        log.info("");
        if (value == null) {
            st.setNull(index, Types.BIGINT);
        }else {
            if (value instanceof User) {
                User user = (User) value;
                Long num = user.getNum();
                st.setLong(index,num);
            }else {
                st.setNull(index, Types.BIGINT); 
            }
        }
    }

    /**
     * Return a deep copy of the persistent state, stopping at entities and at
     * collections. It is not necessary to copy immutable objects, or null
     * values, in which case it is safe to simply return the argument.
     *
     * @param value the object to be cloned, which may be null
     * @return Object a copy
     */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        log.info("{}",value);
        if (value==null) return null;
        if (value instanceof User) {
            User user = (User) value;
            return user;
        }
        return null;
    }

    /**
     * Are objects of this type mutable?
     *
     * @return boolean
     */
    @Override
    public boolean isMutable() {
        log.info("isMutable");
        return false;
    }

    /**
     * Transform the object into its cacheable representation. At the very least this
     * method should perform a deep copy if the type is mutable. That may not be enough
     * for some implementations, however; for example, associations must be cached as
     * identifier values. (optional operation)
     *
     * @param value the object to be cached
     * @return a cachable representation of the object
     * @throws HibernateException
     */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        log.info("disassemble");
        return null;
    }

    /**
     * Reconstruct an object from the cacheable representation. At the very least this
     * method should perform a deep copy if the type is mutable. (optional operation)
     *
     * @param cached the object to be cached
     * @param owner  the owner of the cached object
     * @return a reconstructed object from the cachable representation
     * @throws HibernateException
     */
    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        log.info("assemble");
        return null;
    }

    /**
     * During merge, replace the existing (target) value in the entity we are merging to
     * with a new (original) value from the detached entity we are merging. For immutable
     * objects, or null values, it is safe to simply return the first parameter. For
     * mutable objects, it is safe to return a copy of the first parameter. For objects
     * with component values, it might make sense to recursively replace component values.
     *
     * @param original the value from the detached entity being merged
     * @param target   the value in the managed entity
     * @param owner
     * @return the value to be merged
     */
    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        log.info("replace");
        return this.deepCopy(original);
    }
}
