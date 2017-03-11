/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author deepak
 */
public interface DataModel<E>{
    
    public Collection<E> all() throws SQLException;
    
    public E get(int id) throws SQLException;
    
    public boolean add(E model) throws SQLException;
    
    public boolean delete(int id) throws SQLException;
    
    public void update(E model) throws SQLException;
    
}
