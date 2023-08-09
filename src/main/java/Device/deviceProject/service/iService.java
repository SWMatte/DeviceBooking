package Device.deviceProject.service;

import java.util.List;

public interface iService <T>{

    public List<T> findAll();

    public void add(T element) throws Exception;

    public void remove(int id) throws Exception;

    public void update(T element) throws Exception;

}
