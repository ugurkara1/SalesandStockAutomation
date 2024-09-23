
package tr.com.nemesisyazilim.interfaces;

import java.util.List;

public interface DALInterfaces<T> {
    void Insert(T entity);
    List<T> GetALL();
    T Delete(T entity);
    void Update(T entity);
    List<T> GetById(int id);
}
