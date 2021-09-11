package br.edu.ifce.dao;

import br.edu.ifce.annotations.NotMapped;
import br.edu.ifce.utils.ConnectionFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public abstract class GenericDAO {

    public <T> List<T> GenericSelect(String sql, ArrayList<Object> values, Class c){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<Object> results = new ArrayList<>();

        try{
            conn = ConnectionFactory.getConnection();

            stmt = conn.prepareStatement(sql);

            if(values != null) {
                for(int i = 0; i < values.size(); i++) {
                    stmt.setObject((i+1), values.get(i));
                }
            }

            rs = stmt.executeQuery();

            Class clazz = Class.forName(c.getName());
            Constructor<?> ctor = clazz.getConstructor();

            while(rs.next()){
                Object object = ctor.newInstance();
                for(Field f : object.getClass().getDeclaredFields()){
                    try{
                        if(!f.isAnnotationPresent(NotMapped.class)) {
                            int findColumn = rs.findColumn(f.getName());
                            f.set(object, rs.getObject(f.getName(), f.getType()));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        ex.getMessage();
                    }
                }
                results.add(object);
            }

        } catch (SQLException | ClassNotFoundException |
                NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex)
        {
            System.out.println("Erro: " + ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return (List<T>) results;
    }

    public boolean ExecuteCommand(String sql, ArrayList<Object> values) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();

            stmt = conn.prepareStatement(sql);

            if(values != null) {
                for (int i = 0; i < values.size(); i++) {
                    stmt.setObject((i+1), values.get(i));
                }
            }

            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
}
